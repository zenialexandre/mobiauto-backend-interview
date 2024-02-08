package com.microservice.opportunitymanagement.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.systemcommons.utils.constants.SystemCommonsConstants;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleOfInterestVO {

    @JsonProperty("vehicleBrand")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private String vehicleBrand;

    @JsonProperty("vehicleModel")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private String vehicleModel;

    @JsonProperty("vehicleVersion")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private String vehicleVersion;

    @JsonProperty("vehicleModelDate")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String vehicleModelDate;

}
