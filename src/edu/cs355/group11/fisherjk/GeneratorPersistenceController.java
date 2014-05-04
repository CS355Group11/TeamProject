package edu.cs355.group11.fisherjk;


public class GeneratorPersistenceController {
	// data
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	// methods
	public void persistGenerator(double minSupportLevel, double minConfidenceLevel) {
		String sqlStatement;
		dao.connect();
		sqlStatement = generateInsertStmt(minSupportLevel, minConfidenceLevel);
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
	
	// generateInsertStmt - generate an SQL insert statement for a particular Generator object
	public String generateInsertStmt(double minSupportLevel, double minConfidenceLevel) {
		System.out.println("Generating Insert Statement for vendor");
		String result = null;
		String queryID = "SELECT MAX(TransactionSet_ID) FROM TransactionSet;";
		int generator_TransactionSet_ID =  dao.executeQuery(queryID);
		String insert= "INSERT INTO Generator (Generator_min_support, Generator_min_confidence, Generator_TransactionSet_ID) VALUES("+minSupportLevel+", "+minConfidenceLevel+", " + generator_TransactionSet_ID+");";
		System.out.println("insert statment " + insert);
		result = insert;
		System.out.println("Finished generateInsertStatement for generator: ");
		return result;
	}
	public ErrorLogs getErrorLogs(){
		return dao.getErrorLogs();
	}
}
