package com.zhaojj11.clockwork.user.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhaojj11
 */
@Getter
@Setter
@ToString
public class LoginUserDTO {
    private String username;
    private String password;

    public static LoginUserDTO build(String username, String password) {
        LoginUserDTO dto = new LoginUserDTO();
        dto.setUsername(username);
        dto.setPassword(password);
        return dto;
    }
}
