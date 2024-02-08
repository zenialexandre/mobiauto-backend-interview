package com.microservice.opportunitymanagement.utils.exception;

public class OpportunityNotFoundException extends RuntimeException {

    private String message;

    public OpportunityNotFoundException(final Integer opportunitySequenceId) {
        setMessage(opportunitySequenceId);
    }

    private void setMessage(final Integer opportunitySequenceId) {
        this.message = "Opportunity with sequence id equal to '" + opportunitySequenceId + "' not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
