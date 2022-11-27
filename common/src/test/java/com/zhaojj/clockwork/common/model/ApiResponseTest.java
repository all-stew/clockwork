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
        ApiResponse<Object> ok = ApiResponse.fail("fail");
        Assertions.assertEquals(ApiResultCodeEnum.FAILED.getCode(), ok.getCode());
        Assertions.assertFalse(ok.isSuccess());
        Assertions.assertEquals("fail", ok.getMsg());
    }
}