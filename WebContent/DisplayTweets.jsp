<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.twitter.kevin.stores.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSS/MainStyle.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Blabber - Home</title>

<%
if (session.getAttribute("username") == null)
{
%><a href="Login">Log In</a><%
}
else
{
	%><p>Logged in as: <%= session.getAttribute("username") %></p>
	<form method="post" action="Logout">
	<input type = "submit" value = "Log Out">
	</form><%		
}

%>
<br><br>
</head>
<body >

<img src="http://s3.postimg.org/3m969vs8j/cooltext1415119768.png" alt="Blabber">

<%
if (session.getAttribute("username") != null)
{
%>
<form name="tweetForm" action="Tweet" method=post>
Blab: <input type="text" name="tweet">
Image URL: <input type="text" name="image">
<input type="hidden" name="username" value="<%=session.getAttribute("username") %>">
<input type="submit" value="Submit">
</form>
<br>
<%if (request.getAttribute("allTweetsViewed") == null)
	{
	if (request.getAttribute("startup") != null)
	{
		request.removeAttribute("startup");
	}
	%>
<form name="viewAllTweets" action="Tweet" method=post>
<input type="hidden" name="allTweets" value="true">
<input type="submit" value="View All Tweets">
</form>
<%
	}
else
{%>
	<form name="viewFollowers" action="Tweet" method=post>
	<input type="hidden" name="followers" value="true">
	<input type="submit" value="View Followings' Tweets">
	</form><%
}
}
%>


<br>
<hr>
<br>
<%
if (session.getAttribute("username") == null)
{
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
			if (ts.getImage() != null)
			{
			%>
				<img src="<%=ts.getImage() %>">
			<%
			} 
			%>
				<p>Blabbed by @<%=ts.getUsername() %> at <%=ts.getDateTime() %></p>
				<br>
			<%
		}
	}
}
else
{
	List<TweetStore> lTweet = (List<TweetStore>)request.getAttribute("Tweets");
	System.out.println("Logged in, got tweets of followers");
	//request.setAttribute("username", session.getAttribute("username"));
	//request.getRequestDispatcher("Tweet").forward(request, response);
	Iterator<TweetStore> iterator;
	iterator = lTweet.iterator();     
	while (iterator.hasNext()){
		TweetStore ts = (TweetStore)iterator.next();
		%>
		<h2><%=ts.getTweet() %></h2>
		<%
		if (ts.getImage() != null)
		{
		%>
			<img src="<%=ts.getImage() %>">
		<%
		} 
		%>
			<p>Blabbed by <a href="/Twitter/Follow/<%=ts.getUsername() %>">@<%=ts.getUsername() %></a> at <%=ts.getDateTime() %></p>
			<!-- 
			<p>Blabbed by <a href="Follow?username=<%=ts.getUsername() %>&currentUser=<%=session.getAttribute("username") %>">@<%=ts.getUsername() %></a> at <%=ts.getDateTime() %></p>
			--><br>
		<%
	}
}
%>

</body>
</html>