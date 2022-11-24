package com.zhaojj11.clockwork.configuration.handler;


import com.zhaojj.clockwork.common.exception.BaseException;
import com.zhaojj.clockwork.common.model.ApiResponse;
import com.zhaojj11.clockwork.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author zhaojj11
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserException.class})
    public ApiResponse<Object> baseException(UserException e) {
        log.error("BaseException:", e);
        return ApiResponse.fail(e.getMessage());
    }

    @ExceptionHandler({BaseException.class})
    public ApiResponse<Object> baseException(BaseException e) {
        log.error("BaseException:", e);
        return ApiResponse.fail("bad request");
    }
}
