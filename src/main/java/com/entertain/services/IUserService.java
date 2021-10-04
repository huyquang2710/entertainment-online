package com.entertain.services;

import java.util.List;

import com.entertain.entities.User;

public interface IUserService {
	User findById(Integer id);
	User findByEmail(String email);
	User findByUsername(String username);
	User login(String username, String password);
	User resetPassword(String email);
	List<User> findAll();
	List<User> findAll(int pageNumber, int pageSize);
	User create(String username, String password, String email);
	User update(User user);
	User delete(String username);
}
