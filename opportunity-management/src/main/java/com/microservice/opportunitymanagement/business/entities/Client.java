package com.microservice.opportunitymanagement.business.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;

@Builder
@Embeddable
public class Client {

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = ("client_telephone"))
    private String clientTelephone;

}
