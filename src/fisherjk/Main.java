package fisherjk;

import java.awt.List;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {

		
		
		System.out.println("Starting Reading File");
		TransactionSet transactionSet = new TransactionSet();
		//transactionSet = FileUtilities.readFile2("test.txt");
		//transactionSet = FileUtilities.readFile2("wagner_input.txt");
		transactionSet = FileUtilities.readFile2("multiproduct.txt");
		double transactionSetSize = transactionSet.getTransactionSet().size();
		double numTransContainsOfItemSet = 2.0;
		double minSupportLevel = ((numTransContainsOfItemSet/transactionSetSize ));
		System.out.println("minSupportLevel: " + minSupportLevel);
		double minConfidenceLevel = 0.5;
		System.out.println("Finished Reading File");
		System.out.println("Starting APriori");
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
		//FileUtilities.writeFile(ruleSets, "wagner_output.txt");
		FileUtilities.writeFile(ruleSets, "multiproduct_output.txt");
		System.out.println("Finished Writing File");
	}

}
