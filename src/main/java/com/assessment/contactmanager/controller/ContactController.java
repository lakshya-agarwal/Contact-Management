package com.assessment.contactmanager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/{id}")
    public ResponseEntity<ContactDTO> update(@PathVariable Long id, @Valid @RequestBody ContactDTO contact) {
        return ResponseEntity.ok(contactService.update(id, contact));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAll() {
        return ResponseEntity.ok(contactService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getContactCount() {
        long count = contactService.countContacts();
        return ResponseEntity.ok(Map.of("totalContacts", count));
    }
	
	
}
