package edu.uwec.cs355.group11.testing;

import java.io.Serializable;
import java.util.ArrayList;

import edu.uwec.cs355.group11.client.Timer;
import edu.uwec.cs355.group11.common.RuleSet;
import edu.uwec.cs355.group11.common.TransactionSet;
import edu.uwec.cs355.group11.service.Item;
import edu.uwec.cs355.group11.service.ItemSet;
import edu.uwec.cs355.group11.service.Rule;
import edu.uwec.cs355.group11.service.Transaction;


/*Class to Run the Part I of the A Priori Algorithm */



public class Generator implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double generator_minSupportLevel;
	private double generator_minConfidenceLevel;
	private String generator_filePath;
	
	public Generator(){
		
	}


	public Generator(double generator_minSupportLevel,double generator_minConfidenceLevel, String generator_filePath) {
		this.generator_minSupportLevel = generator_minSupportLevel;
		this.generator_minConfidenceLevel = generator_minConfidenceLevel;
		this.generator_filePath = generator_filePath;
	}



	public Generator(Generator generator) {
		setGenerator_minSupportLevel(generator.getGenerator_minSupportLevel());
		setGenerator_minConfidenceLevel(generator.getGenerator_minConfidenceLevel());
		setGenerator_filePath(generator.getGenerator_filePath());
	}


	public double getGenerator_minSupportLevel() {
		return generator_minSupportLevel;
	}

	public void setGenerator_minSupportLevel(double generator_minSupportLevel) {
		this.generator_minSupportLevel = generator_minSupportLevel;
	}

	public double getGenerator_minConfidenceLevel() {
		return generator_minConfidenceLevel;
	}

	public void setGenerator_minConfidenceLevel(double generator_minConfidenceLevel) {
		this.generator_minConfidenceLevel = generator_minConfidenceLevel;
	}
	

	public String getGenerator_filePath() {
		return generator_filePath;
	}


	public void setGenerator_filePath(String generator_filePath) {
		this.generator_filePath = generator_filePath;
	}


	public static TransactionSet DoApriori(TransactionSet transSet,double minSupportLevel) {
		Timer timer = new Timer();
		timer.startTimer();
		//for (int i = 0; i < transSet.getTransactionSet().size(); i++) {
		//	for (int j = 0; j < transSet.getTransactionSet().size(); j++) {
		//	(new TransactionSet(j)).start();
		//	}
		//}
		//timer.stopTimer();
		System.out.println("Threaded Generator.GetUniqueItems: Time in Milliseconds" + timer.getTotal());
		ItemSet initialItemSet = transSet.GetUniqueItems();//get all singular unique items and put them into a ItemSet object
		TransactionSet finalLargeItemSet = new TransactionSet(); // resultant large itemsets
		TransactionSet LargeItemSet = new TransactionSet(); // large itemset in each iteration
		TransactionSet CandidateItemSet = new TransactionSet(); // candidate itemset in each iteration


		// first iteration (1-item itemsets)
		for (int i = 0; i < initialItemSet.getItemSet().size(); i++) {
			Item candidateItem = initialItemSet.getItemSet().get(i);
			ItemSet candidateItemSet = new ItemSet();
			candidateItemSet.getItemSet().add(candidateItem);
			Transaction candidateTrans = new Transaction(candidateItemSet);
			//candidateTrans.toString();

			CandidateItemSet.getTransactionSet().add(candidateTrans);
		}

		// next iterations
		int k = 2;
		while (CandidateItemSet.getTransactionSet().size() != 0) {
			//set LargeItemSetIteration from CandidateItemSet (pruning)
			LargeItemSet.getTransactionSet().clear();
			System.out.println("START");
			for (Transaction transaction : CandidateItemSet.getTransactionSet()) {//loop through each transaction in each TransactionSet
				double findSupport = transSet.findSupport(transaction.getTransaction());//calculate and find each successive support level for an transaction (remember it is an itemSet)
				transaction.getTransaction().setItemSetSupport(findSupport/(transSet.getTransactionSet().size()));//Set each successive support level for its respective transaction (remember it is an itemSet)

				
				if (transaction.getTransaction().getItemSetSupport() >= minSupportLevel) {//Determine if the itemSet's support level meets or exceeds the supportThreshold to filter out
					
					System.out.println(transaction.toString() + "-" + findSupport);//debugging info
					
					LargeItemSet.getTransactionSet().add(transaction);
					if (transaction.getTransaction().getItemSet().size() > 1) {// this might not work in the long run
						
					
					finalLargeItemSet.getTransactionSet().add(transaction);//add the resultingLargeItemSet of final 
					}
				}

			}
			CandidateItemSet.getTransactionSet().clear();//clear up the candidates to prep for finding all subsets
			System.out.println("making candidates");
			CandidateItemSet = (CandidateItemSet.findKItemSubsets(LargeItemSet.GetUniqueItems(), k));//Add all the combinations of subsets for a given set of unique items/ItemSets
			System.out.println("done making candidates");
			k += 1;
			System.out.println("END");
		}//end of while loop brace
		System.out.println("Final Set: \n" + finalLargeItemSet.toString());
		timer.stopTimer();
		//System.out.println("Generator.DoAPriori: Time in Milliseconds" + timer.getTotal());
		return finalLargeItemSet;//final returned value


	}
	
	/*Method to generate individual ruleSets give an initial transactionSet, a transactionSet from running the APriori Algorithm, and a specified minimumConfidenceLevel*/
	public static RuleSet GenerateRuleSets(TransactionSet transSet, TransactionSet finalLargeItemSet,double minConfidenceLevel) {
		Timer timer = new Timer();
		timer.startTimer();
		RuleSet allRuleSets = new RuleSet(new ArrayList<Rule>());
		
		for (Transaction itemset : finalLargeItemSet.getTransactionSet()) {
			
			ArrayList<ItemSet> possibleRules = new ArrayList<ItemSet>();
			
			possibleRules = findRuleSubsets(itemset.getTransaction(), possibleRules);
	
			for (ItemSet subset : possibleRules) {
				
				double confidencePart1 = transSet.findSupport(itemset.getTransaction());
				double confidencePart2 = transSet.findSupport(subset);
				double confidence = (confidencePart1 / confidencePart2);
				if (confidence >= minConfidenceLevel) {
					
					Rule rule = new Rule();
					
					rule.setX(subset);
					
					
					ArrayList<Item> items = new ArrayList<Item>(itemset.getTransaction().getItemSet());
					ItemSet consequent = new ItemSet(items);
					
					for(int j = 0; j < subset.getItemSet().size(); j++){
						consequent.getItemSet().remove(subset.getItemSet().get(j));
					}
					/*Format to 4 decimal places */
					double formatConfidence = Math.round(confidence*10000.0)/10000.0;
					
					rule.setY(consequent);
					rule.setMinSupportLevel(transSet.findSupport(itemset.getTransaction()));
					rule.setActualConfidenceLevel(formatConfidence);
					
					if(rule.getX().getItemSet().size() > 0  && rule.getY().getItemSet().size() > 0){
						allRuleSets.getRuleSet().add(rule);
					}
				
				}
		
			
			}
			
		}		
		
		allRuleSets.setTimestamp();
		System.out.println("RuleSets:\n" + allRuleSets.toString());
		timer.stopTimer();
		//System.out.println("Generator.GenerateRuleSets: Time in Milliseconds" + timer.getTotal());
		return allRuleSets;
	}

	
	
	/*method to find all combinations of rule subsets to help trim the final rule set generation*/
	public static ArrayList<ItemSet> findRuleSubsets(ItemSet candidates, ArrayList<ItemSet> sets){
		Timer timer = new Timer();
		timer.startTimer();
		ArrayList<ItemSet> allRuleSets = sets;
		
		if(!allRuleSets.contains(candidates)){
			allRuleSets.add(candidates);
		}
		
		for(int i = 0; i < candidates.getItemSet().size();i++){
			
			ArrayList<Item> subset = new ArrayList<Item>(candidates.getItemSet());
			subset.remove(i);
			ItemSet newSet = new ItemSet(subset);
			findRuleSubsets(newSet, allRuleSets);
			
		}
		timer.stopTimer();
		//System.out.println("Generator.findRuleSubsets: Time in Milliseconds" + timer.getTotal());
		return allRuleSets;
	}
	
}
