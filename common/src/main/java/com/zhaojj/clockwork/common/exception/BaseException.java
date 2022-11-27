package com.zhaojj.clockwork.common.exception;

/**
 * 基础异常
 *
 * @author zhaojj11
 */
public class BaseException extends RuntimeException {
    protected int code;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
