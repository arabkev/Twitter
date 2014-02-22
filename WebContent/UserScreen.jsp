<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="com.twitter.kevin.stores.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
head
{
	font-family:Verdana, Arial, sans-serif;
}
body
{
	background-color:#DEF6FC;
	background:url('http://fc02.deviantart.net/fs70/f/2013/165/8/5/ice_ice_baby___blue_stock_texture_by_jrmb_stock-d690u7c.png');
	background-size:cover;
}
a:link {text-decoration:none;}
a:visited {text-decoration:none;}
a:hover {text-decoration:underline; font-size:150%; color:white}
p
{
	font-family:Verdana, Arial, sans-serif;
}
h2
{
	font-family:"Century Gothic", Verdana, sans-serif;
}
</style>
<head>
<!--  <link rel="stylesheet" href="CSS/MainStyle.css" type="text/css">-->
<%  %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= request.getAttribute("username") %></title>
<h1>@<%= request.getAttribute("username") %></h1>
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
<hr>
<%
List<TweetStore> lTweet = (List<TweetStore>)request.getAttribute("Tweets");
if (lTweet==null){
		%>
		<p>No Tweet found</p>
	<% 
}
else
{
	Iterator<TweetStore> iterator;
	iterator = lTweet.iterator();     
	while (iterator.hasNext()){
		TweetStore ts = (TweetStore)iterator.next();
		%>
		<h2><%=ts.getTweet() %></h2>
		<%
		if (ts.getImage().equals("") == false)
		{
		%>
			<img src="<%=ts.getImage() %>">
		<%
		} 
		%>
			<p>Blabbed at <%=ts.getDateTime() %></p>
			<br>
		<%
	}
}
%>
</body>
</html>