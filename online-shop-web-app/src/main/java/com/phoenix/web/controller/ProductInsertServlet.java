package com.phoenix.web.controller;

import java.io.IOException;

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
 * Servlet implementation class ProductInsertServlet
 */
@WebServlet("/ProductInsertServlet")
public class ProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		
		//product reference
		Product InsertProduct = new Product(); 
		
		//get all parameters
		InsertProduct.setId(Integer.parseInt(request.getParameter("ProductId")));
		InsertProduct.setName(request.getParameter("ProductName"));
		InsertProduct.setBrand(request.getParameter("ProductBrand"));
		InsertProduct.setPrice(Float.parseFloat(request.getParameter("ProductPrice")));
		
		//productserciceImpl
		ProductService product = new ProductServiceImpl();
		try {
			product.add(InsertProduct);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		HttpSession obj = request.getSession(false);
		if(obj!=null) {	
			//obj.setAttribute("products", products);
			response.sendRedirect("ProductAllServlet");
		}else {
			System.out.println("session not found");
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
