
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session = "false"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	div{
		border:1px solid black;
	}
</style>
<meta charset="ISO-8859-1">
<title>ChangePassword</title>
</head>
<body>
	<div>
		<%
			HttpSession obj = request.getSession(false);
			if(obj==null){
				
				System.out.println("Session Is no longer available");
			}				
			%>
		<form action="UserUpdateServlet" method = "post">
			
			<p>Old Password : <input type = "password" name = "oldPassword"> </p>
			<p>New Password : <input type = "password" name = "NewPassword"> </p>
			<p>ReEnter New Password : <input type = "password" name = "ReNewPassword"> </p>
			<input name = "Submit" type="submit" value="Submit" >
		</form>
	</div>

</body>
</html>