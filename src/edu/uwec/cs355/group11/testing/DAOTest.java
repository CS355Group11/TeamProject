package edu.uwec.cs355.group11.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fisherjk.MySQLDAO;

public class DAOTest {
	
	@Test
	public void DAOTest() {
		MySQLDAO dao = new MySQLDAO();
		assertNotNull(dao);
		
	}
	
	@Test
	public void connectTest() {
		MySQLDAO dao = new MySQLDAO();
		int test = dao.connect();
		assertEquals(0, test);
	}
	
	@Test
	public void executeUpdateTest() {
		MySQLDAO dao = new MySQLDAO();
		int test = dao.executeUpdate("test");
		assertEquals(1, test);
	}
	
	@Test
	public void executeQueryTest() {
		MySQLDAO dao = new MySQLDAO();
		int test = dao.executeQuery("Test");
		assertEquals(1, test);
	}
	
	@Test
	public void disconnectTest() {
		MySQLDAO dao = new MySQLDAO();
		int test = dao.disconnect();
		assertEquals(1, test);
		dao.connect();
		test = dao.disconnect();
		assertEquals(0, test);
	}
	
	@Test
	public void getErrorLogsTest() {
		MySQLDAO dao = new MySQLDAO();
		dao.executeQuery("Test");
		assertNotNull(dao.getErrorLogs());
	}
}
