package fisherjk;

import java.awt.List;
import java.util.ArrayList;


public class Main {
	
	
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
	

	public static void main(String[] args) {

		
		
		System.out.println("Starting Reading File");
		TransactionSet transactionSet = new TransactionSet();
		//transactionSet = FileUtilities.readFile2("test.txt");
		transactionSet = FileUtilities.readFile2("wagner_input.txt");
		//transactionSet = FileUtilities.readFile2("multiproduct.txt");
		//transactionSet = FileUtilities.readFile2("error_text.txt");
		double transactionSetSize = transactionSet.getTransactionSet().size();
		double numTransContainsOfItemSet = 2.0;
		double minSupportLevel = numTransContainsOfItemSet/transactionSetSize;
		System.out.println("Min support level: " + minSupportLevel);
		double minConfidenceLevel = 0.5;
		System.out.println("Finished Reading File");
		System.out.println("Starting APriori");
		
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
		
		TransactionSet input = APrioriAlgorithm.DoApriori(transactionSet, minSupportLevel);
		System.out.println("Finished APriori");
		System.out.println("Starting Generating Rules");
		RuleSet ruleSets = APrioriAlgorithm.GenerateRuleSets(transactionSet, input, minConfidenceLevel);
		System.out.println("Finished Generating Rules");
		timer.stopTimer();
		System.out.println("elapsed time in msec.: "  + timer.getTotal());
		System.out.println("Starting Writing File");
		//FileUtilities.writeFile(ruleSets, "output.txt");
		FileUtilities.writeFile(ruleSets, "wagner_output.txt");
		//FileUtilities.writeFile(ruleSets, "multiproduct_output.txt");
		System.out.println("Finished Writing File");
	}

}
