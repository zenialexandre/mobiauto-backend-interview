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
import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemadministration.business.repositories.UserRepository;
import com.microservice.systemadministration.utils.exception.UserNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.List;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
import java.util.Comparator;

@NoArgsConstructor
@Service
public class ServiceEditingOpportunitiesService {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OpportunityServiceRepository opportunityServiceRepository;

    @Autowired
    private ServiceEditingOpportunitiesMapper serviceEditingOpportunitiesMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private final Deque<Opportunity> opportunitiesToBeAttendedQueue = new ArrayDeque<>();

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
            final Integer userSequenceId = opportunityService.getUserSequenceId();
            final Integer opportunitiesAttendedNumber = userRepository.findNumberOfOpportunitiesAttended(userSequenceId);
            final Opportunity opportunity = opportunityRepository.findById(opportunitySequenceId).orElseThrow(() ->
                    new OpportunityNotFoundException(opportunitySequenceId)
            );
            final User user = userRepository.findById(userSequenceId).orElseThrow(() ->
                    new UserNotFoundException(userSequenceId)
            );

            opportunity.setStatus(StatusEnum.IN_SERVICE.name());
            user.setLastOpportunityReceived(LocalDateTime.now());
            user.setOpportunitiesAttendedNumber(opportunitiesAttendedNumber + 1);
            opportunityServiceRepository.saveAndFlush(opportunityService);
            opportunityRepository.saveAndFlush(opportunity);
            userRepository.saveAndFlush(user);
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
            final User user = userRepository.findById(userSequenceId).orElseThrow(() ->
                    new UserNotFoundException(userSequenceId)
            );
            final Integer opportunitiesAttendedNumber = userRepository.findNumberOfOpportunitiesAttended(userSequenceId);

            opportunityService.setUserSequenceId(userSequenceId);
            opportunityService.setOpportunityAssignmentDate(LocalDateTime.now());
            user.setLastOpportunityReceived(LocalDateTime.now());
            user.setOpportunitiesAttendedNumber(opportunitiesAttendedNumber + 1);
            opportunityServiceRepository.saveAndFlush(opportunityService);
            userRepository.saveAndFlush(user);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Scheduled(fixedRate = 10000)
    public OpportunityService distributeOpportunitiesBetweenAssistants() {
        populateOpportunitiesToBeAttendedQueue();
        if (opportunitiesToBeAttendedQueue.isEmpty()) return null;

        final List<Object> validOpportunityList = getValidOpportunityToBeAttended();
        if (Objects.isNull(validOpportunityList)) return null;
        return setOpportunityAssistant(validOpportunityList);
    }

    protected void populateOpportunitiesToBeAttendedQueue() {
        final Set<Opportunity> opportunitiesSet = opportunityRepository.findAllWithoutService();
        opportunitiesToBeAttendedQueue.addAll(opportunitiesSet);
    }

    protected List<Object> getValidOpportunityToBeAttended() {
        final List<Object> validOpportunityList = new ArrayList<>();

        for (final Opportunity opportunity : opportunitiesToBeAttendedQueue)  {
            opportunityServiceRepository.findOpportunityStore(opportunity.getStoreSequenceId()).ifPresent(store -> {
                final Set<User> storeAssistantsSet = opportunityServiceRepository.findAssistantsFromStore(store.getStoreSequenceId());

                if (!storeAssistantsSet.isEmpty()) {
                    validOpportunityList.addAll(List.of(opportunity, storeAssistantsSet));
                }
            });
        }
        if (validOpportunityList.isEmpty()) return null;

        final Opportunity opportunityToBeRemovedFromQueue = (Opportunity) validOpportunityList.getFirst();
        opportunitiesToBeAttendedQueue.remove(opportunityToBeRemovedFromQueue);
        return validOpportunityList;
    }

    protected OpportunityService setOpportunityAssistant(final List<Object> validOpportunityList) {
        final Opportunity opportunity = (Opportunity) validOpportunityList.getFirst();
        final Set<User> storeAssistantsSet = (Set<User>) validOpportunityList.get(1);

        if (storeAssistantsSet.size() > 1) {
            final User user = storeAssistantsSet.stream()
                    .min(Comparator.comparing(User::getLastOpportunityReceived).thenComparing(User::getOpportunitiesAttendedNumber)).get();
            return finalizeDistributeOpportunitiesBetweenAssistants(user, opportunity);
        } else if (storeAssistantsSet.size() == 1) {
            return finalizeDistributeOpportunitiesBetweenAssistants(storeAssistantsSet.stream().findFirst().get(), opportunity);
        }
        return null;
    }

    protected OpportunityService finalizeDistributeOpportunitiesBetweenAssistants(final User user, final Opportunity opportunity) {
        final Integer userSequenceId = user.getUserSequenceId();
        final Integer opportunitiesAttendedNumber = userRepository.findNumberOfOpportunitiesAttended(userSequenceId);
        final OpportunityService opportunityService = OpportunityService.builder()
                .opportunitySequenceId(opportunity.getOpportunitySequenceId())
                .userSequenceId(userSequenceId)
                .opportunityAssignmentDate(LocalDateTime.now())
                .opportunityConclusionDate(null)
                .build();

        user.setLastOpportunityReceived(LocalDateTime.now());
        user.setOpportunitiesAttendedNumber(opportunitiesAttendedNumber + 1);
        opportunity.setStatus(StatusEnum.IN_SERVICE.name());
        opportunityServiceRepository.saveAndFlush(opportunityService);
        opportunityRepository.saveAndFlush(opportunity);
        userRepository.saveAndFlush(user);
        return opportunityService;
    }

}
