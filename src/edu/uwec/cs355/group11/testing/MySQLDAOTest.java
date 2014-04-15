/*
 * MySQLDAO - DAO class that persists to a MySQL database
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package edu.uwec.cs355.group11.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.uwec.cs355.group11.database.DAOInterface;
import edu.uwec.cs355.group11.utilities.ErrorLogs;


public class MySQLDAOTest implements DAOInterface {
	// methods
	   // --- set the username and password
	   String user = "CS355GROUP11";		// bypass call to readEntry	
	   String pass = "B334876$";
	   Connection conn = null;
	   Statement stmt = null;
	   ResultSet rset = null;
	   PreparedStatement pstmt = null;
	   ErrorLogs errorLogs = new ErrorLogs();
	
	public int connect() {
		System.out.println("Connecting to the database...");
		int errorCode = 0;
		// load MySQL driver
		 // --- 1) get the Class object for the driver 
		   try
		   {
			  Class.forName ("com.mysql.jdbc.Driver");
			  System.out.println("Found/loaded MySQL Connector JDBC driver");	   
		   }
		   catch (ClassNotFoundException e)
		   {
			  System.out.println ("Could not get class object for Driver");
			  System.out.println(e.getMessage());
			  System.exit(1);
		   }

		   // create connection to MySQL database
		   // --- 2) connect to database
		   conn = null;
		   try
		   {
			  System.out.println("Connected to DB");
			  conn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/cs355group11",user,pass);
		   }
		   catch (SQLException sqle)
		   {
			  errorLogs.getErrorMsgs().add("DATABASE ERROR: Could not make connection to database");
			  System.out.println ("DATABASE ERROR: Could not make connection to database");
			  System.out.println(sqle.getMessage());
			  errorCode = 1;
			 // System.exit(1);
		   }
		   return errorCode;
	}

	public int executeUpdate(String query) {
		int resultCode = 0;			// result code from SQL update
		System.out.println("MySQL DAO execute");
		 // --- 3) prepare and execute statement
		   stmt = null;		// SQL statement object
		   rset = null;		// statement result set object
		   //    get resultset
		   System.out.println("query: " + query);
		   try
		   {
			  stmt = conn.createStatement();
			  System.out.println("Statement : " + stmt);
			  resultCode = stmt.executeUpdate(query);
			  System.out.println("ResultSet : " + rset);
			  
			  if (resultCode == 0) {
				   errorLogs.getErrorMsgs().add("DATABASE ERROR: Insert failed");
				   System.out.println("Insert failed");
			   }
			   else {
				   System.out.println("Insert successful");
			   }
			  
			  
		   }
		   catch (Exception e)
		   {
			   System.out.println("DATABASE ERROR: Could not execute SQL INSERT statement");
			   errorLogs.getErrorMsgs().add("DATABASE ERROR: Could not execute SQL INSERT statement");
			   System.out.println(e.getMessage());
			 //  System.exit(1);
		   }

		return resultCode;
	}
	
	public int executeQuery(String query) {
		System.out.println("Result SET QUERY");
		int id = 0;
		System.out.println("MySQL DAO executeResultSet");
		 // --- 3) prepare and execute statement
		   stmt = null;		// SQL statement object
		   rset = null;		// statement result set object
		   //    get resultset
		   System.out.println("query: " + query);
		   try
		   {
			  System.out.println("Creating a statement");
			  stmt = conn.createStatement();
			  rset = stmt.executeQuery(query);
		   }
		   catch (Exception e)
		   {
			   System.out.println("Could not execute SQL Result statement");
			   errorLogs.getErrorMsgs().add("DATABASE ERROR: Could not execute SQL RESULT statement");
			   System.out.println(e.getMessage());
			   
			   //System.exit(1);
		   }
		// use connection to execute query,
		// --- 4) process result set
		// process resultset
		   if(rset != null){
		   try {
			   while (rset.next()) {
				   id = rset.getInt(1);
			   }     // --- end - while
		   }
		   catch (SQLException sqle) {
			   System.out.println("Could not process result set");
			   errorLogs.getErrorMsgs().add("DATABASE ERROR: Could not process result set");
			   System.out.println(sqle.getMessage());
			  // System.exit(1);
		   }
		   }
		// set errorCode
		return id;
	}
	
	public int disconnect() {
		System.out.println("Disconnecting...");
		// disconnect MySQL connection, statement, resultset
		 // --- 5) clean up statement and connection
		int errorCode = 0;
		   try {
			   if(rset != null){
				   rset.close();
			   }
			   if (stmt != null){
				   stmt.close();
			   }
			   if(conn != null){
			   conn.close();
			   System.out.println("Disconnected Successfully");
			   }
		   } 
		   catch (SQLException sqle) {
			   System.out.println("Could not close statement and/or connection");
			   errorLogs.getErrorMsgs().add("DATABASE ERROR: Could not close statement and/or connection");
			   System.out.println(sqle.getMessage());
			   errorCode =1;
			 //  System.exit(1);
		   }
		   return errorCode;
	}

	@Override
	public ErrorLogs getErrorLogs() {
		// TODO Auto-generated method stub
		return errorLogs;
	}

	
}
