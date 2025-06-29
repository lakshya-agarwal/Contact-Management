package com.assessment.contactmanager.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.contactmanager.dao.ContactDao;
import com.assessment.contactmanager.dto.ContactDTO;
import com.assessment.contactmanager.entity.ContactEntity;
import com.assessment.contactmanager.exception.ContactNotFoundException;
import com.assessment.contactmanager.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{
	
	
	@Autowired
	private ContactDao contactRepository;

	@Override
	public ContactDTO save(ContactDTO contact) {
        ContactEntity entity = mapToEntity(contact);
        ContactEntity saved = contactRepository.save(entity);
        return mapToDTO(saved);
	}

	@Override
	public ContactDTO update(Long id, ContactDTO contact) {
        ContactEntity existing = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));

        existing.setName(contact.getName());
        existing.setEmail(contact.getEmail());
        existing.setPhone(contact.getPhone());
        existing.setAddress(contact.getAddress());

        ContactEntity updated = contactRepository.save(existing);
        return mapToDTO(updated);
	}

	@Override
	public void delete(Long id) {
		 ContactEntity existing = contactRepository.findById(id)
	                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));
	        contactRepository.delete(existing);
		
	}

	@Override
	public ContactDTO get(Long id) {
		ContactEntity entity = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));
        return mapToDTO(entity);
	}

	@Override
	public List<ContactDTO> getAll() {
		 List<ContactEntity> allEntities = contactRepository.findAll();
	        return allEntities.stream()
	                .map(this::mapToDTO)
	                .collect(Collectors.toList());
	}
	
	
	@Override
	public long countContacts() {
	    return contactRepository.count();
	}
	
	
	 private ContactDTO mapToDTO(ContactEntity entity) {
	        ContactDTO dto = new ContactDTO();
	        dto.setName(entity.getName());
	        dto.setEmail(entity.getEmail());
	        dto.setAddress(entity.getAddress());
	        dto.setPhone(entity.getPhone());
	        return dto;
	    }

	    private ContactEntity mapToEntity(ContactDTO dto) {
	        return ContactEntity.builder()
	                .name(dto.getName())
	                .email(dto.getEmail())
	                .address(dto.getAddress())
	                .phone(dto.getPhone())
	                .build();
	    }

}
