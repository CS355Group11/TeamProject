/*
 * MockDAO - mock data access object class
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package fisherjk;




public class MockDAO implements DAOInterface {
	// methods
	public void connect() {
		// do nothing
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
	
	public void disconnect() {
		// do nothing
	}

	public ErrorLogs getErrorLogs() {
		// TODO Auto-generated method stub
		return null;
	}
}
