package com.microservice.systemadministration.business.mappers;

import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.vo.UserVO;
import com.microservice.systemadministration.business.vo.enums.UserRoleEnum;
import com.microservice.systemadministration.inbound.configuration.security.SystemAdministrationSecurityConfiguration;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class SystemAdministrationMapper {

    @Autowired
    private SystemAdministrationSecurityConfiguration systemAdministrationSecurityConfiguration;

    public User map(final UserVO userVO) {
        return User.builder()
                .userName(userVO.getUserName())
                .email(userVO.getEmail())
                .password(systemAdministrationSecurityConfiguration.passwordEncoder().encode(userVO.getPassword()))
                .userRole(UserRoleEnum.valueOf(userVO.getUserRole()))
                .build();
    }

}
