package com.zhaojj11.clockwork.user.service;

import com.zhaojj11.clockwork.user.entity.dto.LoginUserDTO;
import com.zhaojj11.clockwork.user.entity.dto.LoginUserDetails;
import org.springframework.lang.NonNull;

/**
 * UserService
 *
 * @author zhaojj11
 */
public interface UserService {
    /**
     * 登录,并且返回jwt
     *
     * @param dto dto
     * @return jwt
     */
    @NonNull
    String login(LoginUserDTO dto);

    /**
     * 登出
     */
    void logout();

    /**
     * 获取loginUserDetailDTO
     *
     * @param username 用户名（非空）
     * @return jwt
     * @see LoginUserDetails
     */
    @NonNull
    LoginUserDetails getLoginUserDetailDtoOrFail(@NonNull String username);
}
