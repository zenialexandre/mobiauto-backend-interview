package com.microservice.systemadministration.utils.exception;

public class RoleNotFoundException extends RuntimeException {

    private String message;

    public RoleNotFoundException(final String roleName) {
        setMessage(roleName);
    }

    private void setMessage(final String roleName) {
        this.message = "Role with name equal to '" + roleName + "' not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
