<%@page import="com.phoenix.data.User"%>
<jsp:useBean id="ob" class = "com.phoenix.web.models.LoginBean" scope="request"></jsp:useBean>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Welcome</title>
	</head>

	<body>
		<%@ include file="header.html" %>

		
		<jsp:getProperty property="username" name="ob"/>
		
		<%
		String username  = request.getParameter("username");%>
		
		<h2>Welcome <%= username %> to the infinte timeline of phoenix</h2>
		
		<%
			session = request.getSession();
			session.setMaxInactiveInterval(300);
			session.setAttribute("uname",username);
		%>	
			<p>Session Timeout In : <%=session.getMaxInactiveInterval()%></p>
		<%
			String urlProductLink= response.encodeURL("ProductAllServlet");
		%>
			<h3>For Product Sale <a href=<%=urlProductLink%>>click here</a></h3>
		<%
			String changePasswordLink = response.encodeURL("ChangePassword.jsp");
		%>
			
			<p>For Change Password <a href=<%=changePasswordLink%>>Update Password</a> </p>
		<%
			String deleteAccountLink = response.encodeURL("DeleteUser.jsp");
		%>
			
			<p>For Delete Your Account <a href=<%=deleteAccountLink%>>Delete Account</a> </p>
			
		<%@ include file="footer.html" %>
	
	</body>
</html>