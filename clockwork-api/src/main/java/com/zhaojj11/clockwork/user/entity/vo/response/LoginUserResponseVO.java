package com.zhaojj11.clockwork.user.entity.vo.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhaojj11
 */
@Setter
@Getter
@ToString
public class LoginUserResponseVO {
    private String token;

    public static LoginUserResponseVO build(String jwt) {
        LoginUserResponseVO vo = new LoginUserResponseVO();
        vo.setToken(jwt);
        return vo;
    }
}
