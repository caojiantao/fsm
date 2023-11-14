package com.github.caojiantao.fsm;

import java.util.Objects;

public class FsmAssert {

    public static void notNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new FsmException(message);
        }
    }

    public static void isTrue(boolean value, String message) {
        if (!value) {
            throw new FsmException(message);
        }
    }
}
