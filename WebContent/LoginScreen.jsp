<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="MainStyle.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blabber - Log In</title>
</head>
<body>
<form name="loginForm" action="Login" method=post>
Username: <input type="text" name="uname">
<br>
Password: <input type="password" name="pword">
<input type="submit" value="Log In">
<% 

if (request.getAttribute("loggedIn") != null)
{
	if (session.getAttribute("username") != null)
	{
		%><p>Logged in as: <%= session.getAttribute("username") %></p><%
		request.setAttribute("justLoggedIn", "true");
		request.setAttribute("username", session.getAttribute("username"));
		System.out.println("After just loggin in the request is a " + request.getMethod());
		request.getRequestDispatcher("Tweet").forward(request, response);
	}
	else
	{
		%><p>INVALID LOGIN</p><%
	}
}
	
%>
</form>
<p>
<a href="RegisterScreen.jsp">Register New User</a>
<a href="Tweet">Go Back</a>
</p>
</body>
</html>