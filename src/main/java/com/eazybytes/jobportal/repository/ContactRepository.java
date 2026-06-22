package com.eazybytes.jobportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.jobportal.entity.Contact;

import jakarta.persistence.Id;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Id> {

    List<Contact> findByStatus(String status);

    Page<Contact> findContactByStatus(PageRequest pageable, String contactStatusNew);

    Optional<Contact> findById(Long id);
    
}
