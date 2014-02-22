package com.twitter.kevin.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.twitter.kevin.stores.UserStore;

public class RegisterModel {
	
	private DataSource _ds = null;
	
	public RegisterModel()
	{
		
	}
	
	public void setDataSource(DataSource _ds)
	{
		this._ds = _ds;
		System.out.println("Set Data Source in Model" + _ds.toString());
	}
	
	public boolean RegisterUser(String username, String password, String email)
	{
		Connection conn;
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
		//String sqlQuery = "INSERT INTO user(Username, Password, Email) VALUES ('" + username + "', '" + password + "', '" + email + "');";
		String sqlQuery = "INSERT INTO user(Username, Password, Email) VALUES (?,?,?);";
		System.out.println(username + password + email);
		try
		{
			try
			{
				//stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				pstmt.setString(3, email);
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
}
