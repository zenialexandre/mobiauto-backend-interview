package com.microservice.opportunitymanagement.business.repositories;

import com.microservice.opportunitymanagement.business.entities.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {}
