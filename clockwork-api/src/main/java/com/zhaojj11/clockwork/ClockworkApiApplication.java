package com.zhaojj11.clockwork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动器
 *
 * @author zhaojj11
 */
@SpringBootApplication
@MapperScan("com.zhaojj11.clockwork.domain")
public class ClockworkApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClockworkApiApplication.class, args);
    }

}
