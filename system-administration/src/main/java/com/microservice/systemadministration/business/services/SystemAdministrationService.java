package com.microservice.systemadministration.business.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.systemadministration.business.entities.Profile;
import com.microservice.systemadministration.business.entities.Role;
import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.mappers.SystemAdministrationMapper;
import com.microservice.systemadministration.business.repositories.ProfileRepository;
import com.microservice.systemadministration.business.repositories.RoleRepository;
import com.microservice.systemadministration.business.repositories.UserRepository;
import com.microservice.systemadministration.business.vo.ProfileVO;
import com.microservice.systemadministration.business.vo.UserVO;
import com.microservice.systemadministration.inbound.configuration.security.SystemAdministrationSecurityConfiguration;
import com.microservice.systemadministration.utils.exception.ProfileNotFoundByNameException;
import com.microservice.systemadministration.utils.exception.ProfileNotFoundException;
import com.microservice.systemadministration.utils.exception.RoleNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class SystemAdministrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SystemAdministrationMapper systemAdministrationMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<?> getUserBySequenceId(final Integer userSequenceId) {
        try {
            final User user = userRepository.findById(userSequenceId).orElseThrow(() ->
                    new UsernameNotFoundException("User not found by the sequence provided.")
            );
            return new ResponseEntity<>(objectMapper.writeValueAsString(user), HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getProfileBySequenceId(final Integer profileSequenceId) {
        try {
            final Profile profile = profileRepository.findById(profileSequenceId).orElseThrow(() ->
                    new ProfileNotFoundException(profileSequenceId)
            );
            return new ResponseEntity<>(objectMapper.writeValueAsString(profile), HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createUser(final SystemAdministrationSecurityConfiguration systemAdministrationSecurityConfiguration,
                                        final UserVO userVO) {
        try {
            final User user = systemAdministrationMapper.map(this, systemAdministrationSecurityConfiguration, userVO);
            userRepository.saveAndFlush(user);
            ResponseEntity.ok(HttpStatus.CREATED);
            return ResponseEntity.ok(user);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> createProfile(final ProfileVO profileVO) {
        try {
            final Profile profile = systemAdministrationMapper.map(this, profileVO);
            profileRepository.saveAndFlush(profile);
            ResponseEntity.ok(HttpStatus.CREATED);
            return ResponseEntity.ok(profile);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteUser(final Integer userSequenceId) {
        try {
            final User user = userRepository.findById(userSequenceId).orElseThrow(() ->
                    new UsernameNotFoundException("User not found by the sequence provided.")
            );
            userRepository.delete(user);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteProfile(final Integer profileSequenceId) {
        try {
            final Profile profile = profileRepository.findById(profileSequenceId).orElseThrow(() ->
                    new ProfileNotFoundException(profileSequenceId)
            );
            profileRepository.delete(profile);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public Profile getProfileByName(final String profileName) {
        return profileRepository.findProfileByName(profileName).orElseThrow(() ->
                new ProfileNotFoundByNameException(profileName)
        );
    }

    public Role getRoleByName(final String roleName) {
        return roleRepository.findRoleByName(roleName).orElseThrow(() ->
                new RoleNotFoundException(roleName)
        );
    }

}
