package com.microservice.opportunitymanagement.business.vo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusEnum {

    NEW("NEW"),
    IN_SERVICE("IN_SERVICE"),
    CONCLUDED("CONCLUDED");

    private String status;

    StatusEnum(final String status) {
        setUserRoleType(status);
    }

    private void setUserRoleType(final String status) {
        this.status = status;
    }

}
