package com.example.movieRecoSys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.movieRecoSys")
public class MovieRecoSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRecoSysApplication.class, args);
    }

}
