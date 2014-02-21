package com.twitter.kevin.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import com.mysql.jdbc.Statement;



public class DBUtils {

	private static final void listContext(Context ctx, String indent) {
		try {
			NamingEnumeration list = ctx.listBindings("");
			while (list.hasMore()) {
				Binding item = (Binding) list.next();
				String className = item.getClassName();
				String name = item.getName();
				System.out.println("" + className + " " + name);
				Object o = item.getObject();
				if (o instanceof javax.naming.Context) {
					listContext((Context) o, indent + " ");
				}
			}
		} catch (NamingException ex) {
			System.out.println("JNDI failure: " + ex);
		}
	}

	/**
	 * Assembles  a DataSource from JNDI.
	 */
	 public DataSource assemble(ServletConfig config) throws ServletException {
		DataSource _ds = null;
		String dataSourceName = config.getInitParameter("data-source");
		System.out.println("Data Source Parameter" + dataSourceName);
		if (dataSourceName == null)
			throw new ServletException("data-source must be specified");
		Context envContext = null;
		try {
			Context ic = new InitialContext();
			System.out.println("initial context " + ic.getNameInNamespace());
			envContext = (Context) ic.lookup("java:/comp/env");
			System.out.println("envcontext  " + envContext);
			listContext(envContext, "");
		} catch (Exception et) {
			throw new ServletException("Can't get contexts " + et);
		}
		// _ds = (DataSource) ic.lookup("java:"+dataSourceName);
		// _ds = (DataSource) ic.lookup("java:comp/env/" );
		try {
			_ds = (DataSource) envContext.lookup(dataSourceName);

			if (_ds == null)
				throw new ServletException(dataSourceName
						+ " is an unknown data-source.");
		} catch (NamingException e) {
			throw new ServletException("Cant find datasource name " +dataSourceName+" Error "+ e);
		}
		CreateSchema(_ds); // comment out if on local host
		return _ds;

	}

	// create the schema if it doesn't exist
	private void CreateSchema(DataSource _ds) {
		PreparedStatement pmst = null;
		Connection Conn;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {
			return;
		}
		String createStmt = "CREATE DATABASE IF NOT EXISTS `blabber` /*!40100 DEFAULT CHARACTER SET utf8 */;";
		try {
			pmst = Conn.prepareStatement(createStmt);
			pmst.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Can not create table "+ex);
			return;
		}
		String sqlQuery = "CREATE TABLE IF NOT EXISTS `user` (`User_ID` int(11) NOT NULL AUTO_INCREMENT,`Username` varchar(45) NOT NULL,`Password` varchar(45) NOT NULL,`Email` varchar(45) NOT NULL,PRIMARY KEY (`User_ID`),UNIQUE KEY `User_ID_UNIQUE` (`User_ID`),UNIQUE KEY `Email_UNIQUE` (`Email`),UNIQUE KEY `Username_UNIQUE` (`Username`)) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;";
		System.out.println(sqlQuery);
		try {
			pmst = Conn.prepareStatement(sqlQuery);
			pmst.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Can not create table 'USER' in BLABBER"+ex);
			return;
		}
		sqlQuery = "CREATE TABLE IF NOT EXISTS `following` (`Following_ID` int(11) NOT NULL AUTO_INCREMENT,`FollowedUser_ID` int(11) NOT NULL,`FollowingUser_ID` int(11) NOT NULL,PRIMARY KEY (`Following_ID`),UNIQUE KEY `Following_ID_UNIQUE` (`Following_ID`),KEY `FK_FollowingUser_idx` (`FollowingUser_ID`),KEY `FK_FollowedUser_idx` (`FollowedUser_ID`),CONSTRAINT `FK_FollowedUser` FOREIGN KEY (`FollowedUser_ID`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,CONSTRAINT `FK_FollowingUser` FOREIGN KEY (`FollowingUser_ID`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE=InnoDB AUTO_INCREMENT=805 DEFAULT CHARSET=utf8;";
		System.out.println(sqlQuery);
		try {
			pmst = Conn.prepareStatement(sqlQuery);
			pmst.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Can not create table 'FOLLOWING' in BLABBER"+ex);
			return;
		}
		sqlQuery = "CREATE TABLE IF NOT EXISTS `blab` (`Blab_ID` int(11) NOT NULL AUTO_INCREMENT,`Text` varchar(45) NOT NULL,`Image_Link` varchar(100) DEFAULT NULL,`Video_Embed` varchar(100) DEFAULT NULL,`User_ID` int(11) NOT NULL,`DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY (`Blab_ID`),UNIQUE KEY `Blab_ID_UNIQUE` (`Blab_ID`),KEY `FK_User_idx` (`User_ID`),CONSTRAINT `FK_UserID` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8;";
		System.out.println(sqlQuery);
		try {
			pmst = Conn.prepareStatement(sqlQuery);
			pmst.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Can not create table 'BLAB' in BLABBER"+ex);
			return;
		}
		ResultSet rs=null;
		/*sqlQuery="Select count(name) from author as rowcount";
		try {
			pmst = Conn.prepareStatement(sqlQuery);
			rs=pmst.executeQuery();
			if(rs.next()) {
			    int rows = rs.getInt(1);
			    System.out.println("Number of Rows " + rows);
			    if (rows==0){
			    	sqlQuery="INSERT INTO `author` (`name`) VALUES ('Andy'),('Tracey'),('Tom'),('Bill');";
					try {
						pmst = Conn.prepareStatement(sqlQuery);
						pmst.executeUpdate();
					} catch (Exception ex) {
						System.out.println("Can not insert names in authors "+ex);
						return;
					}
					sqlQuery="INSERT INTO `section` (`name`) VALUES ('Cassandra'),('Hadoop'),('Debian');";
					try {
						pmst = Conn.prepareStatement(sqlQuery);
						pmst.executeUpdate();
					} catch (Exception ex) {
						System.out.println("Can not insert names in sections "+ex);
						return;	
					}
					sqlQuery="INSERT INTO `fault` (`summary`,`details`,`author_idauthor`,`section_idsection`) VALUES ('Startup fails on a pi','Because the number of processors returned is zero startup fails','1','1');";
					try {
						pmst = Conn.prepareStatement(sqlQuery);
						pmst.executeUpdate();
					} catch (Exception ex) {
						System.out.println("Can not insert default fault "+ex);
						return;	
					}
			    }
			}

		} catch (Exception ex) {
			System.out.println("Can not select count "+ex);
			return;
		}

*/
	}
	
	
	public void createSchema(){
		String url = "jdbc:mysql://localhost";
		Connection conn=null;
		try {
		   Class.forName ("com.mysql.jdbc.Driver").newInstance ();
		   conn = DriverManager.getConnection (url, "root", "jimmygomis"); ////CHANGE PASSWORD HERE//////////////////

		}catch (Exception et){
			System.out.println("Can't get conenction to create schema "+et);
			return;
		}
		String sqlcreateSchema="CREATE DATABASE IF NOT EXISTS `blabber`;";
		try{
			java.sql.Statement statement=conn.createStatement();
			statement.execute(sqlcreateSchema);
			conn.close();
		}catch (Exception et){
			System.out.println("Can not create schema ");
			return;
		}

	}
	
}
