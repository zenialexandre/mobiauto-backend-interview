package com.microservice.storemanagement.utils.exception;

public class StoreNotFoundException extends RuntimeException {

    private String message;

    public StoreNotFoundException(final Integer storeSequenceId) {
        setMessage(storeSequenceId);
    }

    private void setMessage(final Integer storeSequenceId) {
        this.message = "Store with sequence id equal to '" + storeSequenceId + "' not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
