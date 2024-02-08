package com.microservice.systemadministration.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.systemadministration.business.entities.Profile;
import com.microservice.systemadministration.business.vo.enums.UserRoleEnum;
import com.microservice.systemcommons.utils.constants.SystemCommonsConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO {

    @JsonProperty("userName")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private String userName;

    @JsonProperty("email")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    @Email
    private String email;

    @JsonProperty("password")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$",
            message = "The password must contain the following: " +
                    "Minimum of 1 uppercase letter, " +
                    "Minimum of 1 lowercase letter, " +
                    "Minimum of 1 special character, " +
                    "Minimum of 1 number, " +
                    "Minimum of 8 characters."
    )
    private String password;

    @JsonProperty("profiles")
    @NotNull(message = SystemCommonsConstants.NOT_NULLABLE_FIELD)
    private Set<Profile> profiles;

}
