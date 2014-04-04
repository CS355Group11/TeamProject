/*
 * DAOInterface - interface for various DAO implementations
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package fisherjk;

import java.sql.ResultSet;

public interface DAOInterface {
	public void connect();				// connect to data store
	public int executeUpdate(String query);	// execute a query, return error code
	public int executeQuery(String query);
	public void disconnect();			// disconnect from data store
	public ErrorLogs getErrorLogs();
}
