package com.zhaojj11.clockwork.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaojj11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDTO {
    private String username;
    private String password;
}
