package com.microservice.resalemanagement.business.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.resalemanagement.business.entities.Resale;
import com.microservice.resalemanagement.business.mappers.ResaleManagementMapper;
import com.microservice.resalemanagement.business.repositories.ResaleManagementRepository;
import com.microservice.resalemanagement.business.vo.ResaleVO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ResaleManagementService {

    @Autowired
    private ResaleManagementRepository resaleManagementRepository;

    @Autowired
    private ResaleManagementMapper resaleManagementMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<?> getResaleByResaleSequenceId(final Integer resaleSequenceId) {
        try {
            final Resale resale = resaleManagementRepository.findById(resaleSequenceId).get();
            return new ResponseEntity<>(objectMapper.writeValueAsString(resale), HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createResale(final ResaleVO resaleVO) {
        try {
            final Resale resale = resaleManagementMapper.map(resaleVO);
            resaleManagementRepository.saveAndFlush(resale);
            return ResponseEntity.ok(HttpStatus.CREATED).ok(resale);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteResale(final Integer resaleSequenceId) {
        try {
            final Resale resale = resaleManagementRepository.findById(resaleSequenceId).get();
            resaleManagementRepository.delete(resale);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
