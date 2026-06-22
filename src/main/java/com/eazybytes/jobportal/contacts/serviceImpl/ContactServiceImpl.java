package com.eazybytes.jobportal.contacts.serviceImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eazybytes.jobportal.constant.ApplicationConstants;
import com.eazybytes.jobportal.contacts.service.ContactService;
import com.eazybytes.jobportal.dto.ContactDTO;
import com.eazybytes.jobportal.dto.ContactResponseDTO;
import com.eazybytes.jobportal.entity.Contact;
import com.eazybytes.jobportal.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

  private final ContactRepository contactRepository;

  public ContactServiceImpl(ContactRepository contactRepository){
    this.contactRepository = contactRepository;
  }

  @Transactional
   @Override
    public Boolean saveContactDetails(ContactDTO contactDTO) {
      Boolean isSaved = false;
      Contact contactToEntity = ContactDTO.convertDTOtoEntity(contactDTO);
     
       Contact contact= contactRepository.save(contactToEntity);
       if(contact!=null){
        isSaved = true;
       }
        return isSaved;
    }

   @Override
   public List<ContactResponseDTO> fetchNewContactMsgs() {
    List<Contact> newContactMsgs = contactRepository.findByStatus(ApplicationConstants.CONTACT_STATUS_NEW);
    List<ContactResponseDTO> contactResponseDtos = convertEntityToResponseDTO(newContactMsgs);
    return contactResponseDtos;
   }

   private List<ContactResponseDTO> convertEntityToResponseDTO(List<Contact> newContactMsgs) {
      return newContactMsgs.stream().map(contact -> new ContactResponseDTO(contact.getId(), contact.getName(),
       contact.getEmail(), contact.getUserType(), contact.getSubject(), contact.getMessage(), 
       contact.getStatus(), contact.getCreatedAt())).toList();
   }

   @Override
   public Page<ContactResponseDTO> fetchContactMsgsWithPagination(int page, int size, String sortBy, String sortAsc) {

    Sort sort = sortAsc.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    PageRequest pageable = PageRequest.of(page, size, sort);
    Page<Contact> contactMsgsPage = contactRepository.findContactByStatus(pageable, ApplicationConstants.CONTACT_STATUS_NEW);
    Page<ContactResponseDTO> contactResponseDtosPage = contactMsgsPage.map(contact -> new ContactResponseDTO(contact.getId(), contact.getName(),
       contact.getEmail(), contact.getUserType(), contact.getSubject(), contact.getMessage(), 
       contact.getStatus(), contact.getCreatedAt()));
    return contactResponseDtosPage;    
  }


    @Transactional
    @Override
    public boolean updateContactMsgStatus(Long id, String status) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact == null) {
            return false;
        }
        contact.setStatus(status);
        contactRepository.save(contact);
        return true;
    }
}


