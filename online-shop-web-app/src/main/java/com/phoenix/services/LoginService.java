package com.phoenix.services;

import java.sql.SQLException;
import java.util.List;

import com.phoenix.data.User;
import com.phoenix.exceptions.ServiceException;
import com.phoenix.exceptions.UserNotFoundException;

public interface LoginService {
	List<User> findall() throws ServiceException, SQLException; 
	User findUserById(String username) throws UserNotFoundException, SQLException;
	void add(User user) throws SQLException;
	void edit(User user) throws ServiceException,SQLException; 
	void remove(User user)throws ServiceException,SQLException; 
	String ChangePassword(User user) throws ServiceException,SQLException; 
}
