package com.zhaojj.clockwork.common.model;

import com.zhaojj.clockwork.common.enums.ApiResultCodeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ApiResponseTest {
    @Test
    void testOK() {
        ApiResponse<Object> ok = ApiResponse.ok();
        Assertions.assertEquals(ApiResultCodeEnum.SUCCESS.getCode(), ok.getCode());
        Assertions.assertTrue(ok.isSuccess());

        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        ApiResponse<ArrayList<Integer>> okWithData = ApiResponse.ok(integerList);
        Assertions.assertEquals(ApiResultCodeEnum.SUCCESS.getCode(), okWithData.getCode());
        Assertions.assertTrue(okWithData.isSuccess());
        Assertions.assertEquals(ArrayList.class, okWithData.getData().getClass());
        Assertions.assertEquals(1, okWithData.getData().size());
    }

    @Test
    void testFail() {
        ApiResponse<Object> fail = ApiResponse.fail("fail");
        Assertions.assertEquals(ApiResultCodeEnum.FAILED.getCode(), fail.getCode());
        Assertions.assertFalse(fail.isSuccess());
        Assertions.assertEquals("fail", fail.getMsg());

        ApiResponse<Object> failWithCode = ApiResponse.fail(401, "fail");
        Assertions.assertFalse(failWithCode.isSuccess());
        Assertions.assertEquals("fail", failWithCode.getMsg());
        Assertions.assertEquals(401, failWithCode.getCode());
    }

    @Test
    void testBuild() {
        ApiResponse<ArrayList<Integer>> apiResponse = new ApiResponse<>();
        apiResponse.setMsg("hello");
        apiResponse.setCode(300);
        apiResponse.setSuccess(true);
        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        apiResponse.setData(integerList);
        Assertions.assertEquals(300, apiResponse.getCode());
        Assertions.assertTrue(apiResponse.isSuccess());
        Assertions.assertEquals(ArrayList.class, apiResponse.getData().getClass());
        Assertions.assertEquals(1, apiResponse.getData().size());
    }
}