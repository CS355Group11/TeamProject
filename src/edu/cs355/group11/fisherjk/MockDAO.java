/*
 * MockDAO - mock data access object class
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package edu.cs355.group11.fisherjk;




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
		//do nothing
		return null;
	}
}
