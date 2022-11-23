package com.zhaojj11.clockwork.entity.vo.request;

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
public class LoginUserRequestVO {
    private String username;
    private String password;
}
