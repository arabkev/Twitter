package com.twitter.kevin.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.twitter.kevin.stores.UserStore;

public class FollowModel {

private DataSource _ds = null;
	
	public FollowModel()
	{
		
	}
	
	public void setDataSource(DataSource _ds)
	{
		this._ds = _ds;
		System.out.println("Set Data Source in Model" + _ds.toString());
	}
	
	public boolean FollowUser(String followeduser, String followinguser)
	{
		Connection conn;
		ResultSet resultSet = null;
		int followedID = 0, followingID = 0;
		try
		{
			conn = _ds.getConnection();
		}
		catch(Exception e)
		{
			System.out.println("No Connection in Tweets Model");
			return false;
		}
		PreparedStatement pstmt = null;
		Statement stmt = null;
		String sqlQuery = "SELECT User_ID, Username FROM USER WHERE Username='" + followeduser + "'";
		try
		{
			try
			{
				stmt = conn.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				resultSet = stmt.executeQuery(sqlQuery);
			}
			catch(Exception e)
			{
				System.out.println("Cannot execute query " + e);
				return false;
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
				followedID = resultSet.getInt("User_ID");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in query: " + e);
			return false;
		}
		sqlQuery = "SELECT User_ID, Username FROM USER WHERE Username='" + followinguser + "'";
		try
		{
			try
			{
				stmt = conn.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				resultSet = stmt.executeQuery(sqlQuery);
			}
			catch(Exception e)
			{
				System.out.println("Cannot execute query " + e);
				return false;
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
				followingID = resultSet.getInt("User_ID");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in query: " + e);
			return false;
		}
		sqlQuery = "INSERT INTO following(FollowingUser_ID, FollowedUser_ID) VALUES (" + followingID + ", " + followedID + ")";
		try
		{
			try
			{
				stmt = conn.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				stmt.executeUpdate(sqlQuery);
			}
			catch(Exception e)
			{
				System.out.println("Cannot execute query " + e);
				return false;
			}
			System.out.println("Statement executed");
		}
		catch(Exception e)
		{
				System.out.println("Error in query: " + e);
				return false;
		}
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public boolean isFollowed(String followeduser, String followinguser)
	{
		System.out.println("CHECKING IF " + followinguser + " FOLLOWS " + followeduser);
		Connection conn;
		boolean isFound = false;
		ResultSet resultSet = null;
		int followedID = 0, followingID = 0;
		try
		{
			conn = _ds.getConnection();
		}
		catch(Exception e)
		{
			System.out.println("No Connection in Tweets Model");
			return false;
		}
		PreparedStatement pstmt = null;
		Statement stmt = null;
		String sqlQuery = "SELECT User_ID, Username FROM USER WHERE Username='" + followeduser + "'";
		try
		{
			try
			{
				stmt = conn.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				resultSet = stmt.executeQuery(sqlQuery);
			}
			catch(Exception e)
			{
				System.out.println("Cannot execute query " + e);
				return false;
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
				followedID = resultSet.getInt("User_ID");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in query: " + e);
			return false;
		}
		sqlQuery = "SELECT User_ID, Username FROM USER WHERE Username='" + followinguser + "'";
		try
		{
			try
			{
				stmt = conn.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				resultSet = stmt.executeQuery(sqlQuery);
			}
			catch(Exception e)
			{
				System.out.println("Cannot execute query " + e);
				return false;
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
				followingID = resultSet.getInt("User_ID");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in query: " + e);
			return false;
		}
		System.out.println("FOLLOWING USER ID = " + followingID + ", FOLLOWED USER ID = " + followedID);
		sqlQuery = "SELECT * FROM following WHERE FollowedUser_ID=" + followedID + " AND FollowingUser_ID=" + followingID;
		try
		{
			try
			{
				stmt = conn.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				resultSet = stmt.executeQuery(sqlQuery);
			}
			catch(Exception e)
			{
				System.out.println("Cannot execute query " + e);
				return false;
			}
			System.out.println("Statement executed");
			if (resultSet.wasNull())
			{
				System.out.println("Result set was null");
				//isFound = false;
			}
			else
			{
				//isFound = true;
			}
			while(resultSet.next())
			{
				isFound = false;
				if (resultSet.getInt("FollowingUser_ID") == followingID && resultSet.getInt("FollowedUser_ID") == followedID)
				{
					System.out.println("FOUND FOLLOWING");
					isFound = true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in query: " + e);
			
		}
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			
		} 
		System.out.println(isFound);
		return isFound;
	}
	
	
	public boolean unfollowUser(String followeduser, String followinguser)
	{
		System.out.println(followinguser + " will now unfollow " + followeduser);
		Connection conn;
		boolean isFound = false;
		ResultSet resultSet = null;
		int followedID = 0, followingID = 0;
		try
		{
			conn = _ds.getConnection();
		}
		catch(Exception e)
		{
			System.out.println("No Connection in Tweets Model");
			return false;
		}
		PreparedStatement pstmt = null;
		Statement stmt = null;
		String sqlQuery = "SELECT User_ID, Username FROM USER WHERE Username='" + followeduser + "'";
		try
		{
			try
			{
				stmt = conn.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				resultSet = stmt.executeQuery(sqlQuery);
			}
			catch(Exception e)
			{
				System.out.println("Cannot execute query " + e);
				return false;
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
				followedID = resultSet.getInt("User_ID");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in query: " + e);
			return false;
		}
		sqlQuery = "SELECT User_ID, Username FROM USER WHERE Username='" + followinguser + "'";
		try
		{
			try
			{
				stmt = conn.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				resultSet = stmt.executeQuery(sqlQuery);
			}
			catch(Exception e)
			{
				System.out.println("Cannot execute query " + e);
				return false;
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
				followingID = resultSet.getInt("User_ID");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in query: " + e);
			return false;
		}
		sqlQuery = "DELETE FROM following WHERE FollowedUser_ID=" + followedID + " AND FollowingUser_ID=" + followingID;
		System.out.println("UNFOLLOW QUERY: " + sqlQuery);
		try
		{
			try
			{
				stmt = conn.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				stmt.executeUpdate(sqlQuery);
			}
			catch(Exception e)
			{
				System.out.println("Cannot execute query " + e);
				return false;
			}
			System.out.println("Statement executed");
		}
		catch(Exception e)
		{
				System.out.println("Error in query: " + e);
				return false;
		}
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
}
