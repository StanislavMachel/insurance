package com.example.insurance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsuranceApp {

    private static Logger LOG = LoggerFactory.getLogger(InsuranceApp.class);

    public static void main(String[] args) {
        SpringApplication.run(InsuranceApp.class, args);
    }
}
