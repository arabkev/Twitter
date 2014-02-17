package com.twitter.kevin.servlets;

import java.io.IOException;

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
		request.setAttribute("username", request.getParameter("username"));
		request.setAttribute("currentUser", request.getParameter("currentuser"));
		System.out.println("IN DOGET OF FOLLOW: user=" + request.getParameter("username") + ", currentuser=" + request.getParameter("currentuser")); 
		FollowModel model = new FollowModel();
		model.setDataSource(_ds);
		if (model.isFollowed((String)request.getParameter("username"), (String)request.getParameter("currentuser")))
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
		request.getRequestDispatcher("Tweet").forward(request, response);
	}

}
