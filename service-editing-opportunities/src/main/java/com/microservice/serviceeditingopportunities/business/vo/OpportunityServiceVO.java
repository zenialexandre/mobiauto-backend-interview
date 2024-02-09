package com.microservice.serviceeditingopportunities.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.systemcommons.utils.constants.SystemCommonsConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpportunityServiceVO {

    @JsonProperty("opportunitySequenceId")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private Integer opportunitySequenceId;

    @JsonProperty("userSequenceId")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private Integer userSequenceId;

    @JsonProperty("opportunityAssignmentDate")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime opportunityAssignmentDate;

    @JsonProperty("opportunityConclusionDate")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime opportunityConclusionDate;

}
