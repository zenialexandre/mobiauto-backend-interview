package com.microservice.systemadministration.utils.exception;

public class ProfileNotFoundByNameException extends RuntimeException {

    private String message;

    public ProfileNotFoundByNameException(final String profileName) {
        setMessage(profileName);
    }

    private void setMessage(final String profileName) {
        this.message = "Profile with name equal to '" + profileName + "' not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
