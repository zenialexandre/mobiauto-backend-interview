package com.microservice.systemadministration.business.services.security;

import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.repositories.RoleRepository;
import com.microservice.systemadministration.business.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class SystemAdministrationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        try {
            final User user = userRepository.findByEmail(email).orElseThrow(() ->
                    new UsernameNotFoundException("User not found by the email provided.")
            );
            return new SystemAdministrationUserDetails(roleRepository, user);
        } catch (final Exception exception) {
            throw new UsernameNotFoundException(exception.getMessage());
        }
    }

}
