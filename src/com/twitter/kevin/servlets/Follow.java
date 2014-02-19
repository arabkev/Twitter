package com.twitter.kevin.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.twitter.kevin.lib.*;
import com.twitter.kevin.models.*;
import com.twitter.kevin.stores.*;


/**
 * Servlet implementation class Follow
 */
@WebServlet(
		urlPatterns = { 
				"/Follows", 
				"/Follow/*"
		}, 
		initParams = { 
				@WebInitParam(name = "data-source", value = "jdbc/blabber")
		})
public class Follow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null; 
    HttpSession session = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Follow() {
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
		//request.setAttribute("username", request.getParameter("username"));
		System.out.println("REQUEST URL: " + request.getRequestURI());
		String[] urlBits = request.getRequestURI().split("/");
		request.setAttribute("username", urlBits[3]);
		//request.setAttribute("currentUser", request.getParameter("currentuser"));
		request.setAttribute("currentUser", request.getSession().getAttribute("username"));
		System.out.println(request.getAttribute("currentUser") + " IS VIEWING " + request.getAttribute("username"));
		System.out.println("IN DOGET OF FOLLOW: user=" + request.getAttribute("username") + ", currentUser=" + request.getAttribute("currentUser")); 
		FollowModel model = new FollowModel();
		model.setDataSource(_ds);
		//if (model.isFollowed((String)request.getAttribute("username"), (String)request.getAttribute("currentUser")))
		if (model.isFollowed(urlBits[3], (String)request.getSession().getAttribute("username")))
		{
			System.out.println("FOLLOWING " + request.getParameter("username"));
			request.removeAttribute("notFollowed");
			request.setAttribute("followed", "true");
		}
		else
		{
			System.out.println("NOT FOLLOWING " + request.getParameter("username"));
			request.removeAttribute("followed");
			request.setAttribute("notFollowed", "true");
		}
		LinkedList<TweetStore> tweetList;
		tweetList = model.getUserTweets(urlBits[3]);
		request.setAttribute("Tweets", tweetList);
		request.getRequestDispatcher("/UserScreen.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("IN DOPOST OF FOLLOW: User=" + request.getParameter("username") + ", currentuser=" + request.getParameter("currentuser"));
		FollowModel model = new FollowModel();
		model.setDataSource(_ds);
		if (request.getParameter("follow") != null)
		{
			if (model.FollowUser((String)request.getParameter("username"), (String)request.getParameter("currentuser")))
			{
				System.out.println("Successfully Followed");
			}
			else
			{
				System.out.println("Could not follow user");
			}
		}
		else
		{
			if (model.unfollowUser((String)request.getParameter("username"), (String)request.getParameter("currentuser")))
			{
				System.out.println("Successfully unfollowed");
			}
			else
			{
				System.out.println("Could not unfollow user");
			}
		}
		session = request.getSession();
		session.setAttribute("username", request.getParameter("currentuser"));
		request.setAttribute("loggedIn", "true");
		response.sendRedirect("/Twitter/Tweet");
		//request.getRequestDispatcher("Tweet").forward(request, response);
	}

}
