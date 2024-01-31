package com.microservice.resalemanagement.inbound.controller;

import com.microservice.resalemanagement.business.services.ResaleManagementService;
import com.microservice.resalemanagement.business.vo.ResaleVO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/resale-management")
public class ResaleManagementController {

    @Autowired
    private ResaleManagementService resaleManagementService;

    @GetMapping(
            path = "/{resaleSequenceId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> getResaleByResaleSequenceId(final @PathVariable("resaleSequenceId") Integer resaleSequenceId) {
        return resaleManagementService.getResaleByResaleSequenceId(resaleSequenceId);
    }

    @PostMapping(
            path = "/create-resale",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> createResale(final @Valid @RequestBody ResaleVO resaleVO) {
        return resaleManagementService.createResale(resaleVO);
    }

    @DeleteMapping(
            path = "/delete-resale/{resaleSequenceId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteResale(final @PathVariable("resaleSequenceId") Integer resaleSequenceId) {
        return resaleManagementService.deleteResale(resaleSequenceId);
    }

}
