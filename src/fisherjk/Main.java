package fisherjk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) {

		double minSupportLevel = 0.5;
		double minConfidenceLevel = 0.5;
		
		//runTest("test.txt", "output.txt", minSupportLevel, minConfidenceLevel);//needs minSupportLevel to be 0.22 and minConfidenceLevel to be 0.5 to mimic PPT
		//runTest("wagner_input.txt", "wagner_output.txt", minSupportLevel, minConfidenceLevel);
	    //runTest("multiproduct.txt", "multiproduct_output.txt", minSupportLevel, minConfidenceLevel);
		//runTest("error_test.txt", "error_output.txt", minSupportLevel, minConfidenceLevel);
		//runTest("transactions1.txt", "transactions1_output01.txt", 0.25, 0.5);
		//runTest("transactions1.txt", "transactions1_output02.txt", 0.5, 0.66);
		//runTest("transactions1.txt", "transactions1_output03.txt", 0.75, 0.66);
		//runTest("transactions1.txt", "transactions1_output04.txt", 0.5, 0.8);
		//runTest("transactions1.txt", "transactions1_output05.txt", 0.0, 0.0);
		//runTest("transactions1.txt", "transactions1_output06.txt", -1.0, 0.0);//one error
		//runTest("transactions1.txt", "transactions1_output07.txt", 0.0, -1.0);//one error
		//runTest("transactions1.txt", "transactions1_output08.txt", -1.0, -1.0);//two errors
		//runTest("transactions1.txt", "transactions1_output09.txt", 1.1, 0.0);//one error
		//runTest("transactions1.txt", "transactions1_output10.txt", 0.0, 1.1);//one error
		//runTest("transactions1.txt", "transactions1_output11.txt", 1.1, 1.1);//two errors
		//runTest("transactions2.txt", "transactions2_output.txt", 0.0, 0.0);//bad format
		//runTest("transactions3.txt", "transactions3_output.txt", 0.0, 0.0);//bad format
		//runTest("transactions4.txt", "transactions4_output.txt", 0.0, 0.0);//bad format
		//runTest("transactions5.txt", "transactions5_output.txt", 0.25, 0.5);
		//runTest("transactions6.txt", "transactions6_output.txt", 0.014, 0.2);//1000 transactions with 100 items
		//runTest("transactions7.txt", "transactions7_output.txt", 0.12, 0.6);//10000 transactions with 1000 items
		
		
		

		
		/*END OF DAO MAIN*/
	}
	
	/*Method to run various tests with different parameters quickly"*/
	public static void runTest(String fileInputName, String fileOutputName, double minSupportLevel, double minConfidenceLevel){
		String supMsg = checkLevels(minSupportLevel);
		String confMsg = checkLevels(minConfidenceLevel);
		//System.out.println("supMsg: " + supMsg);
		//System.out.println("confMsg: " + confMsg);
		if(supMsg.equals("") && confMsg.equals("")) {//no errors
			System.out.println("Min. support level is" + supMsg);
			System.out.println("Min. confidence level is" + confMsg);
		
		
		Timer timer = new Timer();
		timer.startTimer();
		TransactionSet transactionSet = new TransactionSet();
		System.out.println("Starting Reading File..." + fileInputName);
		transactionSet = FileUtilities.readFile(fileInputName);
		
		System.out.println("Starting APriori");
		TransactionSet input = APrioriAlgorithm.DoApriori(transactionSet, minSupportLevel);
		System.out.println("Finished APriori");
		System.out.println("Starting Generating Rules");
		RuleSet ruleSets = APrioriAlgorithm.GenerateRuleSets(transactionSet, input, minConfidenceLevel);
		System.out.println("Finished Generating Rules");
		timer.stopTimer();
		System.out.println("elapsed time in msec.: "  + timer.getTotal());
		System.out.println("Starting Writing File");
		FileUtilities.writeFile(ruleSets, fileOutputName);
		System.out.println("Finished Writing File..." + fileOutputName);
		
		}else{
			System.out.println("Error: " + supMsg);
			System.out.println("Error: " + confMsg);
			System.out.println("Errors Found No Rules Generated");
		}
	}

	/*method to determine acceptable levels minSupportLevel and minConfidenceLevel*/
	public static String checkLevels(double level){
		String message ="";
		if(level < 0.0){
			message = " below acceptable range"; 
		}	
		if(level > 1.0){
			
			message = " above acceptable range"; 
		}
		return message;
	}
	
	
	/*DAO MAIN*/
public void DAOController (TransactionSet transactionSet, RuleSet ruleSet){
	TransactionPersistenceController tpc = new TransactionPersistenceController();		// controller for delegating transaction persistence
	RulePersistenceController rpc = new RulePersistenceController();		// controller for delegating transaction persistence
	String daoString = null;
    InputStreamReader unbuffered = new InputStreamReader( System.in );
    BufferedReader keyboard = new BufferedReader( unbuffered );
	try {
		System.out.println("Use (Mock) DAO or (MySQL) DAO? Mock");
		daoString = keyboard.readLine();
	}
	catch (IOException error) {
		System.err.println("Error reading input");
	}

	tpc.setDAO(daoString);
	//iterate through tranactionset to get individual transactions
	for (Transaction transaction : transactionSet.getTransactionSet()) {
		tpc.persistTransaction(transaction);
	}
	rpc.setDAO(daoString);
	//iterate through ruleset to get individual rules
	for (Rule rule : ruleSet.getRuleSet()) {
		rpc.persistRule(rule);
	}
	
}		
}
