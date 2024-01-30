package com.microservice.resalemanagement.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.resalemanagement.utils.constants.ResaleManagementConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResaleVO {

    @JsonProperty("legalEntityCode")
    @NotNull(message = ResaleManagementConstants.NOT_NULLABLE_FIELD)
    @CNPJ(
            message = "Expected format: " + ResaleManagementConstants.LEGAL_ENTITY_CODE_EXPECTED_FORMAT +
                    ". Format example: " + ResaleManagementConstants.LEGAL_ENTITY_CODE_FORMAT_EXAMPLE
    )
    @Getter
    @Setter
    private String legalEntityCode;

    @JsonProperty("resaleSocialName")
    @NotNull(message = ResaleManagementConstants.NOT_NULLABLE_FIELD)
    @Getter
    @Setter
    private String resaleSocialName;

}
