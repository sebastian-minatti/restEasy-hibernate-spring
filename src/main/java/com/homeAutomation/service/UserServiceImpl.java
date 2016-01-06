package com.homeAutomation.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.homeAutomation.model.User;
import com.homeAutomation.repository.UserRepository;

@Service("userService")
@Repository
@Transactional
public class UserServiceImpl extends BaseService implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public User findById(Long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public User save(User user) {
		validate(user);
		return userRepository.save(user);
	}

	@Override
	public void delete(Long userId) {
		if(userRepository.exists(userId)){}{
			userRepository.delete(userId);
		}		
	}

	@Override
	public User update(Long userId, User user) {		
		User userToUpdate = userRepository.findOne(userId);
		userToUpdate.setEmailAddress(user.getEmailAddress());
		userToUpdate.setFirstName(user.getFirstName());
		userToUpdate.setLastName(user.getLastName());
		userToUpdate.getHomes().clear();
		userToUpdate.setHomes(user.getHomes());
		userToUpdate.setDateUpdated(new Date());
		
		return userRepository.save(userToUpdate);		
	}

	@Override
	public List<User> findAll() {
		return Lists.newArrayList(userRepository.findAll());
	}

}
