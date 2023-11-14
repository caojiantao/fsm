package com.github.caojiantao.fsm;

public class FsmException extends RuntimeException {

    private String message;

    public FsmException(String message) {
        super(message);
    }
}
