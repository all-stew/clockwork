package com.zhaojj11.clockwork.controller;

import com.zhaojj.clockwork.common.model.ApiResponse;
import com.zhaojj11.clockwork.entity.transformer.UserTransformer;
import com.zhaojj11.clockwork.entity.vo.request.LoginUserRequestVO;
import com.zhaojj11.clockwork.entity.vo.response.LoginUserResponseVO;
import com.zhaojj11.clockwork.service.user.UserService;
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
        return ApiResponse.ok(LoginUserResponseVO.builder().token(jwt).build());
    }
}
