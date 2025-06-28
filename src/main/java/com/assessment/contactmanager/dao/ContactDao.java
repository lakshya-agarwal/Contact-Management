package com.assessment.contactmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.contactmanager.entity.ContactEntity;

public interface ContactDao extends JpaRepository<ContactEntity, Long> {

}
