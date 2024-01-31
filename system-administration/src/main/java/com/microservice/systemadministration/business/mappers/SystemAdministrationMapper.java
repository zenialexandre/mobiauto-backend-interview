package com.microservice.systemadministration.business.mappers;

import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.vo.UserVO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class SystemAdministrationMapper {

    public User map(final UserVO userVO) {
        return User.builder()
                .userName(userVO.getUserName())
                .email(userVO.getEmail())
                .password(userVO.getPassword())
                .build();
    }

}
