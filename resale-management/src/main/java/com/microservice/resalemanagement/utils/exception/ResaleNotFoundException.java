package com.microservice.resalemanagement.utils.exception;

public class ResaleNotFoundException extends RuntimeException {

    private String message;

    public ResaleNotFoundException(final Integer resaleSequenceId) {
        setMessage(resaleSequenceId);
    }

    private void setMessage(final Integer resaleSequenceId) {
        this.message = "Resale with sequence id equal to '" + resaleSequenceId + "' not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
