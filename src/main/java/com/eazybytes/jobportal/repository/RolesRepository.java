package com.eazybytes.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.jobportal.entity.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findRoleByName(String name);

}
