package com.phoenix.services;

import java.util.List;

import com.phoenix.dao.LoginDao;
import com.phoenix.dao.LoginDaoImpl;
import com.phoenix.data.User;
import com.phoenix.exceptions.ServiceException;
import com.phoenix.exceptions.UserNotFoundException;

/*
 * version 2.0
 * */
public class LoginServiceImpl implements LoginService {

	private LoginDao loginDao;
	
	public LoginServiceImpl() {
		loginDao = new LoginDaoImpl();
	}

	@Override
	public List<User> findall() throws ServiceException {
		return loginDao.getAll();
	}

	@Override
	public User findUserById(String username) throws UserNotFoundException {
		User user =  loginDao.getUserById(username);
		if(user != null)
			return user;
		else
			return null;
	}

	@Override
	public void add(User user){
		loginDao.insert(user);
	}

	@Override
	public void edit(User user) throws ServiceException {
		loginDao.update(user);
	}

	@Override
	public void remove(User user) throws ServiceException {
		loginDao.delete(user);
		
	}

	@Override
	public String ChangePassword(User user) throws ServiceException {
		// TODO Auto-generated method stub
		edit(user);
		return null;
	}

}
