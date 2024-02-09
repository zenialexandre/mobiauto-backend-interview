package com.microservice.serviceeditingopportunities.business.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.opportunitymanagement.business.entities.Opportunity;
import com.microservice.opportunitymanagement.business.repositories.OpportunityRepository;
import com.microservice.opportunitymanagement.business.vo.enums.StatusEnum;
import com.microservice.opportunitymanagement.utils.exception.OpportunityNotFoundException;
import com.microservice.serviceeditingopportunities.business.entities.OpportunityService;
import com.microservice.serviceeditingopportunities.business.mappers.ServiceEditingOpportunitiesMapper;
import com.microservice.serviceeditingopportunities.business.repositories.OpportunityServiceRepository;
import com.microservice.serviceeditingopportunities.business.vo.OpportunityServiceVO;
import com.microservice.serviceeditingopportunities.utils.exception.OpportunityServiceNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Set;

@NoArgsConstructor
@Service
public class ServiceEditingOpportunitiesService {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private OpportunityServiceRepository opportunityServiceRepository;

    @Autowired
    private ServiceEditingOpportunitiesMapper serviceEditingOpportunitiesMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Deque<Opportunity> opportunitiesToBeAttendedQueue = new ArrayDeque<>();

    public ResponseEntity<?> getOpportunityServiceBySequenceId(final Integer opportunityServiceSequenceId) {
        try {
            final OpportunityService opportunityService = opportunityServiceRepository.findById(opportunityServiceSequenceId).orElseThrow(() ->
                    new OpportunityServiceNotFoundException(opportunityServiceSequenceId)
            );
            return new ResponseEntity<>(objectMapper.writeValueAsString(opportunityService), HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createOpportunityService(final OpportunityServiceVO opportunityServiceVO) {
        try {
            final OpportunityService opportunityService = serviceEditingOpportunitiesMapper.map(opportunityServiceVO);
            final Integer opportunitySequenceId = opportunityService.getOpportunitySequenceId();
            final Opportunity opportunity = opportunityRepository.findById(opportunitySequenceId).orElseThrow(() ->
                    new OpportunityNotFoundException(opportunitySequenceId)
            );
            opportunity.setStatus(StatusEnum.IN_SERVICE.name());
            opportunityServiceRepository.saveAndFlush(opportunityService);
            opportunityRepository.saveAndFlush(opportunity);
            ResponseEntity.ok(HttpStatus.CREATED);
            return ResponseEntity.ok(opportunityService);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteOpportunityService(final Integer opportunityServiceSequenceId) {
        try {
            final OpportunityService opportunityService = opportunityServiceRepository.findById(opportunityServiceSequenceId).orElseThrow(() ->
                    new OpportunityServiceNotFoundException(opportunityServiceSequenceId)
            );
            opportunityServiceRepository.delete(opportunityService);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> concludeOpportunityService(final Integer opportunityServiceSequenceId) {
        try {
            final OpportunityService opportunityService = opportunityServiceRepository.findById(opportunityServiceSequenceId).orElseThrow(() ->
                    new OpportunityServiceNotFoundException(opportunityServiceSequenceId)
            );
            final Integer opportunitySequenceId = opportunityService.getOpportunitySequenceId();

            final Opportunity opportunity = opportunityRepository.findById(opportunitySequenceId).orElseThrow(() ->
                    new OpportunityNotFoundException(opportunitySequenceId)
            );
            opportunityService.setOpportunityConclusionDate(LocalDateTime.now());
            opportunity.setStatus(StatusEnum.CONCLUDED.name());
            opportunityServiceRepository.saveAndFlush(opportunityService);
            opportunityRepository.saveAndFlush(opportunity);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> modifyOpportunityServiceAssistant(final Integer opportunityServiceSequenceId, final Integer userSequenceId) {
        try {
            final OpportunityService opportunityService = opportunityServiceRepository.findById(opportunityServiceSequenceId).orElseThrow(() ->
                    new OpportunityServiceNotFoundException(opportunityServiceSequenceId)
            );
            opportunityService.setUserSequenceId(userSequenceId);
            opportunityService.setOpportunityAssignmentDate(LocalDateTime.now());
            opportunityServiceRepository.saveAndFlush(opportunityService);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // TODO
    @Scheduled(fixedRate = 10000)
    public OpportunityService distributeOpportunitiesBetweenAssistants() {
        populateOpportunitiesToBeAttendedQueue();


        if (opportunitiesToBeAttendedQueue.isEmpty()) {
            return null;
        }
        return null;
    }

    protected void populateOpportunitiesToBeAttendedQueue() {
        final Set<Opportunity> opportunitiesSet = opportunityRepository.findAllWithoutService();
        opportunitiesToBeAttendedQueue.addAll(opportunitiesSet);
    }

}
