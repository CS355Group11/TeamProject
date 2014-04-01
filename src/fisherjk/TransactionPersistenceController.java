/*
 * StudentPersistenceController - controller class to persist a student
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package fisherjk;


public class TransactionPersistenceController {
	// data
	//private Transaction transaction;		// student being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistStudent - overall method to persist a single student object
	public void persistTransaction(Transaction transaction) {
		String sqlStatement;		// SQL statement to persist the student
		
			// could pass a student object in as parameter to this method
		sqlStatement = generateInsertStmt(transaction);
		dao.connect();
		dao.execute(sqlStatement);
		//dao.executeResultSet(sqlStatement);
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
	public String generateInsertStmt(Transaction transaction) {
		System.out.println("Generating Insert Statement for transaction");
		// TODO: code to convert transaction object to SQL insert statement string for that transaction
		String result = null;
		String transactionDateTime = "2014-04-01 12:00:00";
		String transactionItemSet = transaction.getTransaction().getItemSet().toString();
		System.out.println("Transaction Item Set: " + transactionItemSet);
		
		//String query = "SELECT TransactionSet_ID FROM TransactionSet WHERE TranscationSet_ID =" + transaction.getTransactionSet_ID();
		String queryID = "SELECT MAX(TransactionSet_ID) FROM TransactionSet_new;";
		dao.connect();
		int transactionTransactionSet_ID =  dao.executeResultSet(queryID);
		dao.disconnect();
		//int transactionTransactionSet_ID = 2;
		//transactionTransactionSet_ID+=1;
		String convert_date = "STR_TO_DATE(\""+transactionDateTime+"\", \"%Y-%m-%d %H:%i:%S\")";
		//String insert= "INSERT INTO Transaction (TransactionDateTime, TransactionItemSet_ID, TransactionTransactionSet_ID, TransactionVendor_ID) VALUES(" + transactionDateTime +","+transactionItemSet+","+transactionTransactionSet_ID+","+transactionVendor_ID+")";
		String insert= "INSERT INTO Transaction_new (Transaction_datetime, Transaction_items, Transaction_TransactionSet_ID) VALUES("+convert_date+",\""+transactionItemSet+"\" ,"+transactionTransactionSet_ID+" );";
		result = insert;
		System.out.println("Finished generateInsertStatement for transaction: " + transactionItemSet);
		return result;
	}
}
