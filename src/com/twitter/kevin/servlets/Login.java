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
 * Servlet implementation class Login
 */
@WebServlet(
		urlPatterns = { 
				"/Logins", 
				"/Login/*"
		}, 
		initParams = { 
				@WebInitParam(name = "data-source", value = "jdbc/blabber")
		})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;   
	HttpSession session = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		request.getRequestDispatcher("/LoginScreen.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loggedIn = "";
		LoginModel model = new LoginModel();
		model.setDataSource(_ds);
		UserStore user = model.LoginCheck(request.getParameter("uname"), request.getParameter("pword"));
		if (user != null)
		{
			System.out.println("User Found");
			session = request.getSession();
			session.setAttribute("username", user.getUsername());
			loggedIn = "true";
		}
		else
		{
			System.out.println("User Not Found");
			loggedIn = "false";
			session = request.getSession();
			session.removeAttribute("username");
		}
		request.setAttribute("loggedIn", loggedIn);
		request.getRequestDispatcher("/LoginScreen.jsp").forward(request, response);
	}

}
