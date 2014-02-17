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
		
		/*Iterator<TweetStore> iterator;
		TweetModel tm= new TweetModel();
		//tm.setCluster(cluster);
		tm.setDataSource(_ds);
		LinkedList<TweetStore> tweetList = tm.getTweets();
		request.setAttribute("Tweets", tweetList); //Set a bean with the list in it
		RequestDispatcher rd = request.getRequestDispatcher("/DisplayTweets.jsp"); 
		rd.forward(request, response);*/
		request.setAttribute("startup", "true");
				//if (request.getAttribute("justLoggedIn") != null)
				if (request.getSession().getAttribute("username") != null)
				//if (request.getAttribute("username") != null)
				{
					System.out.println("DETECTED LOGIN");
					//if (request.getAttribute("justLoggedIn").equals("true"))
					//{
					request.removeAttribute("justLoggedIn");
					Iterator<TweetStore> iterator;
					TweetModel tm= new TweetModel();
					//tm.setCluster(cluster);
					tm.setDataSource(_ds);
					LinkedList<TweetStore> tweetList;
					/*if (request.getAttribute("username") == null)
					{
						tweetList = tm.getTweets();
					}
					else
					{*/
						tweetList = tm.getTweetsLoggedIn((String)request.getSession().getAttribute("username"));
					//}
					request.setAttribute("Tweets", tweetList); //Set a bean with the list in it
					RequestDispatcher rd = request.getRequestDispatcher("/DisplayTweets.jsp"); 
					rd.forward(request, response);
					//}
				}
				else
				{
				TweetModel tm = new TweetModel();
				tm.setDataSource(_ds);
				if (request.getParameter("tweet") != null)
				{
					tm.postTweet(request.getParameter("tweet"), request.getParameter("username"));
				}
				Iterator<TweetStore> iterator;
				TweetModel tm2= new TweetModel();
				//tm.setCluster(cluster);
				tm2.setDataSource(_ds);
				LinkedList<TweetStore> tweetList = tm2.getTweets();
				request.setAttribute("Tweets", tweetList); //Set a bean with the list in it
				RequestDispatcher rd = request.getRequestDispatcher("/DisplayTweets.jsp"); 
				rd.forward(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//if (request.getAttribute("justLoggedIn") != null)
		if (request.getSession().getAttribute("username") != null && request.getParameter("allTweets") == null)
		//if (request.getAttribute("username") != null)
		{
			System.out.println("DETECTED LOGIN");
			//if (request.getAttribute("justLoggedIn").equals("true"))
			//{
			request.removeAttribute("justLoggedIn");
			Iterator<TweetStore> iterator;
			TweetModel tm= new TweetModel();
			//tm.setCluster(cluster);
			tm.setDataSource(_ds);
			if (request.getParameter("tweet") != null)
			{
				tm.postTweet(request.getParameter("tweet"), request.getParameter("username"));
			}
			LinkedList<TweetStore> tweetList;
			/*if (request.getAttribute("username") == null)
			{
				tweetList = tm.getTweets();
			}
			else
			{*/
				tweetList = tm.getTweetsLoggedIn((String)request.getSession().getAttribute("username"));
			//}
			request.setAttribute("Tweets", tweetList); //Set a bean with the list in it
			//request.setAttribute("followers", "true");
			RequestDispatcher rd = request.getRequestDispatcher("/DisplayTweets.jsp"); 
			rd.forward(request, response);
			//}
		}
		else
		{
		//System.out.println("POSTING TWEET...SESSION USERNAME=" + request.getParameter("username"));
		TweetModel tm = new TweetModel();
		tm.setDataSource(_ds);
		Iterator<TweetStore> iterator;
		TweetModel tm2= new TweetModel();
		//tm.setCluster(cluster);
		tm2.setDataSource(_ds);
		LinkedList<TweetStore> tweetList = tm2.getTweets();
		request.setAttribute("Tweets", tweetList); //Set a bean with the list in it
		if (request.getParameter("allTweets") != null)
		{
			request.setAttribute("allTweetsViewed", "true");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/DisplayTweets.jsp"); 
		rd.forward(request, response);
		}
	}

}
