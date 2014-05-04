package edu.cs355.group11.fisherjk;

//import org.restlet.resource.ClientResource;
import org.restlet.resource.ServerResource;

public class GeneratorServerResource extends ServerResource implements
		GeneratorResource {
	// data
	private static Generator generator = new Generator();
	private static RuleSet ruleSet = new RuleSet();
	//private static ErrorLogs errorLogs = new ErrorLogs();

	// methods
	public GeneratorServerResource() {
		System.out.println("Generator constructor");
	}

	public RuleSet retrieve() {
		
		System.out.println("Set Generator Min Support Level to   : "
				+ GeneratorServerResource.generator
						.getGenerator_minSupportLevel());
		System.out.println("Set Generator Min Confidence Level to: "
				+ GeneratorServerResource.generator
						.getGenerator_minConfidenceLevel());
		System.out.println("Set Generator File Path to           : "
				+ GeneratorServerResource.generator.getGenerator_filePath());

		System.out.println("Server RuleSet SIZE: "
				+ ruleSet.getRuleSet().size());
		return ruleSet;
	}

	public void store(Generator generator) {
		GeneratorServerResource.generator = new Generator(generator);
		System.out.println("Min Support Level    : "
				+ GeneratorServerResource.generator
						.getGenerator_minSupportLevel());
		System.out.println("Min Confidence Level : "
				+ GeneratorServerResource.generator
						.getGenerator_minConfidenceLevel());
		System.out.println("File Path            : "
				+ GeneratorServerResource.generator.getGenerator_filePath());
		String fileOutputName = "rules_of_"
				+ GeneratorServerResource.generator.getGenerator_filePath();
		System.out.println("Starting to Run Test");
		runTest(GeneratorServerResource.generator.getGenerator_filePath(),
				fileOutputName,
				GeneratorServerResource.generator
						.getGenerator_minSupportLevel(),
				GeneratorServerResource.generator
						.getGenerator_minConfidenceLevel());
		System.out.println("Finished Run Test");
	}

	/* Method to run various tests with different parameters quickly" */
	public void runTest(String inputFilePath, String outputRuleFilePath,double minSupportLevel, double minConfidenceLevel) {
		/*Initialize Objects*/
		ErrorLogs errorLogs = new ErrorLogs();
		Timer timer = new Timer();
		Timer tGen = new Timer();
		Timer timerDB = new Timer();
		TimerLogs tlogs = new TimerLogs();
		
		/*Start Master Timer*/
		timer.startTimer();
		
		/*Re-read Transaction Set to create the TransactionSet needed for A Priori*/
		TransactionSet transactionSet = new TransactionSet();
		//System.out.println("Starting Reading File..." + inputFilePath);
		transactionSet = FileUtilities.readFile(inputFilePath);
		
		/*Start Running the A Priori Algorithm*/
		System.out.println("Starting APriori");
		tGen.startTimer();
		TransactionSet input = Generator.DoApriori(transactionSet,minSupportLevel);
		tGen.stopTimer();
		String genTime = ("Generator.DoApriori in msec. = " + tGen.getTotal());
		tlogs.getTimerLogs().add(genTime);
		System.out.println("Finished APriori");
		
		/*Start Generating Rule Sets*/
		System.out.println("Starting Generating Rules");
		Timer tRule = new Timer();
		tRule.startTimer();
		ruleSet = Generator.GenerateRuleSets(transactionSet, input,minConfidenceLevel);
		tRule.stopTimer();
		String ruleTime = ("Generator.GenerateRuleSets in msec. = " + tRule.getTotal());
		tlogs.getTimerLogs().add(ruleTime);
		System.out.println("Finished Generating Rules");

		/* Inserting original transactionSet and generated rule set */
		timerDB.startTimer();
		
		/*Trigger Database Calls*/
		errorLogs = DAOController(generator, transactionSet, ruleSet);
		
		timerDB.stopTimer();
		System.out.println("DB elapsed time in msec.: " + timerDB.getTotal());
		System.out.println("Errors from DAO: " + errorLogs.getErrorCount());

		int errorCount = errorLogs.getErrorCount();
		System.out.println(errorLogs.toString());
		if (errorCount != 0) {
			System.out.println(errorCount+ " error(s) found. No Rules are  Generated");
			errorLogs.getErrorMsgs().add(errorCount + " error(s) found. No Rules are  Generated");
		}
		timer.stopTimer();
		System.out.println("TGEN Total Time elapsed time in msec.: "+ (tGen.getTotal()));
		System.out.println("TRULE Total Time elapsed time in msec.: "+ (tRule.getTotal()));
		System.out.println("TDB Total Time elapsed time in msec.: "+ (timerDB.getTotal()));
		System.out.println("Timer Total Time elapsed time in msec.: "+ (timer.getTotal()));
		//this.errorLogs = errorLogs;
	}


	/* DAO MAIN */
	public static ErrorLogs DAOController(Generator generator,
			TransactionSet transactionSet, RuleSet ruleSet) {
		System.out.println("Starting DAO Controller");
		ErrorLogs errorLogs = new ErrorLogs();
		VendorPersistenceController vpc = new VendorPersistenceController();
		TransactionPersistenceController tpc = new TransactionPersistenceController();
		RulePersistenceController rpc = new RulePersistenceController();
		TransactionSetPersistenceController tspc = new TransactionSetPersistenceController();
		RuleSetPersistenceController rspc = new RuleSetPersistenceController();
		GeneratorPersistenceController gpc = new GeneratorPersistenceController();
		int errorCount = 0;
		String daoString = "MySQL";
		gpc.setDAO(daoString);
		vpc.setDAO(daoString);
		tspc.setDAO(daoString);
		tpc.setDAO(daoString);
		rspc.setDAO(daoString);
		rpc.setDAO(daoString);

		//System.out.println("Starting Persist Vendor");

		for (int i = 0; i < transactionSet.getVendorSet().size(); i++) {
			Vendor vendor = transactionSet.getVendorSet().get(i);
			vpc.persistVendor(vendor);
			System.out
					.println("vendor " + i + " is " + vendor.getVendor_name());
		}
		int vpc_errors = vpc.getErrorLogs().getErrorMsgs().size();
		//System.out.println("Finished Persist Vendor");
		errorCount += vpc_errors;
		if (errorCount != 0) {
			errorLogs.add("DATABASE ERROR: VENDOR TABLE");
			errorLogs.add(vpc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
		}

		
		// iterate through tranactionset to get individual transactions
		//int i = 0;

		//System.out.println("Starting Persist TransactionSet");
		tspc.persistTransactionSet(transactionSet);
		int tspc_errors = tspc.getErrorLogs().getErrorMsgs().size();
		errorCount += tspc_errors;
		//System.out.println("Finished Persist TransactionSet");
		if (errorCount != 0) {
			errorLogs.add("DATABASE ERROR: TRANSACTIONSET TABLE");
			errorLogs.add(tspc.getErrorLogs());
			//System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
		}
		//System.out.println("errorCount: " + errorCount);
		//tpc.connect();
		int i =0;
		int size = transactionSet.getTransactionSet().size();
		for (Transaction transaction : transactionSet.getTransactionSet()) {
			System.out.println("Size: "
					+ transactionSet.getTransactionSet().size());
			// for(int i = 0; i < transactionSet.getTransactionSet().size();
			// i++){
			//System.out.println("Starting Persist Transaction: #" + i);
			// tpc.persistTransaction(transactionSet.getTransactionSet().get(i));
			tpc.persistTransaction(transaction, i, size);
			i++;
			//System.out.println("Finished Persist Transaction: #" + i);
		}
		System.out.println("Finished Persisting Transactions");
		int tpc_errors = tpc.getErrorLogs().getErrorMsgs().size();
		errorCount += tpc_errors;
		//System.out.println("errorCount: " + errorCount);
		
		if (errorCount != 0) {
			//errorLogs.add("DATABASE ERROR: TRANSACTION TABLE");
			errorLogs.add(tpc.getErrorLogs());
			//System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
		}

		//tpc.disconnect();
		System.out.println("Starting Persist Generator");
		gpc.persistGenerator(generator.getGenerator_minSupportLevel(),
				generator.getGenerator_minConfidenceLevel());
		//System.out.println("Finished Persist Generator");
		int gpc_errors = gpc.getErrorLogs().getErrorMsgs().size();
		errorCount += gpc_errors;
		if (errorCount != 0) {
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
		if (errorCount != 0) {
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
		if (errorCount != 0) {
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
