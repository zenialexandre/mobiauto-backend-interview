package com.microservice.resalemanagement.inbound.controller;

import com.microservice.resalemanagement.business.entities.Resale;
import com.microservice.resalemanagement.business.mappers.ResaleManagementMapper;
import com.microservice.resalemanagement.business.repositories.ResaleManagementRepository;
import com.microservice.resalemanagement.business.vo.ResaleVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/resale-management")
public class ResaleManagementController {

    @Autowired
    private ResaleManagementRepository resaleManagementRepository;

    @Autowired
    private ResaleManagementMapper resaleManagementMapper;

    @PostMapping("/create-resale")
    @ResponseBody
    public ResponseEntity<Resale> createResale(final @RequestBody ResaleVO resaleVO) {
        try {
            final Resale resale = resaleManagementMapper.map(resaleVO);
            resaleManagementRepository.saveAndFlush(resale);
            return ResponseEntity.ok(resale);
        } catch (final Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}
