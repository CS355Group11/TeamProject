package fisherjk;


public class TransactionPersistenceController {
	
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	//Method to connect, update, and disconnect Transaction

	
	public void persistTransaction(Transaction transaction) {
		String sqlStatement;
		dao.connect();
		
		sqlStatement = generateInsertStmt(transaction);
		if(dao.getErrorLogs().getErrorMsgs().size() == 0){
		dao.executeUpdate(sqlStatement);
		//dao.executeResultSet(sqlStatement);
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
	
	// generateInsertStmt - generate an SQL insert statement for a particular transaction object
	public String generateInsertStmt(Transaction transaction) {
		System.out.println("Generating Insert Statement for transaction");
		String result = null;
		//transaction.getTransactionDate();
		if(transaction.getTransactionDate()==null){
			transaction.setTimestamp();
			String timestamp = transaction.getTimestamp();
			transaction.setTransactionDate(timestamp);
		}
		String transactionDateTime = transaction.getTransactionDate();
		String transactionItemSet = transaction.getTransaction().getItemSet().toString();
		System.out.println("Transaction Item Set: " + transactionItemSet);
		
		
		String queryID = "SELECT MAX(TransactionSet_ID) FROM TransactionSet;";
		String queryVendor_ID = "SELECT MAX(Vendor_ID) FROM Vendor;";
		int transactionTransactionSet_ID =  dao.executeQuery(queryID);
		int transactionVendor_ID =  dao.executeQuery(queryVendor_ID);
		System.out.println("transactionVendor_ID: " + transactionVendor_ID);
		transaction.setTransactionSet_ID(transactionTransactionSet_ID);
		int ID = transaction.getTransactionSet_ID();
		String convert_date = "STR_TO_DATE(\""+transactionDateTime+"\", \"%Y-%m-%d %H:%i:%S\")";
		String insert= "INSERT INTO Transaction (Transaction_datetime, Transaction_items, Transaction_TransactionSet_ID, Transaction_Vendor_ID) VALUES("+convert_date+", \""+transactionItemSet+"\","+ID+", "+transactionVendor_ID+");";
		result = insert;
		System.out.println("Finished generateInsertStatement for transaction: " + transactionItemSet);
		return result;
	}
	
	public ErrorLogs getErrorLogs(){
		return dao.getErrorLogs();
	}
}
