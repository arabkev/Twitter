package com.twitter.kevin.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import javax.sql.DataSource;

import com.twitter.kevin.stores.TweetStore;
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
		//String sqlQuery = "SELECT User_ID, Username FROM user WHERE Username='" + followeduser + "';";
		String sqlQuery = "SELECT User_ID, Username FROM user WHERE Username=?;";
		try
		{
			try
			{
				//stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setString(1, followeduser);
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				//resultSet = stmt.executeQuery(sqlQuery);
				resultSet = pstmt.executeQuery();
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
		//sqlQuery = "SELECT User_ID, Username FROM user WHERE Username='" + followinguser + "'";
		sqlQuery = "SELECT User_ID, Username FROM user WHERE Username=?;";
		try
		{
			try
			{
				//stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setString(1, followinguser);
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				//resultSet = stmt.executeQuery(sqlQuery);
				resultSet = pstmt.executeQuery();
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
		//sqlQuery = "INSERT INTO following(FollowingUser_ID, FollowedUser_ID) VALUES (" + followingID + ", " + followedID + ");";
		sqlQuery = "INSERT INTO following(FollowingUser_ID, FollowedUser_ID) VALUES (?,?);";
		try
		{
			try
			{
				//stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setInt(1, followingID);
				pstmt.setInt(2, followedID);
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				//stmt.executeUpdate(sqlQuery);
				pstmt.executeUpdate();
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
		//String sqlQuery = "SELECT User_ID, Username FROM user WHERE Username='" + followeduser + "';";
		String sqlQuery = "SELECT User_ID, Username FROM user WHERE Username=?;";
		try
		{
			try
			{
				//stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setString(1, followeduser);
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				//resultSet = stmt.executeQuery(sqlQuery);
				resultSet = pstmt.executeQuery();
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
		//sqlQuery = "SELECT User_ID, Username FROM user WHERE Username='" + followinguser + "';";
		sqlQuery = "SELECT User_ID, Username FROM user WHERE Username=?;";
		try
		{
			try
			{
				//stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setString(1, followinguser);
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				//resultSet = stmt.executeQuery(sqlQuery);
				resultSet = pstmt.executeQuery();
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
		//sqlQuery = "SELECT * FROM following WHERE FollowedUser_ID=" + followedID + " AND FollowingUser_ID=" + followingID + ";";
		sqlQuery = "SELECT * FROM following WHERE FollowedUser_ID=? AND FollowingUser_ID=?;";
		try
		{
			try
			{
				//stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setInt(1, followedID);
				pstmt.setInt(2, followingID);
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				//resultSet = stmt.executeQuery(sqlQuery);
				resultSet = pstmt.executeQuery();
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
		//String sqlQuery = "SELECT User_ID, Username FROM user WHERE Username='" + followeduser + "';";
		String sqlQuery = "SELECT User_ID, Username FROM user WHERE Username=?;";
		try
		{
			try
			{
				//stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setString(1, followeduser);
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				//resultSet = stmt.executeQuery(sqlQuery);
				resultSet = pstmt.executeQuery();
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
		//sqlQuery = "SELECT User_ID, Username FROM user WHERE Username='" + followinguser + "';";
		sqlQuery = "SELECT User_ID, Username FROM user WHERE Username=?;";
		try
		{
			try
			{
				//stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setString(1, followinguser);
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				//resultSet = stmt.executeQuery(sqlQuery);
				resultSet = pstmt.executeQuery();
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
		//sqlQuery = "DELETE FROM following WHERE FollowedUser_ID=" + followedID + " AND FollowingUser_ID=" + followingID + ";";
		sqlQuery = "DELETE FROM following WHERE FollowedUser_ID=? AND FollowingUser_ID=?;";
		System.out.println("UNFOLLOW QUERY: " + sqlQuery);
		try
		{
			try
			{
				//stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setInt(1, followedID);
				pstmt.setInt(2, followingID);
			}
			catch(Exception e)
			{
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try
			{
				//stmt.executeUpdate(sqlQuery);
				pstmt.executeUpdate();
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
	
	public LinkedList<TweetStore> getUserTweets(String username)
	{
		LinkedList<TweetStore> tweetList = new LinkedList<TweetStore>();
		Connection conn;
		TweetStore tweet = null;
		ResultSet resultSet = null;
		int id = 0;
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
		//String sqlQuery = "SELECT User_ID, Username FROM user WHERE Username='" + username + "';";
		String sqlQuery = "SELECT User_ID, Username FROM user WHERE Username=?;";
			System.out.println("User ID query: " + sqlQuery);
			try
			{
				try
				{
					//stmt = conn.createStatement();
					pstmt = conn.prepareStatement(sqlQuery);
					pstmt.setString(1, username);
				}
				catch(Exception e)
				{
					System.out.println("Can't create prepare statement");
					return null;
				}
				System.out.println("Created prepare");
				try
				{
					//resultSet = stmt.executeQuery(sqlQuery);
					resultSet = pstmt.executeQuery();
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
					id = resultSet.getInt("User_ID");
				}
			}
			catch(Exception e)
			{
				System.out.println("Error in query: " + e);
				return null;
			}
			//sqlQuery = "SELECT * FROM blab WHERE User_ID=" + id + ";";
			sqlQuery = "SELECT * FROM blab WHERE User_ID=?;";
			System.out.println("User Tweets query: " + sqlQuery);
			try
			{
				try
				{
					//stmt = conn.createStatement();
					pstmt = conn.prepareStatement(sqlQuery);
					pstmt.setInt(1, id);
				}
				catch(Exception e)
				{
					System.out.println("Can't create prepare statement");
					return null;
				}
				System.out.println("Created prepare");
				try
				{
					//resultSet = stmt.executeQuery(sqlQuery);
					resultSet = pstmt.executeQuery();
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
					tweet = new TweetStore();
					tweet.setText(resultSet.getString("Text"));
					tweet.setUser(resultSet.getString("User_ID"));
					tweet.setDateTime(resultSet.getString("DateTime"));
					//tweet.setUsername(resultSet.getString("Username"));
					tweet.setImage(resultSet.getString("Image_Link"));
					tweetList.add(tweet);
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
			return tweetList;
	}
	
}
