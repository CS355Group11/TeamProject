/*
 * StudentPersistenceController - controller class to persist a student
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package fisherjk;


public class RulePersistenceController {
	// data
	//private Rule rule;		// student being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistStudent - overall method to persist a single student object
	public void persistRule(Rule rule) {
		String sqlStatement;		// SQL statement to persist the student
		
			// could pass a student object in as parameter to this method
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
	    int ruleGenerator = 1;
		//String query = "SELECT RuleSet_ID FROM RuleSet WHERE RuleSet_ID =" +rule.getRuleSet_ID();
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
