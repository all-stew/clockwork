package com.zhaojj11.clockwork.user.entity.vo.response;

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
public class LoginUserResponseVO {
    private String token;
}
