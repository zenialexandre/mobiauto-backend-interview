package com.microservice.serviceeditingopportunities.business.repositories;

import com.microservice.serviceeditingopportunities.business.entities.OpportunityService;
import com.microservice.storemanagement.business.entities.Store;
import com.microservice.systemadministration.business.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OpportunityServiceRepository extends JpaRepository<OpportunityService, Integer> {

    @Query(
            " select u "
            + " from   Store s, User u, Profile p, Role r "
            + " where  s.storeSequenceId = :storeSequenceId "
            + " and    u.storeSequenceId = s.storeSequenceId "
            + " and    p.userSequenceId  = u.userSequenceId "
            + " and    p.roleSequenceId  = r.roleSequenceId "
            + " and    r.roleName        = 'ASSISTANT' "
    )
    Set<User> findAssistantsFromStore(final @Param("storeSequenceId") Integer storeSequenceId);

    @Query(" select s from Store s where s.storeSequenceId = :storeSequenceId ")
    Optional<Store> findOpportunityStore(final @Param("storeSequenceId") Integer storeSequenceId);

}
