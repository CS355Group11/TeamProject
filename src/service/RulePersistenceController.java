package service;

import common.ErrorLogs;


public class RulePersistenceController {
	
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	//Method to connect, update, and disconnect Rule
	public void persistRule(Rule rule) {
		String sqlStatement;
		dao.connect();
		sqlStatement = generateInsertStmt(rule);
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
	
	// generateInsertStmt - generate an SQL insert statement for a particular rule object
	public String generateInsertStmt(Rule rule) {
		String result = null;
		double ruleActualConfidence = rule.getActualConfidenceLevel();
		String ruleAntecedent = rule.getX().getItemSet().toString();
		String ruleConsequent = rule.getY().getItemSet().toString();
		String queryID = "SELECT MAX(RuleSet_ID) FROM RuleSet;";
		//dao.connect();
		int ruleRuleSet_ID =  dao.executeQuery(queryID);
		//dao.disconnect();
		String insert = "INSERT INTO RULE (Rule_antecedent, Rule_consequent, Rule_actual_confidence, Rule_RuleSet_ID) VALUES (\""+ruleAntecedent+"\",\""+ruleConsequent +"\","+ruleActualConfidence+","+ruleRuleSet_ID+")";
		result = insert;
		return result;
	}
	public ErrorLogs getErrorLogs(){
		return dao.getErrorLogs();
	}
}
