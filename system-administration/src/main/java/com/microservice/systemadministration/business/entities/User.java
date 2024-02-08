package com.microservice.systemadministration.business.entities;

import com.microservice.storemanagement.business.entities.Store;
import com.microservice.storemanagement.utils.constants.StoreManagementConstants;
import com.microservice.systemadministration.utils.constants.SystemAdministrationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @Column(name = SystemAdministrationConstants.USER_SEQUENCE_ID)
    @Schema(description = "Automatically generated by the system.")
    @SequenceGenerator(
            name = SystemAdministrationConstants.USER_SEQUENCE_ID,
            sequenceName = SystemAdministrationConstants.USER_SEQUENCE_ID
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = SystemAdministrationConstants.USER_SEQUENCE_ID
    )
    private Integer userSequenceId;

    @Column(name = "user_name")
    @Schema(description = "The name of the user.")
    private String userName;

    @Column(name = "email", unique = true)
    @Schema(description = "The valid email of the user.")
    private String email;

    @Column(name = "password")
    @Schema(description = "The password of the user.")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_table_profile_junction",
            joinColumns = {
                    @JoinColumn(
                            name = SystemAdministrationConstants.USER_SEQUENCE_ID
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = SystemAdministrationConstants.PROFILE_SEQUENCE_ID
                    )
            }
    )
    private Set<Profile> profiles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = StoreManagementConstants.STORE_SEQUENCE_ID,
            nullable = false
    )
    private Store store;

}
