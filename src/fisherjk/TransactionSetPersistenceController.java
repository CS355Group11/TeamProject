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
		
		
		dao.connect();
		sqlStatement = generateInsertStmt(transactionSet);
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
	
	// generateInsertStmt - generate an SQL insert statement for a particular transaction object
	public String generateInsertStmt(TransactionSet transactionSet) {
		// TODO: code to convert transactionSet object to SQL insert statement string for that transactionSet
		System.out.println("Generating Insert Statement for transactionSet");
		String result = null;
		//String transactionSetDateTime = transactionSet.getDatetime();
		String start = transactionSet.getStart_date();
		String end = transactionSet.getEnd_date();
		if(start == null || end == null){
			start = "2014-04-01";
			end = "2014-04-01";
		}
		String startDateTime = start +" 12:00:00";//think about how to get default time here and fileUtitities
		String endDateTime = end +" 12:00:00";
		String convert_start_date = "STR_TO_DATE(\""+startDateTime+"\", \"%Y-%m-%d %H:%i:%S\")";
		String convert_end_date = "STR_TO_DATE(\""+endDateTime+"\", \"%Y-%m-%d %H:%i:%S\")";
		String insert= "INSERT INTO TransactionSet (TransactionSet_start_datetime, TransactionSet_end_datetime) VALUES(" + convert_start_date +", " +convert_end_date+")";
		result = insert;
		System.out.println("result: " + result);
		System.out.println("Finished Insert Statement for transactionSet");
		return result;
	}
	
	public ErrorLogs getErrorLogs(){
		return dao.getErrorLogs();
	}
	
}
