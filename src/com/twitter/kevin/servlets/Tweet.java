package com.twitter.kevin.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//import com.datastax.driver.core.Cluster;


import com.twitter.kevin.lib.*;
import com.twitter.kevin.models.*;
import com.twitter.kevin.stores.*;

/**
 * Servlet implementation class Tweet
 */
@WebServlet(
		urlPatterns = { 
				"/Tweets", 
				"/Tweet/*"
		}, 
		initParams = { 
				@WebInitParam(name = "data-source", value = "jdbc/blabber")
		})

public class Tweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;
    //private Cluster cluster;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tweet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//cluster = CassandraHosts.getCluster();
    	DBUtils db = new DBUtils();
        _ds=db.assemble(config);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String args[]=Convertors.SplitRequestPath(request);
		Iterator<TweetStore> iterator;
		TweetModel tm= new TweetModel();
		//tm.setCluster(cluster);
		tm.setDataSource(_ds);
		LinkedList<TweetStore> tweetList = tm.getTweets();
		request.setAttribute("Tweets", tweetList); //Set a bean with the list in it
		RequestDispatcher rd = request.getRequestDispatcher("/DisplayTweets.jsp"); 

		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
