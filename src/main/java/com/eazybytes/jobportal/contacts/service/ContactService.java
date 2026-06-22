package com.eazybytes.jobportal.contacts.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.eazybytes.jobportal.dto.ContactDTO;
import com.eazybytes.jobportal.dto.ContactResponseDTO;

public interface ContactService {

  public Boolean saveContactDetails(ContactDTO contactDTO);
  List<ContactResponseDTO> fetchNewContactMsgs();
  public Page<ContactResponseDTO> fetchContactMsgsWithPagination(int page, int size, String sortBy, String sortAsc);
  public boolean updateContactMsgStatus(Long id, String status);
  
}
