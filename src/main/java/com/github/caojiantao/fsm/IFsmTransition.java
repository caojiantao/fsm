package com.github.caojiantao.fsm;

/**
 * 状态转移接口
 *
 * @param <P> 状态类型
 * @param <Q> 事件类型
 * @param <T> 状态机上下文
 */
public interface IFsmTransition<P extends Enum<P>, Q extends Enum<Q>, T extends FsmContext<P, Q>> {

    /**
     * 转移前状态
     */
    P getBeforeState();

    /**
     * 触发转移的具体事件
     */
    Q getTriggerEvent();

    /**
     * 转移后状态
     */
    P getAfterState();

    /**
     * 状态转移过程执行的动作
     */
    void doAction(T context);
}
