package com.microservice.serviceeditingopportunities.business.repositories;

import com.microservice.serviceeditingopportunities.business.entities.OpportunityService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityServiceRepository extends JpaRepository<OpportunityService, Integer> {}
