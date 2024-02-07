package com.microservice.resalemanagement.inbound.configuration.security;

import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.services.security.SystemAdministrationSecurityService;
import com.microservice.systemadministration.utils.constants.SystemAdministrationConstants;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@NoArgsConstructor
@Configuration
@EnableWebSecurity
public class ResaleManagementSecurityConfiguration {

    @Autowired
    private SystemAdministrationSecurityService systemAdministrationSecurityService;

    @Bean
    public SecurityFilterChain resaleManagementFilterChain(final HttpSecurity httpSecurity) {
        try {
            httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/api/v1/resale-management/**")
                                .hasAuthority(SystemAdministrationConstants.ADMINISTRATOR_ROLE_NAME)
                                .requestMatchers("/login/**").authenticated();
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
    public UserDetailsService userDetailsService() {
        return systemAdministrationSecurityService.userDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        return systemAdministrationSecurityService.authenticationProvider();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return systemAdministrationSecurityService.passwordEncoder();
    }

    @Bean
    public User defaultAdministratorUserProcess() {
        return systemAdministrationSecurityService.defaultAdministratorUserProcess();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return systemAdministrationSecurityService.roleHierarchy();
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(final RoleHierarchy roleHierarchy) {
        return systemAdministrationSecurityService.methodSecurityExpressionHandler(roleHierarchy);
    }

}
