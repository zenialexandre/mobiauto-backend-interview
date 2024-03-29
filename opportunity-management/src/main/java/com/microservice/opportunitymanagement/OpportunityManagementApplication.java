package com.microservice.opportunitymanagement;

import com.microservice.opportunitymanagement.business.entities.Opportunity;
import com.microservice.opportunitymanagement.business.mappers.OpportunityManagementMapper;
import com.microservice.opportunitymanagement.business.repositories.OpportunityRepository;
import com.microservice.opportunitymanagement.business.services.OpportunityManagementService;
import com.microservice.opportunitymanagement.inbound.configuration.security.OpportunityManagementSecurityConfiguration;
import com.microservice.opportunitymanagement.inbound.controller.OpportunityManagementController;
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
        OpportunityManagementController.class,
        OpportunityManagementMapper.class,
        OpportunityManagementService.class,
        SystemAdministrationSecurityService.class,
        SystemAdministrationService.class,
        SystemAdministrationMapper.class,
        SystemAdministrationSecurityConfiguration.class
})
@ConfigurationPropertiesScan(basePackageClasses = {
        OpportunityManagementSecurityConfiguration.class,
        SystemAdministrationSecurityConfiguration.class
})
@EntityScan(basePackageClasses = {
        Opportunity.class,
        User.class
})
@EnableJpaRepositories(basePackageClasses = {
        OpportunityRepository.class,
        UserRepository.class
})
@SpringBootApplication
public class OpportunityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpportunityManagementApplication.class, args);
    }

}
