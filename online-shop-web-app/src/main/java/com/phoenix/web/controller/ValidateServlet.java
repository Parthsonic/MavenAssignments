package com.phoenix.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phoenix.web.models.LoginBean;

/**
 * Servlet implementation class ValidateServlet
 */
@WebServlet("/LoginS")
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("passwd");
		LoginBean ob1 = new LoginBean();
		ob1.setUsername(username);
		ob1.setPassword(password);
		
		if(ob1.isValid()) {
			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
			request.setAttribute("ob", ob1);
			rd.forward(request, response);
		}
		else{
			PrintWriter p = response.getWriter();
			
			//print message
			p.println("<h2 style='color:red'>Invalid Login</h2>");
			
			//stay on same page
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
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
