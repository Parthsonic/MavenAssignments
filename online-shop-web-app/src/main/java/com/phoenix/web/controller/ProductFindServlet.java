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
import com.phoenix.services.ProductServiceImpl;

/**
 * Servlet implementation class ProductFindServlet
 */
@WebServlet("/Search")
public class ProductFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		HttpSession obj = request.getSession(false);
		if(obj != null) {
			//List<Product> products = new ArrayList<Product>();// = (List<Product>)obj.getAttribute("products");
			String search = request.getParameter("search");
			String [] searcharr = search.split(" ");
			List<Product> products = new ArrayList<Product>();
			ProductServiceImpl proserv = new ProductServiceImpl();
			try {
				System.out.println(searcharr.length);
				for(int i = 0;i<searcharr.length;i++) {
					
					products.addAll(proserv.findByNameAndBrand(searcharr[i], searcharr[i]));
					
					//products.addAll(products1);				
				}
//				for(int i = 0;i<products.size();i++) 
//					System.out.println(products.get(i));
				if(!products.isEmpty()) {
					System.out.println("Product Found");
				}else System.out.println("Empty Products");
				obj.setAttribute("products", products);
				obj.setAttribute("searchVal", search);
				response.sendRedirect("products.jsp");
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
