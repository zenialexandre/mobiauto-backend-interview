package com.microservice.systemadministration.business.vo.enums;

public enum UserRoleEnum {

    ADMINISTRATOR("ADM"),
    OWNER("OWN"),
    MANAGER("MAN"),
    ASSISTANT("ASS");

    private String userRoleType;

    private UserRoleEnum(final String userRoleType) {
        setUserRoleType(userRoleType);
    }

    private void setUserRoleType(final String userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getUserRoleType() {
        return userRoleType;
    }

}
