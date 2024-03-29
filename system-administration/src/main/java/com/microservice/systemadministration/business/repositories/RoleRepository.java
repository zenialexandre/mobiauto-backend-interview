package com.microservice.systemadministration.business.repositories;

import com.microservice.systemadministration.business.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(" select r from Role r where r.roleName = :roleName ")
    Optional<Role> findRoleByName(final @Param("roleName") String roleName);

}
