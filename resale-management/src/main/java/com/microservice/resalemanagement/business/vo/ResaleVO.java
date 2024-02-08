package com.microservice.resalemanagement.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.resalemanagement.utils.constants.ResaleManagementConstants;
import com.microservice.systemcommons.utils.constants.SystemCommonsConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResaleVO {

    @JsonProperty("legalEntityCode")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    @CNPJ(
            message = "Expected format: " + ResaleManagementConstants.LEGAL_ENTITY_CODE_EXPECTED_FORMAT +
                    ". Format example: " + ResaleManagementConstants.LEGAL_ENTITY_CODE_FORMAT_EXAMPLE
    )
    private String legalEntityCode;

    @JsonProperty("resaleSocialName")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private String resaleSocialName;

}
