<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSS/MainStyle.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blabber - Log In</title>
</head>
<body>
<h1>Log In</h1>
<br><br>
<form name="loginForm" action="Login" method=post>
<table>
<tr>
<td>Username: </td><td><input type="text" name="uname"></td>
</tr>
<tr>
 <td>Password: </td><td><input type="password" name="pword"></td>
</tr>
<tr>
<td><input type="submit" value="Log In"></td>
</tr>
</table>
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
<br>
<p>
<a href="RegisterScreen.jsp">Register New User</a>
<br>
<a href="Tweet">Go Back</a>
</p>
</body>
</html>