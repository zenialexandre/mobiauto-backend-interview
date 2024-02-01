package com.microservice.resalemanagement;

import com.microservice.resalemanagement.business.entities.Resale;
import com.microservice.resalemanagement.business.mappers.ResaleManagementMapper;
import com.microservice.resalemanagement.business.repositories.ResaleManagementRepository;
import com.microservice.resalemanagement.business.services.ResaleManagementService;
import com.microservice.resalemanagement.inbound.controller.ResaleManagementController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
        ResaleManagementController.class,
        ResaleManagementMapper.class,
        ResaleManagementService.class
})
@EntityScan(basePackageClasses = Resale.class)
@EnableJpaRepositories(basePackageClasses = ResaleManagementRepository.class)
public class ResaleManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResaleManagementApplication.class, args);
    }

}
