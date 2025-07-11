package com.assessment.contactmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor         // <-- Needed by JPA
@AllArgsConstructor        // <-- Needed for @Builder to work correctly
@Builder
public class ContactEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
    private String name;
	
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    @Column(unique = true)
    private String email;	
    
    @Embedded
    private Address address;
    
    
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;
    
    
    @Column(nullable = false)
    private String username; // Owner of this contact
    

}
