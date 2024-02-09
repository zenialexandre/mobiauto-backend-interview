package com.microservice.serviceeditingopportunities.business.entities;

import com.microservice.serviceeditingopportunities.utils.constants.ServiceEditingOpportunitiesConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "opportunity_service")
public class OpportunityService {

    @Id
    @Column(name = ServiceEditingOpportunitiesConstants.OPPORTUNITY_SERVICE_SEQUENCE_iD)
    @Schema(description = "Automatically generated by the system.")
    @SequenceGenerator(
            name = ServiceEditingOpportunitiesConstants.OPPORTUNITY_SERVICE_SEQUENCE_iD,
            sequenceName = ServiceEditingOpportunitiesConstants.OPPORTUNITY_SERVICE_SEQUENCE_iD
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = ServiceEditingOpportunitiesConstants.OPPORTUNITY_SERVICE_SEQUENCE_iD
    )
    private Integer opportunityServiceSequenceId;

    @Column(name = "opportunity_sequence_id")
    private Integer opportunitySequenceId;

    @Column(name = "user_sequence_id")
    private Integer userSequenceId;

    @Column(name = "opportunity_assignment_date")
    private LocalDateTime opportunityAssignmentDate;

    @Column(name = "opportunity_conclusion_date")
    private LocalDateTime opportunityConclusionDate;

}
