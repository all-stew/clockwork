package com.zhaojj11.clockwork.common.constants;

import lombok.experimental.UtilityClass;

/**
 * @author zhaojj11
 */
@UtilityClass
public class RedisConstants {
    private static final String PREFIX = StringConstant.PROJECT_NAME;
    private static final String SEPARATOR = StringConstant.COLON;

    /**
     * ${project_name}:login_token:${username}
     */
    public static final String USER_LOGIN_TOKEN = PREFIX + SEPARATOR + "login_token" + SEPARATOR + "%s";
}
