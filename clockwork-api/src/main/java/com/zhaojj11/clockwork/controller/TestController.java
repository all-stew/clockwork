package com.zhaojj11.clockwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @author zhaojj11
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/timeFormat")
    public Map<String, Date> testTimeFormat() {
        return Map.of("time", new Date());
    }
}
