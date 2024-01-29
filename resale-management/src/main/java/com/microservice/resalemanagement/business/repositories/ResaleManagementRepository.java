package com.microservice.resalemanagement.business.repositories;

import com.microservice.resalemanagement.business.entities.Resale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResaleManagementRepository extends JpaRepository<Resale, Integer> {}
