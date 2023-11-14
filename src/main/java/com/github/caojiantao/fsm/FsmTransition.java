package com.github.caojiantao.fsm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FsmTransition {

    Class<? extends Enum<?>> stateType();

    String beforeState();

    /**
     * 转移目标状态，程序通常不用
     */
    String afterState();

    Class<? extends Enum<?>> eventType();

    String triggerEvent();
}
