package com.assessment.contactmanager.dto;

import com.assessment.contactmanager.entity.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactDTO {
	
	private long id;

	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	private String phone;
	private Address address;

}
