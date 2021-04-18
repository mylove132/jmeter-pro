package com.lzh.jmeter.system.main.bootstrap;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:08:41
 * Modify date: 2021-04-18:08:41
 */
@SpringBootApplication
@EnableDubbo
public class JmeterSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmeterSystemApplication.class, args);
    }
}
