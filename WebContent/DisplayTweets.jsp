<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.twitter.kevin.stores.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Twitter</title>
</head>
<body>

<h1>Twitter</h1>

<form name="tweetForm" action="/src/Tweet.java/">
Tweet: <input type="text" name="tweet">
<input type="submit" value="Submit">
</form>


<br>
<hr>
<br>
<%
System.out.println("In render");
List<TweetStore> lTweet = (List<TweetStore>)request.getAttribute("Tweets");
if (lTweet==null){
 %>
	<p>No Tweet found</p>
	<% 
}else{
%>


<% 
Iterator<TweetStore> iterator;


iterator = lTweet.iterator();     
while (iterator.hasNext()){
	TweetStore ts = (TweetStore)iterator.next();

	%>
	<a href="/Twitter/Tweet/<%=ts.getID() %>" ><%=ts.getTweet() %></a><br/>
	<p>Tweeted by @<%=ts.getUsername() %> at <%=ts.getDateTime() %></p>
	<br>
	<%

}
}
%>

</body>
</html>