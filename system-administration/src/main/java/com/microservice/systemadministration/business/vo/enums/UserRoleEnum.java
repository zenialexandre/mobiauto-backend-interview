package com.microservice.systemadministration.business.vo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleEnum {

    ADMINISTRATOR("ADM"),
    OWNER("OWN"),
    MANAGER("MAN"),
    ASSISTANT("ASS");

    private String userRoleType;

    UserRoleEnum(final String userRoleType) {
        setUserRoleType(userRoleType);
    }

    private void setUserRoleType(final String userRoleType) {
        this.userRoleType = userRoleType;
    }

}
