package com.microservice.systemadministration.utils.exception;

public class ProfileNotFoundException extends RuntimeException {

    private String message;

    public ProfileNotFoundException(final Integer profileSequenceId) {
        setMessage(profileSequenceId);
    }

    private void setMessage(final Integer profileSequenceId) {
        this.message = "Profile with sequence id equal to '" + profileSequenceId + "' not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
