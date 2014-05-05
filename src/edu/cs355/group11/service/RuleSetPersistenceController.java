package edu.cs355.group11.service;

import edu.cs355.group11.common.ErrorLogs;
import edu.cs355.group11.common.RuleSet;


public class RuleSetPersistenceController {
	
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	//Method to connect, update, and disconnect RuleSet
	public void persistRuleSet(RuleSet ruleSet) {
		String sqlStatement;		
		dao.connect();
		sqlStatement = generateInsertStmt(ruleSet);
		if(dao.getErrorLogs().getErrorMsgs().size() == 0){
		dao.executeUpdate(sqlStatement);
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
	
	// generateInsertStmt - generate an SQL insert statement for a particular ruleSet object
	public String generateInsertStmt(RuleSet ruleSet) {
		String result = null;
		String queryGenID = "SELECT MAX(Generator_ID) FROM Generator;";
		int ruleSet_Generator_ID =  dao.executeQuery(queryGenID);
		String ruleSetDateTime = ruleSet.getTimestamp();
		String convert_date = "STR_TO_DATE(\""+ruleSetDateTime+"\", \"%Y-%m-%d %H:%i:%S\")";
		//System.out.println("Rule Set convert_date: " + convert_date);
		String insert = "INSERT INTO RuleSet (RuleSet_datetime, RuleSet_Generator_ID) VALUES ("+convert_date+", " + ruleSet_Generator_ID+")";
		result = insert;
		return result;
	}
	public ErrorLogs getErrorLogs(){
		return dao.getErrorLogs();
	}
}
