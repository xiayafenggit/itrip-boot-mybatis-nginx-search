package com.boot.itripbootmybatisnginxsearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.po","com.service","com.controller","com.util"})
@MapperScan(basePackages = {"com.mapper"})
public class ItripBootMybatisNginxSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItripBootMybatisNginxSearchApplication.class, args);
    }

}
