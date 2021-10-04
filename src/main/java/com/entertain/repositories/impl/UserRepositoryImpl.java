package com.entertain.repositories.impl;

import java.util.List;

import com.entertain.entities.User;
import com.entertain.repositories.AbstractRepository;
import com.entertain.repositories.IUserRepository;

public class UserRepositoryImpl extends AbstractRepository<User> implements IUserRepository{

	@Override
	public User findById(Integer id) {
		return super.findById(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		String hql = "SELECT o FROM User o WHERE o.email = ?0";
		return super.findOne(User.class, hql, email);
	}

	@Override
	public User findByUsername(String username) {
		String hql = "SELECT o FROM User o WHERE o.username = ?0";
		return super.findOne(User.class, hql, username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		String hql = "SELECT o FROM User o WHERE o.username = ?0 AND o.password = ?1";
		return super.findOne(User.class, hql, username, password);
	}

	@Override
	public List<User> findAll() {
		return super.findAll(User.class, true);
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		return super.findAll(User.class, true, pageNumber, pageSize);
	}

}
