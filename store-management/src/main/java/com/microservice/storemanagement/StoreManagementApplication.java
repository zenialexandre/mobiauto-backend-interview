package com.microservice.storemanagement;

import com.microservice.opportunitymanagement.business.entities.Opportunity;
import com.microservice.storemanagement.business.entities.Store;
import com.microservice.storemanagement.business.mappers.StoreManagementMapper;
import com.microservice.storemanagement.business.repositories.StoreRepository;
import com.microservice.storemanagement.business.services.StoreManagementService;
import com.microservice.storemanagement.inbound.configuration.security.StoreManagementSecurityConfiguration;
import com.microservice.storemanagement.inbound.controller.StoreManagementController;
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

@ComponentScan(basePackageClasses = {
        StoreManagementController.class,
        StoreManagementService.class,
        StoreManagementMapper.class,
        SystemAdministrationSecurityService.class,
        SystemAdministrationService.class,
        SystemAdministrationMapper.class,
        SystemAdministrationSecurityConfiguration.class
})
@ConfigurationPropertiesScan(basePackageClasses = {
        StoreManagementSecurityConfiguration.class,
        SystemAdministrationSecurityConfiguration.class
})
@EntityScan(basePackageClasses = {
        Store.class,
        Opportunity.class,
        User.class
})
@EnableJpaRepositories(basePackageClasses = {
        StoreRepository.class,
        UserRepository.class
})
@SpringBootApplication
public class StoreManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

}
