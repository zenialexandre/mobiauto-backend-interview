package com.microservice.serviceeditingopportunities.inbound.controller;

import com.microservice.serviceeditingopportunities.business.services.ServiceEditingOpportunitiesService;
import com.microservice.serviceeditingopportunities.business.vo.OpportunityServiceVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/service-editing-opportunities")
@Tag(
        name = "Service Editing Opportunities",
        description = "API that handles the requests to service-editing-opportunities microservice."
)
public class ServiceEditingOpportunitiesController {

    @Autowired
    private ServiceEditingOpportunitiesService serviceEditingOpportunitiesService;

    @GetMapping(
            path = "/{opportunityServiceSequenceId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> getOpportunityServiceBySequenceId(final @PathVariable("opportunityServiceSequenceId") Integer opportunityServiceSequenceId) {
        return serviceEditingOpportunitiesService.getOpportunityServiceBySequenceId(opportunityServiceSequenceId);
    }

    @PostMapping(
            path = "/create-opportunity-service",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> createOpportunityService(final @Valid @RequestBody OpportunityServiceVO opportunityServiceVO) {
        return serviceEditingOpportunitiesService.createOpportunityService(opportunityServiceVO);
    }

    @DeleteMapping(
            path = "/delete-opportunity-service/{opportunityServiceSequenceId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteOpportunityService(final @PathVariable("opportunityServiceSequenceId") Integer opportunityServiceSequenceId) {
        return serviceEditingOpportunitiesService.deleteOpportunityService(opportunityServiceSequenceId);
    }

    @PutMapping(
            path = "/conclude-opportunity-service/{opportunityServiceSequenceId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> concludeOpportunityService(final @PathVariable("opportunityServiceSequenceId") Integer opportunityServiceSequenceId) {
        return serviceEditingOpportunitiesService.concludeOpportunityService(opportunityServiceSequenceId);
    }

}
