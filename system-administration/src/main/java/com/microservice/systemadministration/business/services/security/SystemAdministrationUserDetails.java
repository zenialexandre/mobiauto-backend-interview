package com.microservice.systemadministration.business.services.security;

import com.microservice.systemadministration.business.entities.Profile;
import com.microservice.systemadministration.business.entities.Role;
import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.repositories.RoleRepository;
import com.microservice.systemadministration.utils.exception.RoleNotFoundByIdException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class SystemAdministrationUserDetails implements UserDetails {

    private RoleRepository roleRepository;
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<Profile> profiles = user.getProfiles();
        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (final Profile profile : profiles) {
            final Integer roleSequenceId = profile.getRoleSequenceId();
            final Role role = roleRepository.findById(roleSequenceId).orElseThrow(() ->
                    new RoleNotFoundByIdException(roleSequenceId)
            );
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
