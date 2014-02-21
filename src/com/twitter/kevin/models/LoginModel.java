package com.twitter.kevin.models;
import java.util.LinkedList;

import javax.sql.DataSource;

import java.sql.*;

import com.twitter.kevin.lib.*;
import com.twitter.kevin.stores.TweetStore;
import com.twitter.kevin.stores.UserStore;

public class LoginModel 
{
	private DataSource _ds = null;
	
	public LoginModel()
	{
		
	}
	
	public void setDataSource(DataSource _ds)
	{
		this._ds = _ds;
		System.out.println("Set Data Source in Model" + _ds.toString());
	}
	
	public UserStore LoginCheck(String uname, String pword)
	{
		LinkedList<UserStore> userList = new LinkedList<UserStore>();
		Connection conn;
		UserStore user = null;
		ResultSet resultSet = null;
		try
		{
			conn = _ds.getConnection();
		}
		catch(Exception e)
		{
			System.out.println("No Connection in Tweets Model");
			return null;
		}
		PreparedStatement pstmt = null;
		Statement stmt = null;
		String sqlQuery = "SELECT * FROM user;";
		try
		{
			try
			{
				stmt = conn.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return null;
			}
			System.out.println("Created prepare");
			try
			{
				resultSet = stmt.executeQuery(sqlQuery);
			}
			catch(Exception e)
			{
				System.out.println("Cannot execute query " + e);
				return null;
			}
			System.out.println("Statement executed");
			if (resultSet.wasNull())
			{
				System.out.println("Result set was null");
			}
			else
			{
				System.out.println("Users found");
			}
			while (resultSet.next())
			{
				System.out.println("User: " + resultSet.getString("Username"));
				System.out.println("Password: " + resultSet.getString("Password"));
				if (resultSet.getString("Username").equals(uname))
				{
					if (resultSet.getString("Password").equals(pword))
					{
						user = new UserStore();
						user.setPassword(pword);
						user.setUsername(uname);
						user.setEmail("temp");
						conn.close();
						return user;
					}
					else
					{
						return null;
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in query: " + e);
			return null;
		}
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			return null;
		}
		return null;
	}
}
