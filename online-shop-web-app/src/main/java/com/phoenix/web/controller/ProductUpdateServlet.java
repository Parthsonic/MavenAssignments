package com.phoenix.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phoenix.data.Product;
import com.phoenix.exceptions.ProductNotFoundException;
import com.phoenix.exceptions.ServiceException;
import com.phoenix.services.ProductService;
import com.phoenix.services.ProductServiceImpl;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/ProductUpdateServlet")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		//Product Reference
		Product product;
		HttpSession obj = request.getSession(false);
		if(obj!=null) {
			//get productId
			int productId = Integer.parseInt(request.getParameter("UpdateId"));
	
			//call update method
			ProductService productser = new ProductServiceImpl();
			
			//get product by id
			try {
				product = productser.findProductById(productId);
				List<Product> products = (List<Product>) obj.getAttribute("products");
				int i = products.indexOf(product);
				if(product!=null) {
					product.setName(request.getParameter("UpdateName"));
					product.setBrand(request.getParameter("UpdateBrand"));
					product.setPrice(Float.parseFloat(request.getParameter("UpdatePrice")));
					productser.edit(product);	
					products.set(i, product);
					obj.setAttribute("products", products);
				}
				else 
					System.out.println("Product Not Found!!!");
				//response.sendRedirect("ProductAllServlet");
				response.sendRedirect("products.jsp");
			} catch (ProductNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Session Expired!!!");
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
