package com.twitter.kevin.stores;

import java.sql.Date;

public class TweetStore {
String Text;
String User;
String DateTime;
int ID;
String Username;

public String getTweet()
{
	return Text;
}

public String getUser()
{
	return User;
}

public String getDateTime()
{
	return DateTime;
}

public int getID()
{
	return ID;
}

public String getUsername()
{
	return Username;
}

public void setUsername(String Username)
{
	this.Username = Username;
}

public void setID(int ID)
{
	this.ID = ID;
}

public void setDateTime(String DateTime)
{
	this.DateTime = DateTime;
}

public void setText(String Tweet)
{
	this.Text = Tweet;
}

public void setUser(String User)
{
	this.User = User;
}

}
