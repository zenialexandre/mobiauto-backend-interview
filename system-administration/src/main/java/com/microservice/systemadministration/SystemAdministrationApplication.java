package com.microservice.systemadministration;

import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.mappers.SystemAdministrationMapper;
import com.microservice.systemadministration.business.repositories.UserRepository;
import com.microservice.systemadministration.business.services.SystemAdministrationService;
import com.microservice.systemadministration.inbound.controller.SystemAdministrationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
        SystemAdministrationController.class,
        SystemAdministrationMapper.class,
        SystemAdministrationService.class
})
@EntityScan(basePackageClasses = User.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SystemAdministrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemAdministrationApplication.class, args);
    }

}
