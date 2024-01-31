package com.microservice.administration.business.mappers;

import com.microservice.administration.business.entities.User;
import com.microservice.administration.business.vo.UserVO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class AdministrationMapper {

    public User map(final UserVO userVO) {
        return User.builder()
                .userName(userVO.getUserName())
                .email(userVO.getEmail())
                .password(userVO.getPassword())
                .build();
    }

}
