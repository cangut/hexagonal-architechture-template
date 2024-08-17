package com.architecture.can.adapters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "com.architecture.can.entity")
@ComponentScan(basePackages = "com.architecture.can")
public class HexagonalTemplate {
    public static void main(String[] args) {
        SpringApplication.run(HexagonalTemplate.class, args);
    }
}
