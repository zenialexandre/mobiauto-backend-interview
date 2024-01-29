package com.microservice.resalemanagement.business.entities;

import com.microservice.resalemanagement.utils.constants.ResaleManagementConstants;
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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resale")
public class Resale {

    @Id
    @Column(name = ResaleManagementConstants.RESALE_SEQUENCE_ID)
    @SequenceGenerator(
            name = ResaleManagementConstants.RESALE_SEQUENCE_ID,
            sequenceName = ResaleManagementConstants.RESALE_SEQUENCE_ID
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = ResaleManagementConstants.RESALE_SEQUENCE_ID
    )
    private Integer resaleSequenceId;

    @Column(name = "legal_entity_code")
    private String legalEntityCode;

    @Column(name = "resale_social_name")
    private String resaleSocialName;

}