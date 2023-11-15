package com.github.caojiantao.fsm;

import lombok.Data;

/**
 * 状态机上下文
 *
 * @param <P> 状态类型枚举
 * @param <Q> 事件类型枚举
 */
@Data
public class FsmContext<P extends Enum<P>, Q extends Enum<Q>> {

    /**
     * 当前状态
     */
    private P currentState;

    /**
     * 触发事件
     */
    private Q triggerEvent;
}
