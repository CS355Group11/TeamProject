/*
 * StudentPersistenceController - controller class to persist a student
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package edu.cs355.group11.service;

import edu.cs355.group11.common.ErrorLogs;


public class VendorPersistenceController {
	// data
	private DAOInterface dao;	
	public void persistVendor(Vendor vendor) {
		String sqlStatement;		// SQL statement to persist the student
		
			// could pass a student object in as parameter to this method
		dao.connect();
		sqlStatement = generateInsertStmt(vendor);
		if(dao.getErrorLogs().getErrorMsgs().size() == 0){
		dao.executeUpdate(sqlStatement);
		//dao.executeResultSet(sqlStatement);
		}
		if(dao.getErrorLogs().getErrorMsgs().size() == 0){
		dao.disconnect();
		}
		
	}

	// setDAO - set the controller DAO to a given DAO
	public void setDAO(String daoLine) {
		if (daoLine.equals("Mock")) {
			dao = new MockDAO();
		}
		else if (daoLine.equals("MySQL")) {
			dao = new MySQLDAO();
		}
	}
	
	// generateInsertStmt - generate an SQL insert statement for a particular vendor object
	public String generateInsertStmt(Vendor vendor) {
		//System.out.println("Generating Insert Statement for vendor");
		String result = null;
		String vendor_name = vendor.getVendor_name();
		if(vendor_name == ""){
			vendor_name = "Not Provided";
		}
		String insert= "INSERT INTO Vendor (Vendor_name) VALUES(\""+vendor_name+"\");";
		//System.out.println("insert statment " + insert);
		result = insert;
		//System.out.println("Finished generateInsertStatement for vendor: " + vendor_name);
		return result;
	}
	public ErrorLogs getErrorLogs(){
		return dao.getErrorLogs();
	}
}
