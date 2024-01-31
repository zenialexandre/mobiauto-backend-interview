package com.microservice.administration.business.services;

import com.microservice.administration.business.entities.User;
import com.microservice.administration.business.mappers.AdministrationMapper;
import com.microservice.administration.business.repositories.UserRepository;
import com.microservice.administration.business.vo.UserVO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class AdministrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdministrationMapper administrationMapper;

    public ResponseEntity<?> createUser(final UserVO userVO) {
        try {
            final User user = administrationMapper.map(userVO);
            userRepository.saveAndFlush(user);
            return ResponseEntity.ok(HttpStatus.CREATED).ok(user);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
