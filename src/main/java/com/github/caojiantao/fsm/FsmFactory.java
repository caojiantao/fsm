package com.github.caojiantao.fsm;

import org.springframework.context.ApplicationContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FsmFactory {

    private static final Map<Class<?>, Fsm> fsmMap = new HashMap<>();

    private static volatile boolean init = false;

    public static void init(ApplicationContext applicationContext) {
        Collection<IFsmAction> actions = applicationContext.getBeansOfType(IFsmAction.class).values();
        MultiValueMap<Class, IFsmAction> actionMap = new LinkedMultiValueMap<>();
        for (IFsmAction action : actions) {
            FsmTransition annotation = action.getClass().getAnnotation(FsmTransition.class);
            FsmAssert.notNull(annotation, "action 不能没有 @FsmTransition 修饰，" + action);
            actionMap.add(annotation.stateType(), action);
        }
        actionMap.forEach((stateType, actionList) -> {
            Fsm fsm = new Fsm();
            fsm.init(actionList);
            fsmMap.put(stateType, fsm);
        });
        init = true;
    }

    public static <P extends Enum<P>, Q extends Enum<Q>> void fire(FsmContext<P, Q> context) {
        FsmAssert.isTrue(init, "状态机还未初始化");
        Class<?> stateType = context.getCurrentState().getClass();
        Fsm fsm = fsmMap.get(stateType);
        FsmAssert.notNull(fsm, "状态机不存在，" + stateType);
        fsm.fire(context);
    }
}
