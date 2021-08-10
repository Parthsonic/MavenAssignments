package com.phoenix.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phoenix.data.Product;
import com.phoenix.exceptions.ServiceException;
import com.phoenix.services.ProductService;
import com.phoenix.services.ProductServiceImpl;

/**
 * Servlet implementation class ProductAllServlet
 */
@WebServlet("/ProductAllServlet")
public class ProductAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		ProductService product = new ProductServiceImpl();
		
		try {
			List<Product> products = new ArrayList<Product>();
			products = product.findAll();
			//System.out.println(products);
			HttpSession obj = request.getSession(false);
			if(obj!=null) {	
				obj.setAttribute("products", products);
				response.sendRedirect("products.jsp");
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("well not");
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
