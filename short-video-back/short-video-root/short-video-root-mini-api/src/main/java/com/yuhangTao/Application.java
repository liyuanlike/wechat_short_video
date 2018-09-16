package com.yuhangTao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.yuhangTao.mapper")
@ComponentScan(basePackages = {"com.yuhangTao.org.n3r.idworker","com.yuhangTao"})
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
