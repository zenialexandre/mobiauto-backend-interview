package com.microservice.opportunitymanagement.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.systemcommons.utils.constants.SystemCommonsConstants;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientVO {

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

}
