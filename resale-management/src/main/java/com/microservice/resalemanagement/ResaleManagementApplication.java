package com.microservice.resalemanagement;

import com.microservice.resalemanagement.business.entities.Resale;
import com.microservice.resalemanagement.business.mappers.ResaleManagementMapper;
import com.microservice.resalemanagement.business.repositories.ResaleManagementRepository;
import com.microservice.resalemanagement.business.services.ResaleManagementService;
import com.microservice.resalemanagement.inbound.configuration.security.ResaleManagementSecurityConfiguration;
import com.microservice.resalemanagement.inbound.controller.ResaleManagementController;
import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.mappers.SystemAdministrationMapper;
import com.microservice.systemadministration.business.repositories.UserRepository;
import com.microservice.systemadministration.business.services.SystemAdministrationService;
import com.microservice.systemadministration.business.services.security.SystemAdministrationSecurityService;
import com.microservice.systemadministration.inbound.configuration.security.SystemAdministrationSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
        ResaleManagementController.class,
        ResaleManagementMapper.class,
        ResaleManagementService.class,
        SystemAdministrationSecurityService.class,
        SystemAdministrationService.class,
        SystemAdministrationMapper.class,
        SystemAdministrationSecurityConfiguration.class
})
@ConfigurationPropertiesScan(basePackageClasses = {
        ResaleManagementSecurityConfiguration.class,
        SystemAdministrationSecurityConfiguration.class
})
@EntityScan(basePackageClasses = {
        Resale.class,
        User.class
})
@EnableJpaRepositories(basePackageClasses = {
        ResaleManagementRepository.class,
        UserRepository.class
})
public class ResaleManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResaleManagementApplication.class, args);
    }

}
