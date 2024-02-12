package com.microservice.resalemanagement.business.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.resalemanagement.business.entities.Resale;
import com.microservice.resalemanagement.business.mappers.ResaleManagementMapper;
import com.microservice.resalemanagement.business.repositories.ResaleRepository;
import com.microservice.resalemanagement.business.vo.ResaleVO;
import com.microservice.resalemanagement.utils.exception.ResaleNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ResaleManagementService {

    @Autowired
    private ResaleRepository resaleRepository;

    @Autowired
    private ResaleManagementMapper resaleManagementMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<?> getResaleBySequenceId(final Integer resaleSequenceId) {
        try {
            final Resale resale = resaleRepository.findById(resaleSequenceId).orElseThrow(() ->
                    new ResaleNotFoundException(resaleSequenceId)
            );
            return new ResponseEntity<>(objectMapper.writeValueAsString(resale), HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createResale(final ResaleVO resaleVO) {
        try {
            final Resale resale = resaleManagementMapper.map(resaleVO);

            resaleRepository.saveAndFlush(resale);
            ResponseEntity.ok(HttpStatus.CREATED);
            return ResponseEntity.ok(resale);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteResale(final Integer resaleSequenceId) {
        try {
            final Resale resale = resaleRepository.findById(resaleSequenceId).orElseThrow(() ->
                    new ResaleNotFoundException(resaleSequenceId)
            );

            resaleRepository.delete(resale);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
