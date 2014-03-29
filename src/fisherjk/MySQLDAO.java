/*
 * MySQLDAO - DAO class that persists to a MySQL database
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package fisherjk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDAO implements DAOInterface {
	// methods
	   // --- set the username and password
	   String user = "CS355GROUP11";		// bypass call to readEntry	
	   String pass = "B334876$";
	   Connection conn = null;
	   Statement stmt = null;
	   ResultSet rset = null;
	
	public void connect() {
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
			  conn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/student",user,pass);
		   }
		   catch (SQLException sqle)
		   {
			  System.out.println ("Could not make connection to database");
			  System.out.println(sqle.getMessage());
			  System.exit(1);
		   }
	}

	public int execute(String query) {
		int errorCode = 0;
		int resultCode = 0;			// result code from SQL update
		System.out.println("MySQL DAO execute");
		 // --- 3) prepare and execute statement
		   stmt = null;		// SQL statement object
		   rset = null;		// statement result set object
		   //    get resultset
		   try
		   {
			  stmt = conn.createStatement();
			  rset = stmt.executeQuery(query);
		   }
		   catch (Exception e)
		   {
			   System.out.println("Could not execute SQL statement");
			   System.out.println(e.getMessage());
			   errorCode = 1;
			   System.exit(1);
		   }
		// use connection to execute query,
		// --- 4) process result set
		// process resultset
		   try {
			   while (rset.next()) {
				   System.out.println(rset.getString(1) + "  " +	// TODO: change to use metadata
								 	  rset.getString(2));
			   }     // --- end - while
		   }
		   catch (SQLException sqle) {
			   System.out.println("Could not process result set");
			   System.out.println(sqle.getMessage());
			   errorCode = 2;
			   System.exit(1);
		   }
		// set errorCode
		return errorCode;
	}
	
	public int executeResultSet(String query) {
		int id = 0;
		System.out.println("MySQL DAO execute");
		 // --- 3) prepare and execute statement
		   stmt = null;		// SQL statement object
		   rset = null;		// statement result set object
		   //    get resultset
		   try
		   {
			  stmt = conn.createStatement();
			  rset = stmt.executeQuery(query);
		   }
		   catch (Exception e)
		   {
			   System.out.println("Could not execute SQL statement");
			   System.out.println(e.getMessage());
			   
			   System.exit(1);
		   }
		// use connection to execute query,
		// --- 4) process result set
		// process resultset
		   try {
			   while (rset.next()) {
				   id = rset.getInt(0);
			   }     // --- end - while
		   }
		   catch (SQLException sqle) {
			   System.out.println("Could not process result set");
			   System.out.println(sqle.getMessage());
			   System.exit(1);
		   }
		// set errorCode
		return id;
	}
	
	public void disconnect() {
		// disconnect MySQL connection, statement, resultset
		 // --- 5) clean up statement and connection
		   try {
			   rset.close();
			   stmt.close();
			   conn.close();
		   } 
		   catch (SQLException sqle) {
			   System.out.println("Could not close statement and connection");
			   System.out.println(sqle.getMessage());
			   System.exit(1);
		   }
		   
		   System.exit(0);
	}

	
}
