/*
 * StudentPersistenceController - controller class to persist a student
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package fisherjk;


public class RuleSetPersistenceController {
	// data
	//private Rule rule;		// student being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistStudent - overall method to persist a single student object
	public void persistRuleSet(RuleSet ruleSet) {
		String sqlStatement;		// SQL statement to persist the student
		
			// could pass a student object in as parameter to this method
		sqlStatement = generateInsertStmt(ruleSet);
		dao.connect();
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
	public String generateInsertStmt(RuleSet ruleSet) {
		String result = null;
		//String ruleSetDateTime = ruleSet.getDatetime();
		String queryID = "SELECT MAX(TransactionSet_ID) FROM TransactionSet;";
		dao.connect();
		int ruleSet_TransactionSet_ID =  dao.executeQuery(queryID);
		dao.disconnect();
		String ruleSetDateTime = "2014-04-01 12:00:00";
		String convert_date = "STR_TO_DATE(\""+ruleSetDateTime+"\", \"%Y-%m-%d %H:%i:%S\")";
		String insert = "INSERT INTO RuleSet (RuleSet_datetime, RuleSet_TransactionSet_ID) VALUES ("+convert_date+", "+ruleSet_TransactionSet_ID+")";
		result = insert;
		// TODO: code to convert rule object to SQL insert statement string for that rule
		result = insert;
		return result;
	}
	public ErrorLogs getErrorLogs(){
		return dao.getErrorLogs();
	}
}
