package com.lzh.jmeter.system;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.lzh.jmeter.system.mapper"})
@EnableCaching // 开启缓存功能
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

