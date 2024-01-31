package com.microservice.systemadministration.inbound.controller;

import com.microservice.systemadministration.business.services.SystemAdministrationService;
import com.microservice.systemadministration.business.vo.UserVO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/administration")
public class SystemAdministrationController {

    @Autowired
    private SystemAdministrationService administrationService;

    @PostMapping(
            path = "/create-user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> createUser(final @Valid @RequestBody UserVO userVO) {
        return administrationService.createUser(userVO);
    }

}
