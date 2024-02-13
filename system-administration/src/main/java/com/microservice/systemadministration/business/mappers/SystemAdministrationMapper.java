package com.microservice.systemadministration.business.mappers;

import com.microservice.systemadministration.business.entities.Profile;
import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.services.SystemAdministrationService;
import com.microservice.systemadministration.business.vo.ProfileVO;
import com.microservice.systemadministration.business.vo.UserVO;
import com.microservice.systemadministration.inbound.configuration.security.SystemAdministrationSecurityConfiguration;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class SystemAdministrationMapper {

    public User map(final SystemAdministrationSecurityConfiguration systemAdministrationSecurityConfiguration,
                    final UserVO userVO) {
        return User.builder()
                .userName(userVO.getUserName())
                .email(userVO.getEmail())
                .password(systemAdministrationSecurityConfiguration.passwordEncoder().encode(userVO.getPassword()))
                .profiles(userVO.getProfiles())
                .build();
    }

    public Profile map(final SystemAdministrationService systemAdministrationService,
                       final ProfileVO profileVO) {
        return Profile.builder()
                .profileName(profileVO.getProfileName())
                .userSequenceId(profileVO.getUserSequenceId())
                .profileRole(systemAdministrationService.getRoleByName(profileVO.getProfileRole().name()))
                .build();
    }

}
