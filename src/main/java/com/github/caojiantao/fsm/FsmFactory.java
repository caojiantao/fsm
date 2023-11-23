package com.github.caojiantao.fsm;

import org.springframework.context.ApplicationContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 状态机工厂
 */
@SuppressWarnings("all")
public class FsmFactory {

    /**
     * 状态机类型映射
     */
    private static final Map<Class<?>, Fsm> fsmMap = new HashMap<>();

    private static volatile boolean init = false;

    /**
     * 项目各个类型状态机初始化入口
     *
     * @param applicationContext 容器上下文
     */
    public static void init(ApplicationContext applicationContext) {
        Collection<IFsmTransition> transitions = applicationContext.getBeansOfType(IFsmTransition.class).values();
        MultiValueMap<Class, IFsmTransition> transitionMap = new LinkedMultiValueMap<>();
        for (IFsmTransition transition : transitions) {
            Class<?> stateType = GenericUtils.getInterfaceGeneric(transition, IFsmTransition.class, 2);
            FsmAssert.notNull(stateType, "状态机类型不能为空");
            transitionMap.add(stateType, transition);
        }
        transitionMap.forEach((stateType, transitionList) -> {
            Fsm fsm = new Fsm();
            fsm.init(transitionList);
            fsmMap.put(stateType, fsm);
        });
        init = true;
    }

    /**
     * 驱动状态转移
     *
     * @param context 状态机上下文
     */
    public static void fire(FsmContext context) {
        FsmAssert.isTrue(init, "状态机还未初始化");
        Class<?> stateType = context.getClass();
        Fsm fsm = fsmMap.get(stateType);
        FsmAssert.notNull(fsm, "状态机不存在，" + stateType);
        fsm.fire(context);
    }
}
