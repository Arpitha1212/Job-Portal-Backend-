package com.eazybytes.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.jobportal.entity.JobPortalUser;

@Repository
public interface JobPortalUserRepository extends JpaRepository<JobPortalUser, Long> {

    Optional<JobPortalUser> findByEmailAndMobileNumber(String email, String mobileNumber);

    Optional<JobPortalUser> findByEmail(String email);

}
