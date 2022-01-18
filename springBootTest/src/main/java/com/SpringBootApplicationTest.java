package com;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.mapper"})
public class SpringBootApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationTest.class, args);
    }
}
