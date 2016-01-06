package com.homeAutomation.service;

import java.util.List;

import com.homeAutomation.model.User;

public interface UserService {

	public User findById(Long userId);

	public User save(User user);

	public void delete(Long userId);
	
	public User update(Long userId, User user);
	
	public List<User> findAll();
}
