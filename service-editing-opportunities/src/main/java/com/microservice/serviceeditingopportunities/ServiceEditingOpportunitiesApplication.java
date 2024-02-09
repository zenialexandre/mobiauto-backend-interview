package com.microservice.serviceeditingopportunities;

import com.microservice.serviceeditingopportunities.business.entities.OpportunityService;
import com.microservice.serviceeditingopportunities.business.mappers.ServiceEditingOpportunitiesMapper;
import com.microservice.serviceeditingopportunities.business.repositories.OpportunityServiceRepository;
import com.microservice.serviceeditingopportunities.business.services.ServiceEditingOpportunitiesService;
import com.microservice.serviceeditingopportunities.inbound.configuration.security.ServiceEditingOpportunitiesSecurityConfiguration;
import com.microservice.serviceeditingopportunities.inbound.controller.ServiceEditingOpportunitiesController;
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
        ServiceEditingOpportunitiesController.class,
        ServiceEditingOpportunitiesMapper.class,
        ServiceEditingOpportunitiesService.class,
        SystemAdministrationSecurityService.class,
        SystemAdministrationService.class,
        SystemAdministrationMapper.class,
        SystemAdministrationSecurityConfiguration.class
})
@ConfigurationPropertiesScan(basePackageClasses = {
        ServiceEditingOpportunitiesSecurityConfiguration.class,
        SystemAdministrationSecurityConfiguration.class
})
@EntityScan(basePackageClasses = {
        OpportunityService.class,
        User.class
})
@EnableJpaRepositories(basePackageClasses = {
        OpportunityServiceRepository.class,
        UserRepository.class
})
@SpringBootApplication
public class ServiceEditingOpportunitiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEditingOpportunitiesApplication.class, args);
    }

}
