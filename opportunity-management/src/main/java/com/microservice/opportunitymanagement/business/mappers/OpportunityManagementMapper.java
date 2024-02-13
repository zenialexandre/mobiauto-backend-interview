package com.microservice.opportunitymanagement.business.mappers;

import com.microservice.opportunitymanagement.business.entities.Client;
import com.microservice.opportunitymanagement.business.entities.Opportunity;
import com.microservice.opportunitymanagement.business.entities.VehicleOfInterest;
import com.microservice.opportunitymanagement.business.vo.CreateOpportunityVO;
import com.microservice.opportunitymanagement.business.vo.enums.StatusEnum;
import com.microservice.opportunitymanagement.utils.constants.OpportunityManagementConstants;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Component
public class OpportunityManagementMapper {

    public Opportunity map(final CreateOpportunityVO createOpportunityVO) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(OpportunityManagementConstants.DATE_FORMAT);

        return Opportunity.builder()
                .status(StatusEnum.NEW.name())
                .client(
                        Client.builder()
                                .clientName(createOpportunityVO.getClientName())
                                .clientEmail(createOpportunityVO.getClientEmail())
                                .clientTelephone(createOpportunityVO.getClientTelephone())
                                .build()
                )
                .vehicleOfInterest(
                        VehicleOfInterest.builder()
                                .vehicleBrand(createOpportunityVO.getVehicleBrand())
                                .vehicleModel(createOpportunityVO.getVehicleModel())
                                .vehicleVersion(createOpportunityVO.getVehicleVersion())
                                .vehicleModelDate(LocalDate.parse(createOpportunityVO.getVehicleModelDate(), dateTimeFormatter))
                                .build()
                ).build();
    }

}
