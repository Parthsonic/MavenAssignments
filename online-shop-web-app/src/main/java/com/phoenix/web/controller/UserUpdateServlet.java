package com.phoenix.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phoenix.data.User;
import com.phoenix.exceptions.ServiceException;
import com.phoenix.exceptions.UserNotFoundException;
import com.phoenix.services.LoginService;
import com.phoenix.services.LoginServiceImpl;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		User user;
		HttpSession obj = request.getSession(false);
		if(obj!=null){
			String username  = (String)obj.getAttribute("uname");
			String oldPass = request.getParameter("oldPassword");
			//System.out.println("username : " + user.getUsername());
			LoginService login = new LoginServiceImpl();
			try {
				//User user1 = new User();
				user = login.findUserById(username);
				if(user != null) {
					PrintWriter p = response.getWriter();
					if(user.getPassword().equals(oldPass)) {
						String newPassword = request.getParameter("NewPassword");
						String newRePassword = request.getParameter("ReNewPassword");
						if(newPassword.equals(newRePassword)) {
							user.setPassword(newPassword);
							login.ChangePassword(user);
							p.println("Yeah!!! New Password Changed");
							RequestDispatcher rd = request.getRequestDispatcher("login.html");
							rd.include(request, response);
						}else {
							//print message
							p.println("<h2 style='color:red'>Sorry!!! Both Passwords does not match</h2>");
							RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
							rd.include(request, response);
						}
					}
					else {
						p.println("<h2 style='color:red'>Please check your old password!!!</h2>");
						RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
						rd.include(request, response);
					}
					
				}
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}else {
		System.out.println("Session expired!!");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
