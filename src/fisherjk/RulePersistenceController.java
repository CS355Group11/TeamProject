/*
 * StudentPersistenceController - controller class to persist a student
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package fisherjk;


public class RulePersistenceController {
	// data
	private Rule rule;		// student being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistStudent - overall method to persist a single student object
	public void persistRule(Rule rule) {
		String sqlStatement;		// SQL statement to persist the student
		
			// could pass a student object in as parameter to this method
		sqlStatement = generateInsertStmt(rule);
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
	public String generateInsertStmt(Rule rule) {
		String result = null;
		double ruleActualConfidence = rule.getActualConfidenceLevel();
		String ruleAntecedent = rule.getX().toString();
		String ruleConsequent = rule.getY().toString();
	    int ruleGenerator = 1;
	    int ruleRuleSet = 1;
		// TODO: code to convert rule object to SQL insert statement string for that rule
		String insert = "INSERT INTO RULE (RuleActualConfidence, RuleAntecedent_ID, RuleConsequent_ID, RuleGenerator_ID, RuleRuleSet_ID VALUES ("+ruleActualConfidence+","+ruleAntecedent+","+ruleConsequent +","+ruleGenerator +","+ruleRuleSet+")";
		result = insert;
		return result;
	}
}
