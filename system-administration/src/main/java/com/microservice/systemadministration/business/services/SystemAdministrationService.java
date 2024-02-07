package com.microservice.systemadministration.business.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.systemadministration.business.entities.Role;
import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.mappers.SystemAdministrationMapper;
import com.microservice.systemadministration.business.repositories.RoleRepository;
import com.microservice.systemadministration.business.repositories.UserRepository;
import com.microservice.systemadministration.business.vo.UserVO;
import com.microservice.systemadministration.inbound.configuration.security.SystemAdministrationSecurityConfiguration;
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
    private RoleRepository roleRepository;

    @Autowired
    private SystemAdministrationMapper systemAdministrationMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<?> getUserBySequenceId(final Integer userSequenceId) {
        try {
            final User resale = userRepository.findById(userSequenceId).get();
            return new ResponseEntity<>(objectMapper.writeValueAsString(resale), HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createUser(final SystemAdministrationSecurityConfiguration systemAdministrationSecurityConfiguration, final UserVO userVO) {
        try {
            final User user = systemAdministrationMapper.map(this, systemAdministrationSecurityConfiguration, userVO);
            userRepository.saveAndFlush(user);
            ResponseEntity.ok(HttpStatus.CREATED);
            return ResponseEntity.ok(user);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteUser(final Integer userSequenceId) {
        try {
            final User user = userRepository.findById(userSequenceId).get();
            userRepository.delete(user);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public Role getRoleByName(final String roleName) {
        return roleRepository.findRoleByName(roleName).get();
    }

}
