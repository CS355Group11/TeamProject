package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.annotation.Annotation;

import junit.framework.TestCase;

import org.junit.Test;

import service.MySQLDAO;


public class TestMySQLDAO extends TestCase{
	
	
	@Test
	public void testConnect() {
		MySQLDAO dao = new MySQLDAO();
		int test = dao.connect();
		assertEquals(0, test);//0 means a successful connect
	}
	
	@Test
	public void testExecuteUpdate() {
		MySQLDAO dao = new MySQLDAO();
		String insert= "INSERT INTO Vendor (Vendor_name) VALUES(JUnit);";
		int test = dao.executeUpdate(insert);
		assertEquals(0, test);//0 means a successful executeQueryUpdate
	}
	
	@Test
	public void testExecuteQueryTest() {
		MySQLDAO dao = new MySQLDAO();
		String query = "SELECT * FROM Vendor";
		int test = dao.executeQuery(query);
		assertEquals(0, test);//0 means a successful executeQueryTest
	}
	
	@Test
	public void testDisconnect() {
		MySQLDAO dao = new MySQLDAO();
		dao.connect();
		int test = dao.disconnect();
		assertEquals(0, test);//0 means successful disconnect
	}
	
	@Test
	public void testGetErrorLogsTest() {
		MySQLDAO dao = new MySQLDAO();
		dao.getErrorLogs();
		assertNotNull(dao.getErrorLogs());
	}
	
	/* 1. MySQL
	 * 2. Generator
	 * 3. ItemSet
	 * 4. Rule
	 * 5. Transaction
	 */


}
