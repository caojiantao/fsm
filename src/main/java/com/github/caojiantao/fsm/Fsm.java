package com.github.caojiantao.fsm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简易状态机
 */
public class Fsm {

    /**
     * 状态转移映射
     */
    private final Map<String, IFsmAction> transitionMap = new HashMap<>();

    /**
     * 状态机初始化
     */
    public void init(List<IFsmAction> actionList) {
        for (IFsmAction action : actionList) {
            FsmTransition annotation = action.getClass().getAnnotation(FsmTransition.class);
            String key = getTransitionKey(annotation.beforeState(), annotation.triggerEvent());
            transitionMap.put(key, action);
        }
    }

    /**
     * 驱动状态转移
     *
     * @param context 状态机上下文
     * @param <T>     上下文需要继承 FsmContext
     */
    public <T extends FsmContext> void fire(T context) {
        String key = getTransitionKey(context.getCurrentState().toString(), context.getTriggerEvent().toString());
        IFsmAction action = transitionMap.get(key);
        FsmAssert.notNull(action, "找不到 action，" + key);
        action.execute(context);
    }

    private String getTransitionKey(String beforeState, String triggerEvent) {
        return beforeState + ":" + triggerEvent;
    }
}
