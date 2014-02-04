package com.twitter.kevin.models;
import java.util.LinkedList;

import javax.sql.DataSource;

import java.sql.*;

import com.twitter.kevin.lib.*;
import com.twitter.kevin.stores.TweetStore;

public class TweetModel {
		private DataSource _ds = null;
		
		public TweetModel()
		{
			
		}
		
		public void setDataSource(DataSource _ds)
		{
			this._ds = _ds;
			System.out.println("Set Data Source in Model" + _ds.toString());
		}
		
		public LinkedList<TweetStore> getTweets()
		{
			LinkedList<TweetStore> tweetList = new LinkedList<TweetStore>();
			Connection conn;
			TweetStore tweet = null;
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
			String sqlQuery = "SELECT t1.*, t2.Username FROM blab AS t1 INNER JOIN user AS t2 ON t1.User_ID = t2.User_ID";
			System.out.println("Tweets Query: " + sqlQuery);
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
					System.out.println("Tweets found");
				}
				while (resultSet.next())
				{
					System.out.println("Getting ResultSet");
					tweet = new TweetStore();
					tweet.setText(resultSet.getString("Text"));
					tweet.setUser(resultSet.getString("User_ID"));
					tweet.setDateTime(resultSet.getString("DateTime"));
					tweet.setUsername(resultSet.getString("Username"));
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
