package com.github.caojiantao.fsm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fsm {

    private final Map<String, IFsmAction> transitionMap = new HashMap<>();

    public void init(List<IFsmAction> actionList) {
        for (IFsmAction action : actionList) {
            FsmTransition annotation = action.getClass().getAnnotation(FsmTransition.class);
            String key = getTransitionKey(annotation.beforeState(), annotation.triggerEvent());
            transitionMap.put(key, action);
        }
    }

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
