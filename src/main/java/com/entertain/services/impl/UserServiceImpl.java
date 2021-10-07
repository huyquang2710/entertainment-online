package com.entertain.services.impl;

import java.util.List;

import com.entertain.entities.User;
import com.entertain.repositories.IUserRepository;
import com.entertain.repositories.impl.UserRepositoryImpl;
import com.entertain.services.IUserService;

public class UserServiceImpl implements IUserService{
	
	private IUserRepository userRepo;
	
	public UserServiceImpl() {
		userRepo = new UserRepositoryImpl();
	}

	@Override
	public User findById(Integer id) {
		return userRepo.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User login(String username, String password) {
		return userRepo.findByUsernameAndPassword(username, password);
	}

	@Override
	public User resetPassword(String email) {
		User existUser = findByEmail(email);
		if(existUser != null) {
			// create new pass random 1000 -> 9999
			String ranPass = String.valueOf((int) (Math.random() * ((9999 - 1000) + 1)) + 1000);
			existUser.setPassword(ranPass);
			return userRepo.update(existUser);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		return userRepo.findAll(pageNumber, pageSize);
	}

	@Override
	public User create(String username, String password, String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setIsAdmin(Boolean.FALSE);
		user.setIsActive(Boolean.TRUE);
		
		return userRepo.create(user);
	}

	@Override
	public User update(User user) {
		return userRepo.update(user);
	}

	@Override
	public User delete(String username) {
		User user = userRepo.findByUsername(username);
		user.setIsActive(Boolean.FALSE);
		return userRepo.update(user);
	}

}
