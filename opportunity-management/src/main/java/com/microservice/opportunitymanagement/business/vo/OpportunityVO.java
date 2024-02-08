package com.microservice.opportunitymanagement.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.microservice.opportunitymanagement.business.vo.enums.StatusEnum;
import com.microservice.systemcommons.utils.constants.SystemCommonsConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpportunityVO {

    @JsonProperty("status")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @JsonUnwrapped
    private ClientVO clientVO = new ClientVO();

    @JsonUnwrapped
    private VehicleOfInterestVO vehicleOfInterestVO = new VehicleOfInterestVO();

}
