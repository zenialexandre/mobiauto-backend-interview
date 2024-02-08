package com.microservice.storemanagement.business.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.storemanagement.business.entities.Store;
import com.microservice.storemanagement.business.mappers.StoreManagementMapper;
import com.microservice.storemanagement.business.repositories.StoreRepository;
import com.microservice.storemanagement.business.vo.StoreVO;
import com.microservice.storemanagement.utils.exception.StoreNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class StoreManagementService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreManagementMapper storeManagementMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<?> getStoreBySequenceId(final Integer storeSequenceId) {
        try {
            final Store store = storeRepository.findById(storeSequenceId).orElseThrow(() ->
                    new StoreNotFoundException(storeSequenceId)
            );
            return new ResponseEntity<>(objectMapper.writeValueAsString(store), HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createStore(final StoreVO storeVO) {
        try {
            final Store store = storeManagementMapper.map(storeVO);
            storeRepository.saveAndFlush(store);
            ResponseEntity.ok(HttpStatus.CREATED);
            return ResponseEntity.ok(store);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteStore(final Integer storeSequenceId) {
        try {
            final Store store = storeRepository.findById(storeSequenceId).orElseThrow(() ->
                    new StoreNotFoundException(storeSequenceId)
            );
            storeRepository.delete(store);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
