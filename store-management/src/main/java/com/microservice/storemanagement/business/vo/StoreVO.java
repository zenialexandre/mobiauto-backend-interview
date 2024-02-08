package com.microservice.storemanagement.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.opportunitymanagement.business.entities.Opportunity;
import com.microservice.systemadministration.business.entities.User;
import com.microservice.systemcommons.utils.constants.SystemCommonsConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreVO {

    @JsonProperty("storeName")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private String storeName;

    @JsonProperty("storeUsers")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private Set<User> storeUsers;

    @JsonProperty("storeOpportunities")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private Set<Opportunity> storeOpportunities;

}
