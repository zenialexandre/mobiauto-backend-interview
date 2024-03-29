package com.microservice.systemadministration.business.repositories;

import com.microservice.systemadministration.business.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(" select u from User u where u.email = :email ")
    Optional<User> findByEmail(final @Param("email") String email);

    @Query(" select u.opportunitiesAttendedNumber from User u where u.userSequenceId = :userSequenceId ")
    Optional<Integer> findNumberOfOpportunitiesAttended(final @Param("userSequenceId") Integer userSequenceId);

}
