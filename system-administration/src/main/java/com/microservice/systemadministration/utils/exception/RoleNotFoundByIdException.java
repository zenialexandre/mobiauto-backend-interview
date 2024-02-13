package com.microservice.systemadministration.utils.exception;

public class RoleNotFoundByIdException extends RuntimeException {

    private String message;

    public RoleNotFoundByIdException(final Integer roleSequenceId) {
        setMessage(roleSequenceId);
    }

    private void setMessage(final Integer roleSequenceId) {
        this.message = "Role with sequence id equal to '" + roleSequenceId + "' not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
