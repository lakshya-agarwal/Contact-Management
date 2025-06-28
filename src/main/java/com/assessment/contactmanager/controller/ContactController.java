package com.assessment.contactmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.contactmanager.dto.ContactDTO;
import com.assessment.contactmanager.service.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private  ContactService contactService;
	
	@PostMapping
	public ResponseEntity<ContactDTO> createContact(@RequestBody @Valid ContactDTO contact){
		
		
		ContactDTO result = contactService.save(contact);
		return new ResponseEntity<>(result, HttpStatus.CREATED) ;
		
	}
}
