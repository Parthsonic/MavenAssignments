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

import com.phoenix.data.User;
import com.phoenix.exceptions.UserNotFoundException;
import com.phoenix.services.LoginService;
import com.phoenix.services.LoginServiceImpl;

/**
 * Servlet implementation class UserSignUpServlet
 */
@WebServlet("/UserSignUpServlet")
public class UserSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		User user = new User();
		user.setUsername(request.getParameter("userName"));
		user.setPassword(request.getParameter("passWord"));
		LoginService login = new LoginServiceImpl() ;
		System.out.println("usr: " + user.getUsername());
		try {
			if(login.findUserById(user.getUsername())==null) {
				login.add(user);
				System.out.println("User Added");
				response.sendRedirect("login.html");
			}else {
				PrintWriter p = response.getWriter();
				p.println("<h2>Username already exists</h2>");
				RequestDispatcher rd = request.getRequestDispatcher("SignUp.html");
				rd.include(request, response);
			}
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
