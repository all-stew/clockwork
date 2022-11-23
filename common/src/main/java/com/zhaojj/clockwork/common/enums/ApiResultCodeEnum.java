package com.zhaojj.clockwork.common.enums;


/**
 * 统一返回码枚举
 *
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

