package com.assessment.contactmanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.contactmanager.entity.ContactEntity;

public interface ContactDao extends JpaRepository<ContactEntity, Long> {

	
	List<ContactEntity> findByUsername(String username);

}
