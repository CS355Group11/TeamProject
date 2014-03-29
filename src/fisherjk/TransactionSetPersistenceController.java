/*
 * StudentPersistenceController - controller class to persist a student
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package fisherjk;


public class TransactionSetPersistenceController {
	// data
	//private Transaction transaction;		// student being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistStudent - overall method to persist a single student object
	public void persistTransactionSet(TransactionSet transactionSet) {
		String sqlStatement;		// SQL statement to persist the student
		
		
		sqlStatement = generateInsertStmt(transactionSet);
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
	
	// generateInsertStmt - generate an SQL insert statement for a particular transaction object
	public String generateInsertStmt(TransactionSet transactionSet) {
		// TODO: code to convert transactionSet object to SQL insert statement string for that transactionSet
		String result = null;
		String transactionSetDateTime = transactionSet.getDatetime();
		String transactionSetGenerator_ID = "1";
		String insert= "INSERT INTO TransactionSet (TransactionSetDateTime, TransactionSetGenerator_ID VALUES(" + transactionSetDateTime +","+transactionSetGenerator_ID+")";
		result = insert;
		return result;
	}
}
