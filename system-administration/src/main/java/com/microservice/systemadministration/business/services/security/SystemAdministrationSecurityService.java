package com.microservice.systemadministration.business.services.security;

import com.google.common.collect.ImmutableList;
import com.microservice.systemadministration.business.entities.Profile;
import com.microservice.systemadministration.business.entities.Role;
import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.repositories.RoleRepository;
import com.microservice.systemadministration.business.repositories.UserRepository;
import com.microservice.systemadministration.business.services.SystemAdministrationService;
import com.microservice.systemadministration.utils.constants.SystemAdministrationConstants;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@NoArgsConstructor
@Service
public class SystemAdministrationSecurityService {

    @Autowired
    private SystemAdministrationUserDetailsService systemAdministrationUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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

    public UserDetailsService userDetailsService() {
        return new SystemAdministrationUserDetailsService();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Role defaultRolesOnStartUp() {
        try {
            return roleRepository.findRoleByName(SystemAdministrationConstants.ADMINISTRATOR_ROLE_NAME)
                    .orElseGet(this::createDefaultRolesOnStartUp);
        } catch (final Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    protected Role createDefaultRolesOnStartUp() {
        final Role administratorRole = Role.builder().roleName(SystemAdministrationConstants.ADMINISTRATOR_ROLE_NAME).build();
        final Role ownerRole = Role.builder().roleName(SystemAdministrationConstants.OWNER_ROLE_NAME).build();
        final Role managerRole = Role.builder().roleName(SystemAdministrationConstants.MANAGER_ROLE_NAME).build();
        final Role assistantRole = Role.builder().roleName(SystemAdministrationConstants.ASSISTANT_ROLE_NAME).build();
        roleRepository.saveAllAndFlush(ImmutableList.of(administratorRole, ownerRole, managerRole, assistantRole));
        return administratorRole;
    }

    public User defaultAdministratorUserProcess(final SystemAdministrationService systemAdministrationService) {
        try {
            return userRepository.findByEmail(SystemAdministrationConstants.DEFAULT_ADMINISTRATOR_USER_EMAIL)
                    .orElseGet(() -> createDefaultAdministratorUser(systemAdministrationService));
        } catch (final Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    protected User createDefaultAdministratorUser(final SystemAdministrationService systemAdministrationService) {
        final Profile defaultAdminsitratorProfile = Profile.builder()
                .profileName("Unique Administrator Profile")
                .profileRole(systemAdministrationService.getRoleByName(SystemAdministrationConstants.ADMINISTRATOR_ROLE_NAME))
                .build();
        final User defaultAdminsitratorUser = User.builder()
                .userName("admin")
                .email(SystemAdministrationConstants.DEFAULT_ADMINISTRATOR_USER_EMAIL)
                .password(passwordEncoder().encode("admin"))
                .profiles(Set.of(defaultAdminsitratorProfile))
                .build();
        userRepository.saveAndFlush(defaultAdminsitratorUser);
        return defaultAdminsitratorUser;
    }

    public RoleHierarchy roleHierarchy() {
        final RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(
                "ROLE_ADMINISTRATOR > ROLE_OWNER > ROLE_MANAGER > ROLE_ASSISTANT"
        );
        return roleHierarchy;
    }

    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(final RoleHierarchy roleHierarchy) {
        final DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler =
                new DefaultMethodSecurityExpressionHandler();
        defaultMethodSecurityExpressionHandler.setRoleHierarchy(roleHierarchy);
        return defaultMethodSecurityExpressionHandler;
    }

}
