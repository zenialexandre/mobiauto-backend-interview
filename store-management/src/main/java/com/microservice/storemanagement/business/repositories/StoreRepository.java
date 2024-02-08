package com.microservice.storemanagement.business.repositories;

import com.microservice.storemanagement.business.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {}
