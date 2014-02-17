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
<form name="RegisterForm" action="Register" method=post>
Username: <input type="text" name="uname">
<br>
Password: <input type="password" name="pword">
<br>
Email: <input type="text" name="email">
<input type="submit" value="Register">
<% 

if (request.getAttribute("registered") != null)
{
	
		%><p>USER COULD NOT BE REGISTERED</p><%

}
	
%>
</form>
<a href="LoginScreen.jsp">Go Back</a>
</body>
</html>