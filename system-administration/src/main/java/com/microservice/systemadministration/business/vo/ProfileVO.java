package com.microservice.systemadministration.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.systemadministration.business.vo.enums.UserRoleEnum;
import com.microservice.systemcommons.utils.constants.SystemCommonsConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileVO {

    @JsonProperty("profileName")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private String profileName;

    @JsonProperty("userSequenceId")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private Integer userSequenceId;

    @JsonProperty("profileRole")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum profileRole;

}
