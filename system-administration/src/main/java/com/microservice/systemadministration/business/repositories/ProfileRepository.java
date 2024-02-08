package com.microservice.systemadministration.business.repositories;

import com.microservice.systemadministration.business.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {}
