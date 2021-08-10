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
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
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
			String username = (String)obj.getAttribute("uname");
			String pass = request.getParameter("VerifyPassword");
			//System.out.println("username : " + user.getUsername());
			LoginService login = new LoginServiceImpl();
			try {
				user = login.findUserById(username);
				if(user != null) {
					if(user.getPassword().equals(pass)) {
						login.remove(user);
						System.out.println("User Removed Successfully!!!");
						obj.invalidate();
						response.sendRedirect("login.html");
					}else {
						PrintWriter p = response.getWriter();
						p.println("<h2 style='color:red'>Please check your password!!!</h2>");
						RequestDispatcher rd = request.getRequestDispatcher("DeleteUser.jsp");
						rd.include(request, response);
					}
				}	
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("session invalid");
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
