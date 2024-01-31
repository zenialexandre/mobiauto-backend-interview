package com.microservice.systemadministration.business.services;

import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.mappers.SystemAdministrationMapper;
import com.microservice.systemadministration.business.repositories.UserRepository;
import com.microservice.systemadministration.business.vo.UserVO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class SystemAdministrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SystemAdministrationMapper administrationMapper;

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
