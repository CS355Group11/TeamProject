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
		dao.execute(sqlStatement);
		dao.disconnect();
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
		String ruleSetDateTime = ruleSet.getDatetime();
		String ruleSetTransactionSet_ID = "";
		// TODO: code to convert rule object to SQL insert statement string for that rule
		String insert = "INSERT INTO RULE (RuleActualDateTime, RuleSetTransactionSet_ID VALUES ("+ruleSetDateTime+","+ruleSetTransactionSet_ID+")";
		result = insert;
		return result;
	}
}
