package com.microservice.opportunitymanagement.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.opportunitymanagement.utils.constants.OpportunityManagementConstants;
import com.microservice.systemcommons.utils.constants.SystemCommonsConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOpportunityVO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("clientName")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private String clientName;

    @JsonProperty("clientEmail")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    @Email
    private String clientEmail;

    @JsonProperty("clientTelephone")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    @Pattern(regexp = "(\\d{2}) \\d{4}-\\d{4}")
    private String clientTelephone;

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
    @DateTimeFormat(pattern = OpportunityManagementConstants.DATE_FORMAT)
    private String vehicleModelDate;

}
