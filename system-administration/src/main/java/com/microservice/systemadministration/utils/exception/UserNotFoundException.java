package com.microservice.systemadministration.utils.exception;

public class UserNotFoundException extends RuntimeException {

    private String message;

    public UserNotFoundException(final Integer userSequenceId) {
        setMessage(userSequenceId);
    }

    private void setMessage(final Integer userSequenceId) {
        this.message = "User with sequence id equal to '" + userSequenceId + "' not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
