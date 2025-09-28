package com.elleined.philippine_location_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PhilippineLocationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhilippineLocationApiApplication.class, args);
    }
}
