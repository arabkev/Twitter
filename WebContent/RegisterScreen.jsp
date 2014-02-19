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
<h1>Register New User</h1>
<br>
<br>
<form name="RegisterForm" action="Register" method=post>
<table>
<tr>
<td>Username: </td><td><input type="text" name="uname"></td>
</tr>
<tr>
<td>Password: </td><td><input type="password" name="pword"></td>
</tr>
<tr>
<td>Email: </td><td><input type="text" name="email"></td>
</tr>
<tr>
<td><input type="submit" value="Register"></td>
</tr>
</table>
<% 

if (request.getAttribute("registered") != null)
{
	
		%><p>USER COULD NOT BE REGISTERED</p><%

}
	
%>
</form>
<br><br>
<a href="LoginScreen.jsp">Go Back</a>
</body>
</html>