package com.microservice.systemadministration.inbound.controller;

import com.microservice.systemadministration.business.services.SystemAdministrationService;
import com.microservice.systemadministration.business.vo.UserVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/system-administration")
@Tag(
        name = "System Administration",
        description = "API that handles the requests to the system-administration microservice."
)
public class SystemAdministrationController {

    @Autowired
    private SystemAdministrationService systemAdministrationService;

    @GetMapping(
            path = "/{userSequenceId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> getUserBySequenceId(final @PathVariable("userSequenceId") Integer userSequenceId) {
        return systemAdministrationService.getUserBySequenceId(userSequenceId);
    }

    @PostMapping(
            path = "/create-user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> createUser(final @Valid @RequestBody UserVO userVO) {
        return systemAdministrationService.createUser(userVO);
    }

    @DeleteMapping(
            path = "/delete-user/{userSequenceId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteUser(final @PathVariable("userSequenceId") Integer userSequenceId) {
        return systemAdministrationService.deleteUser(userSequenceId);
    }

}
