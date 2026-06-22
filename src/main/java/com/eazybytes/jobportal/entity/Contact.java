package com.eazybytes.jobportal.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTACTS")
public class Contact extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "user_type", nullable = false, length = 50)
    private String userType;

    @Column(nullable = false, length = 255)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String message;

    @Column(nullable = false, length = 50)
    private String status = "NEW";

 

    public Contact(){      
    }

    public Contact(String email, Long id, String message, String name, String subject, Instant updatedAt, String updatedBy, String userType) {
        this.email = email;
        this.id = id;
        this.message = message;
        this.name = name;
        this.subject = subject;  
        this.userType = userType;
    }

    public Long getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public String getEmail() {
      return email;
    }

    public String getUserType() {
      return userType;
    }

    public String getSubject() {
      return subject;
    }

    public String getMessage() {
      return message;
    }

    public String getStatus() {
      return status;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public void setUserType(String userType) {
      this.userType = userType;
    }

    public void setSubject(String subject) {
      this.subject = subject;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public void setStatus(String status) {
      this.status = status;
    }
   
    
}