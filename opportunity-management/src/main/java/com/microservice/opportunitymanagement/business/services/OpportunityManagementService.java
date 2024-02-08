package com.microservice.opportunitymanagement.business.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.opportunitymanagement.business.entities.Opportunity;
import com.microservice.opportunitymanagement.business.mappers.OpportunityManagementMapper;
import com.microservice.opportunitymanagement.business.repositories.OpportunityRepository;
import com.microservice.opportunitymanagement.business.vo.CreateOpportunityVO;
import com.microservice.opportunitymanagement.utils.exception.OpportunityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class OpportunityManagementService {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private OpportunityManagementMapper opportunityManagementMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<?> getOpportunityBySequenceId(final Integer opportunitySequenceId) {
        try {
            final Opportunity opportunity = opportunityRepository.findById(opportunitySequenceId).orElseThrow(() ->
                new OpportunityNotFoundException(opportunitySequenceId)
            );
            return new ResponseEntity<>(objectMapper.writeValueAsString(opportunity), HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createOpportunity(final CreateOpportunityVO createOpportunityVO) {
        try {
            final Opportunity opportunity = opportunityManagementMapper.map(createOpportunityVO);
            opportunityRepository.saveAndFlush(opportunity);
            ResponseEntity.ok(HttpStatus.CREATED);
            return ResponseEntity.ok(opportunity);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteOpportunity(final Integer opportunitySequenceId) {
        try {
            final Opportunity opportunity = opportunityRepository.findById(opportunitySequenceId).orElseThrow(() ->
                    new OpportunityNotFoundException(opportunitySequenceId)
            );
            opportunityRepository.delete(opportunity);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
