package com.microservice.storemanagement.inbound.controller;

import com.microservice.storemanagement.business.services.StoreManagementService;
import com.microservice.storemanagement.business.vo.StoreVO;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/store-management")
@Tag(
        name = "Store Management",
        description = "API that handles the requests to store-management microservice."
)
public class StoreManagementController {

    @Autowired
    private StoreManagementService storeManagementService;

    @Operation(summary = "Get Store.", description = "Gets a Store record by sequence id.")
    @GetMapping(
            path = "/{storeSequenceId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> getStoreBySequenceId(final @PathVariable("storeSequenceId") Integer storeSequenceId) {
        return storeManagementService.getStoreBySequenceId(storeSequenceId);
    }

    @Operation(summary = "Create Store.", description = "Creates a new Store record.")
    @PostMapping(
            path = "/create-store",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> createStore(final @Valid @RequestBody StoreVO storeVO) {
        return storeManagementService.createStore(storeVO);
    }

    @Operation(summary = "Delete Store.", description = "Deletes a Store record by sequence id.")
    @DeleteMapping(
            path = "/delete-store/{storeSequenceId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteStore(final @PathVariable("storeSequenceId") Integer storeSequenceId) {
        return storeManagementService.deleteStore(storeSequenceId);
    }

    @Operation(summary = "Add User to Store.", description = "Adds a User to the Store record.")
    @PutMapping(
            path = "/add-user/{storeSequenceId}/{userSequenceId}"
    )
    public ResponseEntity<?> addUserToStore(final @PathVariable("storeSequenceId") Integer storeSequenceId,
                                            final @PathVariable("userSequenceId") Integer userSequenceId) {
        return storeManagementService.addUserToStore(storeSequenceId, userSequenceId);
    }

    @Operation(summary = "Add Opportunity to Store.", description = "Adds a Opportunity to the Store record.")
    @PutMapping(
            path = "/add-opportunity/{storeSequenceId}/{opportunitySequenceId}"
    )
    public ResponseEntity<?> addOpportunityToStore(final @PathVariable("storeSequenceId") Integer storeSequenceId,
                                                   final @PathVariable("opportunitySequenceId") Integer opportunitySequenceId) {
        return storeManagementService.addOpportunityToStore(storeSequenceId, opportunitySequenceId);
    }

}
