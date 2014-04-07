package fisherjk;


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
		String queryID = "SELECT MAX(TransactionSet_ID) FROM TransactionSet;";
		String queryGenID = "SELECT MAX(Generator_ID) FROM Generator;";
		//dao.connect();
		int ruleSet_TransactionSet_ID =  dao.executeQuery(queryID);
		int ruleSet_Generator_ID =  dao.executeQuery(queryGenID);
		//dao.disconnect();
		String ruleSetDateTime = ruleSet.getTimestamp();
		String convert_date = "STR_TO_DATE(\""+ruleSetDateTime+"\", \"%Y-%m-%d %H:%i:%S\")";
		System.out.println("Rule Set convert_date: " + convert_date);
		String insert = "INSERT INTO RuleSet (RuleSet_datetime, RuleSet_TransactionSet_ID, RuleSet_Generator_ID) VALUES ("+convert_date+", "+ruleSet_TransactionSet_ID+", " + ruleSet_Generator_ID+")";
		result = insert;
		result = insert;
		return result;
	}
	public ErrorLogs getErrorLogs(){
		return dao.getErrorLogs();
	}
}
