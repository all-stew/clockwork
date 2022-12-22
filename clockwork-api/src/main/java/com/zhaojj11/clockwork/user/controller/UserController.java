package com.zhaojj11.clockwork.user.controller;

import com.zhaojj11.clockwork.common.model.ApiResponse;
import com.zhaojj11.clockwork.user.entity.transformer.UserTransformer;
import com.zhaojj11.clockwork.user.entity.vo.request.LoginUserRequestVO;
import com.zhaojj11.clockwork.user.entity.vo.response.LoginUserResponseVO;
import com.zhaojj11.clockwork.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojj11
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    private final UserTransformer userTransformer;

    @PostMapping("/login")
    public ApiResponse<LoginUserResponseVO> login(@RequestBody LoginUserRequestVO loginUserRequestVO) {
        String jwt = userService.login(userTransformer.toLoginUser(loginUserRequestVO));
        return ApiResponse.ok(LoginUserResponseVO.build(jwt));
    }

    @PostMapping("/logout")
    public ApiResponse<Object> logout() {
        userService.logout();
        return ApiResponse.ok();
    }
}
