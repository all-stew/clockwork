package com.zhaojj11.clockwork.common.exception;

import com.zhaojj.clockwork.common.exception.BaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BaseExceptionTest {
    @Test
    void testBaseException() {
        try {
            throw new BaseException("hello");
        } catch (BaseException e) {
            Assertions.assertEquals("hello", e.getMessage());
        }

        try {
            throw new BaseException(200, "hello");
        } catch (BaseException e) {
            Assertions.assertEquals("hello", e.getMessage());
            Assertions.assertEquals(200, e.getCode());
        }
    }
}