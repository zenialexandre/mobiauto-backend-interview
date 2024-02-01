package com.microservice.systemadministration.inbound.configuration.security;

import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.repositories.UserRepository;
import com.microservice.systemadministration.business.services.security.SystemAdministrationUserDetailsService;
import com.microservice.systemadministration.business.vo.enums.UserRoleEnum;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@NoArgsConstructor
@Configuration
@EnableWebSecurity
public class SystemAdministrationSecurityConfiguration {

    @Autowired
    private SystemAdministrationUserDetailsService systemAdministrationUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity httpSecurity) {
        try {
            httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                authorizationManagerRequestMatcherRegistry
                        .requestMatchers(HttpMethod.POST).hasRole(UserRoleEnum.ADMINISTRATOR.name())
                        .requestMatchers(HttpMethod.DELETE).hasRole(UserRoleEnum.ADMINISTRATOR.name())
                        .requestMatchers("/api/v1/system-administration/**")
                            .hasAnyRole(UserRoleEnum.ADMINISTRATOR.name(), UserRoleEnum.OWNER.name())
                        .requestMatchers("/login/**").permitAll()
                        .anyRequest().authenticated();
            }).httpBasic(Customizer.withDefaults())
                    .sessionManagement(httpSecuritySessionManagementConfigurer -> {
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                    });

            return httpSecurity.build();
        } catch (final Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        try {
            final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(systemAdministrationUserDetailsService);
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            return daoAuthenticationProvider;
        } catch (final Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public User defaultAdministratorUserProcess() {
        try {
            return userRepository.findByEmail("admin@gmail.com").orElseGet(this::createDefaultAdministratorUser);
        } catch (final Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    protected User createDefaultAdministratorUser() {
        final User defaultAdminsitratorUser = User.builder()
                .userName("admin")
                .email("admin@gmail.com")
                .password(passwordEncoder().encode("admin"))
                .userRole(UserRoleEnum.ADMINISTRATOR)
                .build();
        userRepository.saveAndFlush(defaultAdminsitratorUser);
        return defaultAdminsitratorUser;
    }

}
