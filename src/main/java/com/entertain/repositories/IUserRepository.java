package com.entertain.repositories;

import java.util.List;

import com.entertain.entities.User;

public interface IUserRepository {
	User findById(Integer id);
	User findByEmail(String email);
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
	List<User> findAll();
	List<User> findAll(int pageNumber, int pageSize);
	User create(User user);
	User update(User user);
	User delete(User user);
}
