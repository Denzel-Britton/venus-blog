package com.example.venusblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.venusblog.controller")
public class VenusBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(VenusBlogApplication.class, args);
    }

}
