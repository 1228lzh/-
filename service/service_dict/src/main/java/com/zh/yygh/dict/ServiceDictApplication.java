package com.zh.yygh.dict;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: ZH
 * @Date: 2022/11/15 20:14
 */
@SpringBootApplication
public class ServiceDictApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDictApplication.class,args);
    }
}
