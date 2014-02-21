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
 * Servlet implementation class Register
 */
@WebServlet(
		urlPatterns = { 
				"/Registers", 
				"/Register/*"
		}, 
		initParams = { 
				@WebInitParam(name = "data-source", value = "jdbc/blabber")
		})
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null; 
    HttpSession session = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//cluster = CassandraHosts.getCluster();
    	DBUtils db = new DBUtils();
    	db.createSchema();
        _ds=db.assemble(config);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RegisterModel model = new RegisterModel();
		model.setDataSource(_ds);
		if (model.RegisterUser((String)request.getParameter("uname"), (String)request.getParameter("pword"), (String)request.getParameter("email")))
		{
			System.out.println("Successfully Registered");
			session = request.getSession();
			session.setAttribute("username", request.getParameter("uname"));
			request.setAttribute("loggedIn", "true");
			request.getRequestDispatcher("Tweet").forward(request, response);
		}
		else
		{
			System.out.println("Could not register user");
			request.setAttribute("registered", "false");
			request.getRequestDispatcher("/RegisterScreen.jsp").forward(request, response);
		}
	}

}
