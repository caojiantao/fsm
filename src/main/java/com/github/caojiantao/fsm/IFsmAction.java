package com.github.caojiantao.fsm;

public interface IFsmAction<T extends FsmContext>  {

    void execute(T context);
}
