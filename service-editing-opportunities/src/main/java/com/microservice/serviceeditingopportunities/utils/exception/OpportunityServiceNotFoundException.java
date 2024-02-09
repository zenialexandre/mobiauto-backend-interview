package com.microservice.serviceeditingopportunities.utils.exception;

public class OpportunityServiceNotFoundException extends RuntimeException {

    private String message;

    public OpportunityServiceNotFoundException(final Integer opportunityServiceSequenceId) {
        setMessage(opportunityServiceSequenceId);
    }

    private void setMessage(final Integer opportunityServiceSequenceId) {
        this.message = "Opportunity Service with sequence id equal to '" + opportunityServiceSequenceId + "' not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
