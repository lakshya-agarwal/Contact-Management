package com.assessment.contactmanager.service;

import java.util.List;

import com.assessment.contactmanager.dto.ContactDTO;

public interface ContactService {

	ContactDTO save(ContactDTO contact);

	ContactDTO update(Long id, ContactDTO contact);

	void delete(Long id);

	ContactDTO get(Long id);

	List<ContactDTO> getAll();
	
	long countContacts();

}
