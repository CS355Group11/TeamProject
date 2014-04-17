package edu.uwec.cs355.group11.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.uwec.cs355.group11.algorithm.Generator;
import edu.uwec.cs355.group11.algorithm.Rule;
import edu.uwec.cs355.group11.algorithm.RuleSet;
import edu.uwec.cs355.group11.algorithm.Transaction;
import edu.uwec.cs355.group11.algorithm.TransactionSet;
import edu.uwec.cs355.group11.algorithm.Vendor;
import edu.uwec.cs355.group11.database.GeneratorPersistenceController;
import edu.uwec.cs355.group11.database.RulePersistenceController;
import edu.uwec.cs355.group11.database.RuleSetPersistenceController;
import edu.uwec.cs355.group11.database.TransactionPersistenceController;
import edu.uwec.cs355.group11.database.TransactionSetPersistenceController;
import edu.uwec.cs355.group11.database.VendorPersistenceController;
/*
 * import java.util.ArrayList;
import java.util.HashSet;
*/
public class Main {

	public static void main(String[] args) {

		double minSupportLevel = 0.2;
		double minConfidenceLevel = 0.2;
		/*
		ArrayList<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("E");
		list.add("B");
		list.add("D");
		list.add("B");
		list.add("C");
		list.add("A");
		list.add("C");
		list.add("B");
		list.add("C");
		list.add("A");
		list.add("C");
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("E");
		list.add("A");
		list.add("B");
		list.add("C");
		HashSet<String> uniqueSet = new HashSet<String>(list);
		ArrayList<String> uniqueList = new ArrayList<String>();
		System.out.println("UniqueSet  count: " + uniqueSet.size());
		for(String string : uniqueSet){
			uniqueList.add(string);
			System.out.println("Item: " + string.toString());
		}
		System.out.println("UniqueList  count: " + uniqueSet.size());
		System.out.println();
		*/
		
		//runTest("test.txt", "output.txt", 0.22, minConfidenceLevel);// needs minSupportLevel to be 0.22 and minConfidenceLevel to be 0.5 to mimic PPT
		//runTest("wagner_input.txt", "wagner_output.txt", minSupportLevel,minConfidenceLevel);
		//runTest("unique_items.txt", "unique_items_output.txt", 0.2,0.2);
		// runTest("multiproduct.txt", "multiproduct_output.txt",minSupportLevel, minConfidenceLevel);
		// runTest("error_test.txt", "error_output.txt", minSupportLevel,minConfidenceLevel);
		
		/*ALL TESTS SUCCEED*/
		 /*
		 runTest("transactions1.txt", "transactions1_output01.txt", 0.25,0.5);
		 runTest("transactions1.txt", "transactions1_output02.txt", 0.5, 0.66);
		 runTest("transactions1.txt", "transactions1_output03.txt", 0.75,0.66);
		 runTest("transactions1.txt", "transactions1_output04.txt", 0.5, 0.8);
		 runTest("transactions1.txt", "transactions1_output05.txt", 0.0, 0.0);
		 */
		/*ERROR TESTS*/
		/*ERRORS ARE SUCCESSFULLY CAUGHT*/
		/*
		 runTest("transactions1.txt", "transactions1_output06.txt", -1.0, 0.0);//one error
		 runTest("transactions1.txt", "transactions1_output07.txt", 0.0, -1.0);//one error
		 runTest("transactions1.txt", "transactions1_output08.txt", -1.0, -1.0);//two errors
		 runTest("transactions1.txt", "transactions1_output09.txt", 1.1, 0.0);//one error
		 runTest("transactions1.txt", "transactions1_output10.txt", 0.0, 1.1);//one error
		 runTest("transactions1.txt", "transactions1_output11.txt", 1.1, 1.1);//two errors
		*/
		 runTest("transactions2.txt", "transactions2_output.txt", 0.0, 0.0);//bad format
		// runTest("transactions3.txt", "transactions3_output.txt", 0.0, 0.0);//bad format
		// runTest("transactions4.txt", "transactions4_output.txt", 0.0, 0.0);//bad format
		
		
		/*LOAD TEST*/
		//runTest("transactions5.txt", "transactions5_output.txt", 0.25, 0.5);
		//runTest("smalltransactions6.txt", "transactions6_output.txt", 0.014, 0.2);//1000 transactions with 100 items (0.014)
		//runTest("transactions6.txt", "transactions6_output.txt", 0.014, 0.2);//1000 transactions with 100 items (0.014)
		//?????? runTest("transactions7.txt", "transactions7_output.txt", 0.12, 0.6);//10000 transactions with 1000 items (0.012)

		/* END OF DAO MAIN */
	}

	/* Method to run various tests with different parameters quickly" */
	public static void runTest(String fileInputName, String fileOutputName,
			double minSupportLevel, double minConfidenceLevel) {
		String supMsg = checkLevels(minSupportLevel);
		String confMsg = checkLevels(minConfidenceLevel);
		ErrorLogs errorLogs = new ErrorLogs();
		//ErrorLogs daoLogs = new ErrorLogs();
		RuleSet ruleSet = new RuleSet();
		Timer timer = new Timer();
		Timer timerDB = new Timer();
		TimerLogs tlogs = new TimerLogs();
		
		// System.out.println("supMsg: " + supMsg);
		// System.out.println("confMsg: " + confMsg);
		errorLogs = parameterLogs(supMsg, confMsg);

		if (supMsg.equals("") && confMsg.equals("")) {// no errors
			System.out.println("Min. support level is" + supMsg);
			System.out.println("Min. confidence level is" + confMsg);
			Generator generator = new Generator(minSupportLevel, minConfidenceLevel);
			//Timer timer = new Timer();
			timer.startTimer();
			TransactionSet transactionSet = new TransactionSet();
			System.out.println("Starting Reading File..." + fileInputName);
			Timer tRead = new Timer();
			tRead.startTimer();
			transactionSet = FileUtilities.readFile(fileInputName);
			tRead.stopTimer();
			String readTime = ("FileUtilties.readFile in msec. = " + tRead.getTotal());
			tlogs.getTimerLogs().add(readTime);
			if (transactionSet != null) {// while I have transactionSet

				System.out.println("Starting APriori");
				Timer tGen = new Timer();
				tGen.startTimer();
				TransactionSet input = Generator.DoApriori(
						transactionSet, minSupportLevel);
				tGen.stopTimer();
				String genTime = ("Generator.DoApriori in msec. = " + tGen.getTotal());
				tlogs.getTimerLogs().add(genTime);
				System.out.println("Finished APriori");
				System.out.println("Starting Generating Rules");
				Timer tRule = new Timer();
				tRule.startTimer();
				ruleSet = Generator.GenerateRuleSets(transactionSet,
						input, minConfidenceLevel);
				tRule.stopTimer();
				String ruleTime = ("Generator.GenerateRuleSets in msec. = " + tRule.getTotal());
				tlogs.getTimerLogs().add(ruleTime);
				System.out.println("Finished Generating Rules");
				timer.stopTimer();
				System.out
						.println("elapsed time in msec.: " + timer.getTotal());
				/* Inserting original transactionSet and generated rule set */
				//Timer timerDB = new Timer();
				//errorLogs = DAOController(generator, transactionSet, ruleSet);
				timerDB.stopTimer();
				System.out.println("DB elapsed time in msec.: " + timerDB.getTotal());
				System.out.println("Errors from DAO: " + errorLogs.getErrorCount());
			} else {
				errorLogs.getErrorMsgs().add(
						"Format Error: Transaction Set is not well-formed");
			}
		}

		int errorCount = errorLogs.getErrorCount();
		System.out.println(errorLogs.toString());
		if (errorCount != 0) {
			System.out.println(errorCount
					+ " error(s) found. No Rules are  Generated");
			errorLogs.getErrorMsgs().add(errorCount+ " error(s) found. No Rules are  Generated");
		}else{
		System.out
				.println("No error(s) found. Rules are Successfully Generated");
		errorLogs.getErrorMsgs().add("No error(s) found. Rules are Successfully Generated");
		}
		System.out.println("Total Time elapsed time in msec.: " + (timer.getTotal() + timerDB.getTotal()));
		System.out.println("Starting Writing File: " + fileOutputName);
		FileUtilities.writeFile(ruleSet, fileOutputName, errorLogs);
		System.out.println("Finished Writing File:  " + fileOutputName);
		FileUtilities.writeTimes(tlogs);
	}

	/*
	 * method to determine acceptable levels minSupportLevel and
	 * minConfidenceLevel
	 */
	public static String checkLevels(double level) {
		String message = "";
		if (level < 0.0) {
			message = " below acceptable range.";
		}
		if (level > 1.0) {

			message = " above acceptable range.";
		}

		return message;
	}
	
	public static ErrorLogs parameterLogs(String supMsg, String confMsg){
		ErrorLogs logs = new ErrorLogs();
		String parameterError = "Parameter Input Error: ";
		String parameterRange = " Must be between 0.0 and 1.0, inclusively";
		if (!supMsg.equals("")) {
			logs.getErrorMsgs().add(
					parameterError + " Min Support Level is " + supMsg
							+ parameterRange);
		}
		if (!confMsg.equals("")) {
			logs.getErrorMsgs().add(
					parameterError + " Min Confidence Level is " + confMsg
							+ parameterRange);
		}
		return logs;
		
	}
	

	/* DAO MAIN */
	public static ErrorLogs DAOController(Generator generator, TransactionSet transactionSet,RuleSet ruleSet) {
		System.out.println("Starting DAO Controller");
		ErrorLogs errorLogs = new ErrorLogs();
		VendorPersistenceController vpc = new VendorPersistenceController();
		TransactionPersistenceController tpc = new TransactionPersistenceController();
		RulePersistenceController rpc = new RulePersistenceController();
		TransactionSetPersistenceController tspc = new TransactionSetPersistenceController();
		RuleSetPersistenceController rspc = new RuleSetPersistenceController();
		GeneratorPersistenceController gpc = new GeneratorPersistenceController();
		int errorCount = 0;
		String daoString = null;
		InputStreamReader unbuffered = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(unbuffered);
		try {
			System.out.println("Use (Mock) DAO or (MySQL) DAO? Mock");
			daoString = keyboard.readLine();
		} catch (IOException error) {
			System.err.println("Error reading input");
		}
		gpc.setDAO(daoString);
		vpc.setDAO(daoString);
		tspc.setDAO(daoString);
		tpc.setDAO(daoString);
		rspc.setDAO(daoString);
		rpc.setDAO(daoString);
		
		System.out.println("Starting Persist Vendor");
		

		
		for (int i = 0; i < transactionSet.getVendorSet().size(); i++) {
			Vendor vendor = transactionSet.getVendorSet().get(i);
			vpc.persistVendor(vendor);
			System.out
					.println("vendor " + i + " is " + vendor.getVendor_name());
		}
		int vpc_errors = vpc.getErrorLogs().getErrorMsgs().size();
		System.out.println("Finished Persist Vendor");
		errorCount += vpc_errors;
		if(errorCount != 0){
			errorLogs.add("DATABASE ERROR: VENDOR TABLE");
			errorLogs.add(vpc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
		}

		// daoString = "MySQL";
		// iterate through tranactionset to get individual transactions
		int i = 0;
		
			System.out.println("Starting Persist TransactionSet");
			tspc.persistTransactionSet(transactionSet);
			int tspc_errors = tspc.getErrorLogs().getErrorMsgs().size();
			errorCount += tspc_errors;
			System.out.println("Finished Persist TransactionSet");
		if(errorCount != 0){
			errorLogs.add("DATABASE ERROR: TRANSACTIONSET TABLE");
			errorLogs.add(tspc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
		}
		System.out.println("errorCount: " + errorCount);
	
			for (Transaction transaction : transactionSet.getTransactionSet()) {
				System.out.println("Size: "
						+ transactionSet.getTransactionSet().size());
				// for(int i = 0; i < transactionSet.getTransactionSet().size();
				// i++){
				System.out.println("Starting Persist Transaction: #" + i);
				// tpc.persistTransaction(transactionSet.getTransactionSet().get(i));
				tpc.persistTransaction(transaction);
				i++;
				System.out.println("Finished Persist Transaction: #" + i);
			}
			int tpc_errors = tpc.getErrorLogs().getErrorMsgs().size();
			errorCount += tpc_errors;
			System.out.println("errorCount: " + errorCount);
			
			if(errorCount != 0){
			errorLogs.add("DATABASE ERROR: TRANSACTION TABLE");
			errorLogs.add(tpc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
			}
		
			System.out.println("Starting Persist Generator");
			gpc.persistGenerator(generator.getGenerator_minSupportLevel(), generator.getGenerator_minConfidenceLevel());
			System.out.println("Finished Persist Generator");
			int gpc_errors = gpc.getErrorLogs().getErrorMsgs().size();
			errorCount += gpc_errors;
			if(errorCount != 0){
				errorLogs.add("DATABASE ERROR: GENERATOR TABLE");
				errorLogs.add(gpc.getErrorLogs());
				return errorLogs;
			}
		
		
		// }
		int j = 0;
		
			// iterate through ruleset to get individual rules
			System.out.println("Starting Persist RuleSet");
			rspc.persistRuleSet(ruleSet);
			int rspc_errors = rspc.getErrorLogs().getErrorMsgs().size();
			errorCount += rspc_errors;
			System.out.println("errorCount: " + errorCount);
		if(errorCount != 0){
			errorLogs.add("DATABASE ERROR: RULESET TABLE");
			errorLogs.add(rspc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
			}
		
			System.out.println("Finished Persist RuleSet");
			for (Rule rule : ruleSet.getRuleSet()) {
				System.out.println("Starting Persist Rule: #" + j);
				rpc.persistRule(rule);
				System.out.println("Finished Persist Rule: #" + j);
			}
			int rpc_errors = rpc.getErrorLogs().getErrorMsgs().size();
			errorCount += rpc_errors;
			System.out.println("errorCount: " + errorCount);
			if(errorCount != 0){
			errorLogs.add("DATABASE ERROR: RULE TABLE");
			errorLogs.add(rpc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
			}
	
		System.out.println("Final errorCount: " + errorCount);
		System.out.println("Finished DAO Controller");
		System.out.println("size: " + errorLogs.getErrorMsgs().size());
		return errorLogs;
	}
	
	
	
	
}
