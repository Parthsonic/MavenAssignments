package com.phoenix.web.models;

import java.sql.SQLException;

import com.phoenix.data.User;
import com.phoenix.exceptions.UserNotFoundException;
import com.phoenix.services.LoginService;
import com.phoenix.services.LoginServiceImpl;

public class LoginBean {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isValid() {
		User user;
		LoginService login = new LoginServiceImpl();
		try {
			user = login.findUserById(username);
			if(user!=null && password.equals(user.getPassword())) {
				return true;
			}
			//invalid login
			else {
				return false;
			}
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
