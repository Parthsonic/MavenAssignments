
<%@page import="java.util.Collections"%>
<%@page import="com.phoenix.data.User"%>
<%@page import="com.phoenix.services.ProductService"%>
<%@page import="com.phoenix.services.ProductServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.phoenix.data.Product"%>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Welcome</title>
		<style>
			body {background-color: powderblue;}
			h1,h2,h3   {color: blue;}
			p    {color: red;}
			table{margin:2px auto;}
			table, th, td {
			  border: 1px solid black;
			  border-collapse: collapse;
			}
			th, td {
			  padding: 15px;
			  text-align: left;
			}
			#t01 tr:nth-child(even) {
			  background-color: #eee;
			}
			#t01 tr:nth-child(odd) {
			 background-color: #fff;
			}
			#t01 th {
			  background-color: black;
			  color: white;
			}
		</style>
	</head>
	<body style= 'text-align:center;'>
		<%@ include file="header.html" %>
		<%@ page session="false" %>
			<%
				HttpSession obj = request.getSession(false);
				
				if(obj != null) {
					String username = (String)obj.getAttribute("uname");
					List<Product> products = (List<Product>)obj.getAttribute("products"); 
					ProductService productServ = new ProductServiceImpl(); 
					Collections.sort(products);
					String s = request.getParameter("sorted");
					String searchVal = (String)obj.getAttribute("searchVal");
					for(Product product : products)System.out.println(product.getId());
			%>		 
					<h2> <%=username%> Welcome to Product Sale </h2>
					<table id="t01">
							<tr>
								<th>
									<form action="products.jsp">
										<label for="sort">Sort By:</label>
      									  <select name ="sorted" id="sorted">
										  <option value="notSelected" <%if(s==null || s.equals("notSelected")){%>selected<%}%>>not selected</option>
										  <option value="Name" <%if(s!=null){if(s.equals("Name")){%>selected<%}}%>>Name</option>
										  <option value="Brand" <%if(s!=null){if(s.equals("Brand")){%>selected<%}}%>>Brand</option>
										  <option value="Price" <%if(s!=null){if(s.equals("Price")){%>selected<%}}%>>Price</option>
										  <option value="Desc" <%if(s!=null){if(s.equals("Desc")){%>selected<%}}%>>PriceDesc</option>
										</select>
										<input type="submit" value="Apply">
									</form>
								</th>
								<th>
									<form action = "Search">
										<p>Search<input type = "text" name = "search" <%if(searchVal!=null) %>value = "<%=searchVal%>">
										<input name = "Submit" type="submit" value="Submit" ></p>
									</form>
								</th>
							</tr>
							<%					
								if(s!=null){
									if(s.equals("Name"))
										products = productServ.sortByName(products);
									else if(s.equals("Brand"))
										products = productServ.sortByBrand(products);
									else if(s.equals("Price"))
										products = productServ.sortByPrice(products);
									else if(s.equals("Desc"))
										products = productServ.sortByPriceDesc(products);
									else{
										s = null;
										//products = productServ.findAll();										
									}
								}
							%>
							
							<tr>
								<th>Product_Id</th>
								<th>Product_Name</th>
								<th>Product_Brand</th>
								<th>Product_Price</th>
							</tr>
						<%if(!products.isEmpty()){
							for(Product product : products){%>
							<tr>
								<td><%= product.getId() %></td>
								<td><%= product.getName() %></td>
								<td><%= product.getBrand() %></td>
								<td><%= product.getPrice() %></td>
							</tr>
						<%	} 
						}else {%>
							<tr>
								<h2 style='color:red'>Sorry Product not found!!!</h2>
							</tr>
						<%}%>
					</table>
					<div style='border:1px solid black;width:50%;border-radius:2px;margin:5px auto;padding:5px;'>
						<form action="ProductDeleteServlet">
							<p>Enter Id For delete an item : <input type = "text" name = "delete" required></p>
							<input type = "submit" value="Delet an Item">
						</form>
					</div>
					
					<div style='border:1px solid black;width:50%;border-radius:2px;margin:5px auto;padding:5px'>
						<form action="ProductInsertServlet">
							<p>Enter Id    : <input type = "text" name = "ProductId" required></p>
							<p>Enter Name  : <input type = "text" name = "ProductName" required></p>
							<p>Enter Brand : <input type = "text" name = "ProductBrand" required></p>
							<p>Enter Price : <input type = "text" name = "ProductPrice" required></p>
							<input type = "submit" value="Insert an Item">
						</form>
					</div>
					
					<div style='border:1px solid black;width:50%;border-radius:2px;margin:5px auto;padding:5px'>
						<form action="ProductUpdateServlet">
							<p>Enter Id For Update   : <input type = "text" name = "UpdateId" required></p>
							<p>Enter details you wanted to Update:</p>
							<p>Enter Name  : <input type = "text" name = "UpdateName" required></p>
							<p>Enter Brand : <input type = "text" name = "UpdateBrand" required></p>
							<p>Enter Price : <input type = "text" name = "UpdatePrice" required></p>
							<input type = "submit" value="Update an Item">
						</form>
					</div>
			<%	}
				else {%>
					<h3>Session Invalid!!!</h3>
			<%	}%>
		<%@ include file="footer.html" %>
	</body>
</html>