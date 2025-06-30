package com.assessment.contactmanager.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.contactmanager.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Long> {
	
    Optional<UserEntity> findByUsername(String username);

}
