package fisherjk;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ServerResource;

public class GeneratorServerResource extends ServerResource implements
		GeneratorResource {
	// data
	private static Generator generator = new Generator();
	private static RuleSet ruleSet = new RuleSet();
	private static ErrorLogs errorLogs = new ErrorLogs();

	// methods
	public GeneratorServerResource() {
		System.out.println("Generator constructor");
	}

	public RuleSet retrieve() {
		/*
		 * GeneratorServerResource.generator.setGenerator_minSupportLevel(0.5);
		 * GeneratorServerResource
		 * .generator.setGenerator_minConfidenceLevel(0.5);
		 * GeneratorServerResource
		 * .generator.setGenerator_filePath("transactions1.txt");
		 */
		System.out.println("Set Generator Min Support Level to   : "
				+ GeneratorServerResource.generator
						.getGenerator_minSupportLevel());
		System.out.println("Set Generator Min Confidence Level to: "
				+ GeneratorServerResource.generator
						.getGenerator_minConfidenceLevel());
		System.out.println("Set Generator File Path to           : "
				+ GeneratorServerResource.generator.getGenerator_filePath());
		/*
		 * String outputRuleFilePath =
		 * "rules_of_"+GeneratorServerResource.generator
		 * .getGenerator_filePath(); String outputErrorFilePath =
		 * "rules_of_"+GeneratorServerResource
		 * .generator.getGenerator_filePath();
		 * System.out.println("Starting Writing File(s): " + outputRuleFilePath
		 * + " and " + outputErrorFilePath); FileUtilities.writeFile(ruleSet,
		 * outputRuleFilePath, errorLogs, outputErrorFilePath);
		 * System.out.println("Finished Writing File(s):  " + outputRuleFilePath
		 * +" and " + outputErrorFilePath);
		 */

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
		System.out.println("Starting Reading File..." + inputFilePath);
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
		System.out.println("Total Time elapsed time in msec.: "+ (timer.getTotal()));
		
		this.errorLogs = errorLogs;
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

	public static ErrorLogs parameterLogs(String supMsg, String confMsg) {
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
		/*
		 * InputStreamReader unbuffered = new InputStreamReader(System.in);
		 * BufferedReader keyboard = new BufferedReader(unbuffered); try {
		 * System.out.println("Use (Mock) DAO or (MySQL) DAO? Mock"); daoString
		 * = keyboard.readLine(); } catch (IOException error) {
		 * System.err.println("Error reading input"); }
		 */
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
		if (errorCount != 0) {
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
		if (errorCount != 0) {
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

		if (errorCount != 0) {
			errorLogs.add("DATABASE ERROR: TRANSACTION TABLE");
			errorLogs.add(tpc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
		}

		System.out.println("Starting Persist Generator");
		gpc.persistGenerator(generator.getGenerator_minSupportLevel(),
				generator.getGenerator_minConfidenceLevel());
		System.out.println("Finished Persist Generator");
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
