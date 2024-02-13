package com.microservice.opportunitymanagement.business.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VehicleOfInterest {

    @Column(name = "vehicle_brand")
    private String vehicleBrand;

    @Column(name = "vehicle_model")
    private String vehicleModel;

    @Column(name = "vehicle_version")
    private String vehicleVersion;

    @Column(name = "vehicle_model_date")
    @Past
    private LocalDate vehicleModelDate;

}
