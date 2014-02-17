<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="MainStyle.css" type="text/css">
<%  %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= request.getAttribute("username") %></title>
<h1><%= request.getAttribute("username") %></h1>
</head>
<body>
<% if (request.getAttribute("followed") == null)
{ 
%>
<form method="post" action="Follow">
<input type="hidden" name="username" value="<%=request.getAttribute("username") %>">
<input type="hidden" name="currentuser" value="<%=request.getAttribute("currentUser") %>">
<input type="hidden" name="follow" value="true">
<input type="submit" value="Follow">
</form>
<%
}
else
{
%>
	<form method="post" action="Follow">
	<input type="hidden" name="username" value="<%=request.getAttribute("username") %>">
	<input type="hidden" name="currentuser" value="<%=request.getAttribute("currentUser") %>">
	<input type="hidden" name="unfollow" value="true">
	<input type="submit" value="Unfollow">
	</form>
<% 
}
%>
</body>
</html>