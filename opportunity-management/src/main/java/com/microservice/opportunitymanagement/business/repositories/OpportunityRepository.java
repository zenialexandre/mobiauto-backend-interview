package com.microservice.opportunitymanagement.business.repositories;

import com.microservice.opportunitymanagement.business.entities.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    @Query(" select o from Opportunity o where status = 'NEW' ")
    Set<Opportunity> findAllWithoutService();

}
