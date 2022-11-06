package com.zhaojj.clockwork.common.enums;


/**
 * @author zhaojj11
 */
public enum ApiResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200),
    FAILED(400);

    private final Integer code;

    ApiResultCodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

