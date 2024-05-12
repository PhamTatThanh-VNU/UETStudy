package com.example.uetstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.uetstudy.*"})
@EnableJpaRepositories(value = "com.example.uetstudy.library.Repository")
@EntityScan(value = "com.example.uetstudy.library.Model")
public class UetStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(UetStudyApplication.class, args);
    }

}
