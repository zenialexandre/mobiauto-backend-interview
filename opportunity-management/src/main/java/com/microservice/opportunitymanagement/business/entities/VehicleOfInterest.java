package com.microservice.opportunitymanagement.business.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Embeddable
public class VehicleOfInterest {

    @Column(name = "vehicle_brand")
    private String vehicleBrand;

    @Column(name = "vehicle_model")
    private String vehicleModel;

    @Column(name = "vehicle_version")
    private String vehicleVersion;

    @Column(name = "vehicle_model_date")
    private LocalDateTime vehicleModelDate;

}
