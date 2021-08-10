<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DeleteAccount</title>
</head>
<body>
	<div>
		<%
			HttpSession obj = request.getSession(false);
			if(obj!=null){
				
				System.out.println("Session Is no longer available");
			}				
			%>
		<form action="UserDeleteServlet" method = "post">
			<p>Enter Password : <input type = "password" name = "VerifyPassword"> </p>
			<input name = "Submit" type="submit" value="Submit" >
		</form>
	</div>
</body>
</html>