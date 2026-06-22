package com.eazybytes.jobportal.dto;

import com.eazybytes.jobportal.constant.ApplicationConstants;
import com.eazybytes.jobportal.entity.Contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ContactDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "User type is required")
    @Pattern(regexp= "Job Seaker|Employer|Other")
    private String userType;

    @NotBlank(message = "Subject is required")
    @Size(min = 5, max = 100, message = "Subject must be between 5 and 100 characters")
    private String subject;

    @NotBlank(message = "Message is required")
    @Size(min = 10, max = 500, message = "Message must be between 10 and 500 characters")
    private String message;

    private String status;

    public ContactDTO(){
        
    }

    public ContactDTO(String email, Long id, String message, String name, String status, String subject, String userType) {
        this.email = email;
        this.id = id;
        this.message = message;
        this.name = name;
        this.status = status;
        this.subject = subject;
        this.userType = userType;
    }

    public static Contact convertDTOtoEntity(ContactDTO contactDTO){
        Contact contact = new Contact();
        contact.setEmail(contactDTO.email);
        contact.setMessage(contactDTO.message);
        contact.setName(contactDTO.name);
        contact.setStatus(ApplicationConstants.CONTACT_STATUS_NEW);
        contact.setSubject(contactDTO.subject);
        contact.setUserType(contactDTO.userType);
        return contact;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    

}
