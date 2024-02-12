package com.microservice.storemanagement.business.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.opportunitymanagement.business.entities.Opportunity;
import com.microservice.opportunitymanagement.business.repositories.OpportunityRepository;
import com.microservice.opportunitymanagement.utils.exception.OpportunityNotFoundException;
import com.microservice.storemanagement.business.entities.Store;
import com.microservice.storemanagement.business.mappers.StoreManagementMapper;
import com.microservice.storemanagement.business.repositories.StoreRepository;
import com.microservice.storemanagement.business.vo.StoreVO;
import com.microservice.storemanagement.utils.exception.StoreNotFoundException;
import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.repositories.UserRepository;
import com.microservice.systemadministration.utils.exception.UserNotFoundException;
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
    private UserRepository userRepository;

    @Autowired
    private OpportunityRepository opportunityRepository;

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

            linkUsersToStore(store, storeVO);
            linkOpportunitiesToStore(store, storeVO);
            storeRepository.saveAndFlush(store);
            ResponseEntity.ok(HttpStatus.CREATED);
            return ResponseEntity.ok(store);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    protected void linkUsersToStore(final Store store, final StoreVO storeVO) {
        storeVO.getStoreUsers().forEach(user -> {
            user.setStoreSequenceId(store.getStoreSequenceId());
        });
    }

    protected void linkOpportunitiesToStore(final Store store, final StoreVO storeVO) {
        storeVO.getStoreOpportunities().forEach(opportunity -> {
            opportunity.setStoreSequenceId(store.getStoreSequenceId());
        });
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

    public ResponseEntity<?> addUserToStore(final Integer storeSequenceId, final Integer userSequenceId) {
        try {
            final Store store = storeRepository.findById(storeSequenceId).orElseThrow(() ->
                    new StoreNotFoundException(storeSequenceId)
            );
            final User user = userRepository.findById(userSequenceId).orElseThrow(() ->
                    new UserNotFoundException(userSequenceId)
            );

            user.setStoreSequenceId(storeSequenceId);
            store.getStoreUsers().add(user);
            storeRepository.saveAndFlush(store);
            userRepository.saveAndFlush(user);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> addOpportunityToStore(final Integer storeSequenceId, final Integer opportunitySequenceId) {
        try {
            final Store store = storeRepository.findById(storeSequenceId).orElseThrow(() ->
                    new StoreNotFoundException(storeSequenceId)
            );
            final Opportunity opportunity = opportunityRepository.findById(opportunitySequenceId).orElseThrow(() ->
                    new OpportunityNotFoundException(opportunitySequenceId)
            );

            opportunity.setStoreSequenceId(storeSequenceId);
            store.getStoreOpportunities().add(opportunity);
            storeRepository.saveAndFlush(store);
            opportunityRepository.saveAndFlush(opportunity);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
