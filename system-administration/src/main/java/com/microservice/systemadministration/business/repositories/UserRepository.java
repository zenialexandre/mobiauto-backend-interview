package com.microservice.systemadministration.business.repositories;

import com.microservice.systemadministration.business.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {}
