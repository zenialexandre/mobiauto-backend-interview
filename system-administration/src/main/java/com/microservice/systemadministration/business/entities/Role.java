package com.microservice.systemadministration.business.entities;

import com.microservice.systemadministration.utils.constants.SystemAdministrationConstants;
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

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = SystemAdministrationConstants.ROLE_SEQUENCE_ID)
    @Schema(description = "Automatically generated by the system.")
    @SequenceGenerator(
            name = SystemAdministrationConstants.ROLE_SEQUENCE_ID,
            sequenceName = SystemAdministrationConstants.ROLE_SEQUENCE_ID
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = SystemAdministrationConstants.ROLE_SEQUENCE_ID
    )
    private Integer roleSequenceId;

    @Column(name = "role_name")
    @Schema(description = "The name of the role.")
    private String roleName;

}
