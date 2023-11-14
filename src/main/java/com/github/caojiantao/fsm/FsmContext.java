package com.github.caojiantao.fsm;

import lombok.Data;

@Data
public class FsmContext<P extends Enum<P>, Q extends Enum<Q>> {

    private P currentState;
    private Q triggerEvent;
}
