package com.homeAutomation.repository;

import org.springframework.data.repository.CrudRepository;

import com.homeAutomation.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
}
