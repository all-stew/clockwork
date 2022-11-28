package com.zhaojj.clockwork.common.constants;

import com.zhaojj11.clockwork.common.constants.StringConstant;
import lombok.experimental.UtilityClass;

/**
 * @author zhaojj11
 */
@UtilityClass
public class RedisConstants {
    private final static String PREFIX = StringConstant.PROJECT_NAME;
    private final static String SEPARATOR = StringConstant.COLON;

    /**
     * ${project_name}:login_token:${username}
     */
    public final static String USER_LOGIN_TOKEN = PREFIX + SEPARATOR + "login_token" + SEPARATOR + "%s";
}
