package com.ledao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LeDao
 */
@SpringBootApplication
@MapperScan("com.ledao.mapper")
public class InformationsharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(InformationsharingApplication.class, args);
    }

}
