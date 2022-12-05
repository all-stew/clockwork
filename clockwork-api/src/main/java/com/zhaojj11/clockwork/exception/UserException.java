package com.zhaojj11.clockwork.exception;

import com.zhaojj11.clockwork.common.exception.BaseException;

/**
 * 用户异常
 *
 * @author zhaojj11
 */
public class UserException extends BaseException {
    public UserException(String message) {
        super(message);
    }

    public UserException(Exception e) {
        super(e);
    }

    public UserException(Integer code, String message) {
        super(code, message);
    }
}
