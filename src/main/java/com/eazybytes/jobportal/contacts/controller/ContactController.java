package com.eazybytes.jobportal.contacts.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.jobportal.constant.ApplicationConstants;
import com.eazybytes.jobportal.contacts.service.ContactService;
import com.eazybytes.jobportal.dto.ContactDTO;
import com.eazybytes.jobportal.dto.ContactResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @PostMapping(value = "/public" ,version = "1.0")
    public ResponseEntity<String> saveContactDetails(@RequestBody @Valid ContactDTO contactDTO){
      boolean isSaved = contactService.saveContactDetails(contactDTO);
        if(isSaved){
           return ResponseEntity.status(HttpStatus.ACCEPTED).body("Saved Successfully"); 
        }else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save contact details");
    }

    @GetMapping(value = "/admin", version = "1.0")
    public ResponseEntity<List<ContactResponseDTO>> fetchNewContactMsgs(){
        List<ContactResponseDTO> contactResponseDtos = contactService.fetchNewContactMsgs();
        return ResponseEntity.status(HttpStatus.OK).body(contactResponseDtos);
    }

    @GetMapping(value = "/page/admin", version = "1.0")
    public ResponseEntity<Page<ContactResponseDTO>> fetchContactMsgsWithPagination(
     @RequestParam(defaultValue = "0") int page,
     @RequestParam(defaultValue = "10") int size,
     @RequestParam(defaultValue = "createdAt") String sortBy,
     @RequestParam(defaultValue = "asc") String sortAsc){
        Page<ContactResponseDTO> contactResponseDtos = contactService.fetchContactMsgsWithPagination(page, size, sortBy, sortAsc);
        return ResponseEntity.status(HttpStatus.OK).body(contactResponseDtos);
    }


    @PatchMapping(value = "/{id}/status/admin", version = "1.0")
    public ResponseEntity<String> updateContactMsgStatus(@PathVariable Long id){
        boolean isUpdated = contactService.updateContactMsgStatus(id, ApplicationConstants.CONTACT_STATUS_RESOLVED);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body("Contact message status updated successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update contact message status");
        }
    }
}
