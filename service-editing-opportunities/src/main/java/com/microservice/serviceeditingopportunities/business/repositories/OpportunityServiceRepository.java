package com.microservice.serviceeditingopportunities.business.repositories;

import com.microservice.serviceeditingopportunities.business.entities.OpportunityService;
import com.microservice.systemadministration.business.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OpportunityServiceRepository extends JpaRepository<OpportunityService, Integer> {

    // TODO
    /*@Query(
            " select u" +
            " from   User u " +
            " where  u.storeSequenceId = :storeSequenceId " +
            " and    " //with assistant role +
            " and    "
    )
    Optional<User> findUserToBeLinkedWithService(final @Param("storeSequenceId") Integer storeSequenceId);*/

}
