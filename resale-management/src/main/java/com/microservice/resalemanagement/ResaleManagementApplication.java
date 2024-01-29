package com.microservice.resalemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "business.entities", "business.mappers",
        "business.repositories", "business.vo",
        "inbound.controller", "inbound.configuration"
})
public class ResaleManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResaleManagementApplication.class, args);
    }

}