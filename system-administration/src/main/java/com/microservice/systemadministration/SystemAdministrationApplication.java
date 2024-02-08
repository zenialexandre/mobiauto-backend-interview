package com.microservice.systemadministration;

import com.microservice.systemadministration.business.entities.Profile;
import com.microservice.systemadministration.business.entities.Role;
import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.mappers.SystemAdministrationMapper;
import com.microservice.systemadministration.business.repositories.ProfileRepository;
import com.microservice.systemadministration.business.repositories.RoleRepository;
import com.microservice.systemadministration.business.repositories.UserRepository;
import com.microservice.systemadministration.business.services.SystemAdministrationService;
import com.microservice.systemadministration.business.services.security.SystemAdministrationSecurityService;
import com.microservice.systemadministration.business.services.security.SystemAdministrationUserDetailsService;
import com.microservice.systemadministration.inbound.configuration.security.SystemAdministrationSecurityConfiguration;
import com.microservice.systemadministration.inbound.controller.SystemAdministrationProfileController;
import com.microservice.systemadministration.inbound.controller.SystemAdministrationUserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
        SystemAdministrationUserController.class,
        SystemAdministrationProfileController.class,
        SystemAdministrationMapper.class,
        SystemAdministrationService.class,
        SystemAdministrationSecurityService.class,
        SystemAdministrationUserDetailsService.class,
        SystemAdministrationSecurityConfiguration.class
})
@ConfigurationPropertiesScan(basePackageClasses = SystemAdministrationSecurityConfiguration.class)
@EntityScan(basePackageClasses = {
        User.class,
        Profile.class,
        Role.class
})
@EnableJpaRepositories(basePackageClasses = {
        UserRepository.class,
        ProfileRepository.class,
        RoleRepository.class
})
public class SystemAdministrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemAdministrationApplication.class, args);
    }

}
