package com.github.caojiantao.fsm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简易状态机
 *
 * @param <P> 状态类型
 * @param <Q> 事件类型
 * @param <T> 状态机上下文
 */
public class Fsm<P extends Enum<P>, Q extends Enum<Q>, T extends FsmContext<P, Q>> {

    /**
     * 状态转移映射
     */
    private final Map<String, IFsmTransition<P, Q, T>> transitionMap = new HashMap<>();

    /**
     * 状态机初始化
     */
    public void init(List<IFsmTransition<P, Q, T>> transitionList) {
        for (IFsmTransition<P, Q, T> transition : transitionList) {
            String key = getTransitionKey(transition.getBeforeState(), transition.getTriggerEvent());
            transitionMap.put(key, transition);
        }
    }

    /**
     * 驱动状态转移
     *
     * @param context 状态机上下文
     */
    public void fire(T context) {
        String key = getTransitionKey(context.getCurrentState(), context.getTriggerEvent());
        IFsmTransition<P, Q, T> action = transitionMap.get(key);
        FsmAssert.notNull(action, "找不到 action，" + key);
        action.doAction(context);
    }

    private String getTransitionKey(P beforeState, Q triggerEvent) {
        return beforeState.name() + ":" + triggerEvent.name();
    }
}
