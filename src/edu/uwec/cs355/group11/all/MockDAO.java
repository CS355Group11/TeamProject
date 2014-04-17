/*
 * MockDAO - mock data access object class
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package edu.uwec.cs355.group11.all;




public class MockDAO implements DAOInterface {
	// methods
	public int connect() {
		// do nothing
		return 0;
	}

	public int executeUpdate(String query) {
		// return fake error code
		System.out.println("mock DAO execute");
		return 0;
	}
	
	public int executeQuery(String query) {
		// return fake error code
		System.out.println("mock DAO execute");
		return 0;
	}
	
	public int disconnect() {
		// do nothing
		return 0;
	}

	public ErrorLogs getErrorLogs() {
		// TODO Auto-generated method stub
		return null;
	}
}
