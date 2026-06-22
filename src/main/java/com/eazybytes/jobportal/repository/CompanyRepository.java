package com.eazybytes.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eazybytes.jobportal.entity.Company;

import jakarta.persistence.Id;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Id> {

    @Query("SELECT DISTINCT c FROM Company c JOIN FETCH c.jobs j WHERE j.status = :status")
     List<Company> findAllWithJobByStatus(@Param("status") String status);
}
