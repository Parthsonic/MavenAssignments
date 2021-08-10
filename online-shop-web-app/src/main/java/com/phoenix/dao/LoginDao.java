package com.phoenix.dao;
import java.util.List;

import com.phoenix.data.User;
/*
 * version 2.0
 * */
public interface LoginDao {
	List<User> getAll();
	User getUserById(String username);
	void insert(User user);
	void update(User user);
	void delete(User user);
}
