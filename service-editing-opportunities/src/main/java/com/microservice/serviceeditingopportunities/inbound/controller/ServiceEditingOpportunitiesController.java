package com.microservice.serviceeditingopportunities.inbound.controller;

import com.microservice.serviceeditingopportunities.business.services.ServiceEditingOpportunitiesService;
import com.microservice.serviceeditingopportunities.business.vo.OpportunityServiceVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "Get Opportunity Service.", description = "Gets a Opportunity Service by sequence id.")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'OWNER', 'MANAGER')")
    @GetMapping(
            path = "/{opportunityServiceSequenceId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> getOpportunityServiceBySequenceId(final @PathVariable("opportunityServiceSequenceId") Integer opportunityServiceSequenceId) {
        return serviceEditingOpportunitiesService.getOpportunityServiceBySequenceId(opportunityServiceSequenceId);
    }

    @Operation(summary = "Create a Opportunity Service.", description = "Creates a new Opportunity Service (Attendance of a Opportunity).")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'OWNER', 'MANAGER')")
    @PostMapping(
            path = "/create-opportunity-service",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> createOpportunityService(final @Valid @RequestBody OpportunityServiceVO opportunityServiceVO) {
        return serviceEditingOpportunitiesService.createOpportunityService(opportunityServiceVO);
    }

    @Operation(summary = "Delete a Opportunity Service.", description = "Delete a Opportunity Service by sequence id.")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'OWNER', 'MANAGER')")
    @DeleteMapping(
            path = "/delete-opportunity-service/{opportunityServiceSequenceId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteOpportunityService(final @PathVariable("opportunityServiceSequenceId") Integer opportunityServiceSequenceId) {
        return serviceEditingOpportunitiesService.deleteOpportunityService(opportunityServiceSequenceId);
    }

    @Operation(summary = "Conclude Opportunity Service.", description = "Conclude a Opportunity Service (Set Status to 'CONCLUDED').")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'OWNER', 'MANAGER', 'ASSISTANT')")
    @PutMapping(path = "/conclude-opportunity-service/{opportunityServiceSequenceId}")
    public ResponseEntity<?> concludeOpportunityService(final @PathVariable("opportunityServiceSequenceId") Integer opportunityServiceSequenceId) {
        return serviceEditingOpportunitiesService.concludeOpportunityService(opportunityServiceSequenceId);
    }

    @Operation(summary = "Modify Assistant of the Opportunity Service.", description = "Modify the current Assistant of the Opportunity Service.")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'OWNER', 'MANAGER')")
    @PutMapping(path = "/modify-opportunity-service-assistant/{opportunityServiceSequenceId}/{userSequenceId}")
    public ResponseEntity<?> modifyOpportunityServiceAssistant(final @PathVariable("opportunityServiceSequenceId") Integer opportunityServiceSequenceId,
                                                               final @PathVariable("userSequenceId") Integer userSequenceId) {
        return serviceEditingOpportunitiesService.modifyOpportunityServiceAssistant(opportunityServiceSequenceId, userSequenceId);
    }

}
