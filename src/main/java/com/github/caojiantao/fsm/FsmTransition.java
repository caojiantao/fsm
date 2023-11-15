package com.github.caojiantao.fsm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FsmTransition {

    /**
     * 状态类型，用来标记所属状态机类型
     */
    Class<? extends Enum<?>> stateType();

    /**
     * 转移前状态
     */
    String beforeState();

    /**
     * 转移后状态
     */
    String afterState();

    /**
     * 事件类型
     */
    Class<? extends Enum<?>> eventType();

    /**
     * 触发转移的具体事件
     */
    String triggerEvent();
}
