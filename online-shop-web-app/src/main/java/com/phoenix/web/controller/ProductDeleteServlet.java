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
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/ProductDeleteServlet")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		ProductService product = new ProductServiceImpl();
		String delProduct = request.getParameter("delete");
		Product delP;
		try {
//			List<Product> products = new ArrayList<Product>();
//			products.remove(products)
			//products = product.findAll();
//			for(Product p: products) {
//				System.out.println(p.getId());
//			}
			
			//System.out.println(products);
			HttpSession obj = request.getSession(false);
			if(obj!=null) {	
				System.out.println("delproduct : " + delProduct);
				delP = product.findProductById(Integer.parseInt(delProduct));
				if(delP!=null) {
					product.remove(delP);
					List<Product> products = (List<Product>) obj.getAttribute("products");
					products.remove(delP);
//					for(int i = 0;i<products.size();i++) 
//						System.out.println(products.get(i).getId());
					obj.setAttribute("products", products);
				}
				else System.out.println("Sorry!! product could not found");
				//obj.setAttribute("products", products);
				//response.sendRedirect("ProductAllServlet");
				response.sendRedirect("products.jsp");
			}else {
				System.out.println("session not found");
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			//System.out.println("well not");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductNotFoundException e) {
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
