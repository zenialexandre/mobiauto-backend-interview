package com.microservice.opportunitymanagement.inbound.controller;

import com.microservice.opportunitymanagement.business.services.OpportunityManagementService;
import com.microservice.opportunitymanagement.business.vo.CreateOpportunityVO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/opportunity-management")
@Tag(
        name = "Opportunity Management",
        description = "API that handles the requests to opportunity-management microservice."
)
public class OpportunityManagementController {

    @Autowired
    private OpportunityManagementService opportunityManagementService;

    @GetMapping(
            path = "/{opportunitySequenceId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> getOpportunityBySequenceId(final @PathVariable("opportunitySequenceId") Integer opportunitySequenceId) {
        return opportunityManagementService.getOpportunityBySequenceId(opportunitySequenceId);
    }

    @PostMapping(
            path = "/create-opportunity",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> createOpportunity(final @Valid @RequestBody CreateOpportunityVO createOpportunityVO) {
        return opportunityManagementService.createOpportunity(createOpportunityVO);
    }

    @DeleteMapping(
            path = "/delete-opportunity/{opportunitySequenceId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteOpportunity(final @PathVariable("opportunitySequenceId") Integer opportunitySequenceId) {
        return opportunityManagementService.deleteOpportunity(opportunitySequenceId);
    }

}
