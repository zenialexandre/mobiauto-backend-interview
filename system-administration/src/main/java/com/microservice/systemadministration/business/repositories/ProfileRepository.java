package com.microservice.systemadministration.business.repositories;

import com.microservice.systemadministration.business.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    @Query(" select p from Profile p where p.profileName = :profileName ")
    Optional<Profile> findProfileByName(final @Param("profileName") String profileName);

}
