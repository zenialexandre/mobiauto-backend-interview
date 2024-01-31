package com.microservice.administration;

import com.microservice.administration.business.entities.User;
import com.microservice.administration.business.mappers.AdministrationMapper;
import com.microservice.administration.business.repositories.UserRepository;
import com.microservice.administration.business.services.AdministrationService;
import com.microservice.administration.inbound.controller.AdministrationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
        AdministrationController.class,
        AdministrationMapper.class,
        AdministrationService.class
})
@EntityScan(basePackageClasses = User.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class AdministrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdministrationApplication.class, args);
    }

}
