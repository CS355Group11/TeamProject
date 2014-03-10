package fisherjk;


public class Main {

	public static void main(String[] args) {

		double minSupportLevel = 0.5;
		double minConfidenceLevel = 0.5;
		
		//runTest("test.txt", "output.txt", minSupportLevel, minConfidenceLevel);//needs minSupportLevel to be 0.22 and minConfidenceLevel to be 0.5 to mimic PPT
		runTest("wagner_input.txt", "wagner_output.txt", minSupportLevel, minConfidenceLevel);
	    //runTest("multiproduct.txt", "multiproduct_output.txt", minSupportLevel, minConfidenceLevel);
		//runTest("error_test.txt", "error_output.txt", minSupportLevel, minConfidenceLevel);
	}
	
	/*Method to run various tests with different parameters quickly"*/
	public static void runTest(String fileInputName, String fileOutputName, double minSupportLevel, double minConfidenceLevel){
		String supMsg = checkLevels(minSupportLevel);
		String confMsg = checkLevels(minConfidenceLevel);
		
		if(!supMsg.equals("")) {
			System.out.println("Min. support level is" + supMsg);
		}
		
		if(!confMsg.equals("")) {
			System.out.println("Min. confidence level is" + confMsg);
		}
		
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
}
