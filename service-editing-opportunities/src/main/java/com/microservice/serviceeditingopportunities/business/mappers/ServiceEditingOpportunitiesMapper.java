package com.microservice.serviceeditingopportunities.business.mappers;

import com.microservice.serviceeditingopportunities.business.entities.OpportunityService;
import com.microservice.serviceeditingopportunities.business.vo.OpportunityServiceVO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@NoArgsConstructor
@Component
public class ServiceEditingOpportunitiesMapper {

    public OpportunityService map(final OpportunityServiceVO opportunityServiceVO) {
        return OpportunityService.builder()
                .opportunitySequenceId(opportunityServiceVO.getOpportunitySequenceId())
                .userSequenceId(opportunityServiceVO.getUserSequenceId())
                .opportunityAssignmentDate(LocalDateTime.now())
                .opportunityConclusionDate(null)
                .build();
    }

}
