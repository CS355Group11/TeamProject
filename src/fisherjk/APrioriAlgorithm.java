package fisherjk;

import java.util.ArrayList;
import java.util.List;


/*Class to Run the Part I of the A Priori Algorithm */
public class APrioriAlgorithm {

	public static TransactionSet DoApriori(TransactionSet transSet,
			double supportThreshold) {

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
				transaction.getTransaction().setItemSetSupport(findSupport);//Set each successive support level for its respective transaction (remember it is an itemSet)

				
				if (transaction.getTransaction().getItemSetSupport() >= supportThreshold) {//Determine if the itemSet's support level meets or exceeds the supportThreshold to filter out
					//System.out.println("k = " + k);
					System.out.println(transaction.toString() + "-" + findSupport);//debugging info
					
					LargeItemSet.getTransactionSet().add(transaction);
					if (transaction.getTransaction().getItemSet().size() > 1) {// this might not work in the long run
						
					
					finalLargeItemSet.getTransactionSet().add(transaction);//add the resultingLargeItemSet of final 
					}
				}

			}


			CandidateItemSet.getTransactionSet().clear();//clear up the candidates to prep for finding all subsets
			System.out.println("AFTER CLEAR candidate item set size: " + CandidateItemSet.getTransactionSet().size());
			CandidateItemSet = (CandidateItemSet.findKItemSubsets(LargeItemSet.GetUniqueItems(), k));//Add all the combinations of subsets for a given set of unique items/ItemSets
			//System.out.println("GET UNIQUE ITEMS: \n" + LargeItemSet.GetUniqueItemSets().toString());
			k += 1;
			System.out.println("END");
		}
		System.out.println("Final Set: \n" + finalLargeItemSet.toString());
		
		return finalLargeItemSet;//final returned value


	}
	
	
/*
public static List<AssociationRule> GenerateRulesOld(TransactionSet transSet, TransactionSet finalLargeItemSet, double confidenceThreshold){
	    System.out.println("Inside Generating rule's method");
		List<AssociationRule> allRules = new ArrayList<AssociationRule>();
	 
		
		
		
	    for (Transaction itemset : finalLargeItemSet.getTransactionSet()){
	    	System.out.println("itemset: " + itemset.toString() );
	        TransactionSet subsets = findKItemSubsets(itemset.getTransaction(), 0); //get all subsets
	        System.out.println("subsets: \n" + subsets.toString());
	        for (Transaction subset : subsets.getTransactionSet()){
	        	//System.out.println("Inner for loop: ");
	        	double confidence = ((transSet.findSupport(itemset.getTransaction())) / transSet.findSupport(subset.getTransaction())) * 100.0;
	            //System.out.println("confidence: " + confidence);
	        	if (confidence >= confidenceThreshold){
	                AssociationRule rule = new AssociationRule();
	                ItemSet X = subset.getTransaction();
	                System.out.println("X is: " + X.toString());
	                rule.getX().getItemSet().addAll(X.getItemSet());
	                ItemSet Y = itemset.remove(subset);//may need to fix
	                System.out.println("Y is " + Y.toString());
	                rule.getY().getItemSet().addAll(Y.getItemSet());
	                rule.setMinSupportLevel(transSet.findSupport(itemset.getTransaction()));
	                rule.setMinConfidenceLevel(confidence);
	                if (rule.getX().getItemSet().size() > 0 && rule.getY().getItemSet().size() >0)//may need to fix
	                {
	                    allRules.add(rule);
	                    System.out.println(rule.toString());
	                }
	            }
	        }
	    }
	    System.out.println("Reached return statement");
	    
	    System.out.println("tranSet: \n" +transSet.toString());
	    System.out.println("finalSet: \n" +finalLargeItemSet.toString());
	    
	    for(int i = 0; i < allRules.size(); i++){
	    	System.out.println("Rule " + i + ": " + allRules.get(i).toString());
	    }
	    
	    
	    return (allRules);
}


public static ArrayList<AssociationRule> GenerateRules(TransactionSet transSet, TransactionSet finalLargeItemSet, double confidenceThreshold){
    System.out.println("Inside Generating rule's method");
	ArrayList<AssociationRule> allRules = new ArrayList<AssociationRule>();
	
	
 
	 for (Transaction itemset : finalLargeItemSet.getTransactionSet()){
		 TransactionSet subsets = findKItemSubsets(itemset.getTransaction(), 0); //get all subsets
		 System.out.println("subsets: \n" + subsets.toString());
		 for (Transaction subset : subsets.getTransactionSet()){
		 
			 double confidencePart1 = transSet.findSupport(itemset.getTransaction());
			 double confidencePart2 = transSet.findSupport(subset.getTransaction());
			 double confidence = (confidencePart1 / confidencePart2) * 100.0;
			 if (confidence >= confidenceThreshold){
				 AssociationRule rule = new AssociationRule();
	             ItemSet X = subset.getTransaction();
	             rule.getX().getItemSet().addAll((subset.getTransaction().getItemSet()));
	             //System.out.println("TO REMOVE: " + itemset.remove(subset));
	             rule.getY().setItemSet(itemset.remove(subset).getItemSet());
	             //System.out.println("RULE GET X\n" + rule.getX().toString());
	             rule.setMinSupportLevel(transSet.findSupport(itemset.getTransaction()));
	             rule.setMinConfidenceLevel(confidence);
	             
	             
	             if (rule.getX().getItemSet().size() > 0 && rule.getY().getItemSet().size() >0){//may need to fix{
	                    allRules.add(rule);
	                    System.out.println(rule.toString());
	             }
			 }
		 }
	 }
	 
	 
	 for(int i = 0; i < allRules.size(); i++){
	    	System.out.println("Rule " + i + ": " + allRules.get(i).toString());
	    }
	
	return allRules;
	
}
	
*/


	/*Necessary for determining subsets based on the bits of an index*/
	/*
	public static int GetBit(int value, int position) {
		int bit = value & (int) Math.pow(2, position);
		return (bit > 0 ? 1 : 0);
	}
	 */
	
	/*Determines and returns all possible combinations of subsets based on a given itemSet. This allows filtering to take place as the next loop iteration starts up */
	/*
	public static TransactionSet findAllSubsets(ItemSet itemSet, int k) {
		TransactionSet allSubsets = new TransactionSet();//New subset of transactions to return in a TransactionSet
		int subsetCount = (int) Math.pow(2, itemSet.getItemSet().size());//index control for loop
		int itemSetSize = itemSet.getItemSet().size();//size control for inner loop
		for (int i = 0; i < subsetCount; i++) {
			ItemSet subset = new ItemSet();
			for (int bitIndex = 0; bitIndex < itemSetSize; bitIndex++) {
				if (GetBit(i, bitIndex) == 1) {

					subset.getItemSet().add(itemSet.getItemSet().get(bitIndex));
				}
			}
			if (subset.getItemSet().size() == k - 1) {//accounting for missing Bit class. Seems to correct off-by indexing
				allSubsets.getTransactionSet().add(new Transaction(subset));//add the new transaction subset
			}
		}

		return (allSubsets);//final combination of all possible subsets based on the size of k
	}
	
	
	*/
	/*Determines and returns all possible combinations of subsets based on a given itemSet. This allows filtering to take place as the next loop iteration starts up */
	/*
	public static TransactionSet findKItemSubsets(ItemSet itemSet, int k) {
		TransactionSet allSubsets = new TransactionSet();//New subset of transactions to return in a TransactionSet
		int subsetCount = (int) Math.pow(2, itemSet.getItemSet().size());//index control for loop
		int itemSetSize = itemSet.getItemSet().size();//size control for inner loop
		for (int i = 0; i < subsetCount; i++) {
			
			if (k == 0 || GetOnCount(i, itemSet.getItemSet().size()) == k){
                String binary = DecimalToBinary(i, itemSet.getItemSet().size());
			
			ItemSet subset = new ItemSet();
			
			for (int bitIndex = 0; bitIndex < itemSetSize; bitIndex++) {
				if (GetBit(i, bitIndex) == 1) {
					subset.getItemSet().add(itemSet.getItemSet().get(bitIndex));
				}
			}
			//if(binary)
			
			
			//if (subset.getItemSet().size() == k - 1) {//accounting for missing Bit class. Seems to correct off-by indexing
				allSubsets.getTransactionSet().add(new Transaction(subset));//add the new transaction subset
			//}
			}
		}

		return (allSubsets);//final combination of all possible subsets based on the size of k
	}
	
	
	*/
	
	/*Determines and returns all possible combinations of subsets based on a given itemSet. This allows filtering to take place as the next loop iteration starts up */
	/*
	public static TransactionSet findKItemSubsets2(ItemSet itemSet, int k) {
		TransactionSet allSubsets = new TransactionSet();//New subset of transactions to return in a TransactionSet
		int subsetCount = (int) Math.pow(2, itemSet.getItemSet().size());//index control for loop
		int itemSetSize = itemSet.getItemSet().size();//size control for inner loop
		for (int i = 0; i < subsetCount; i++) {
			System.out.println("Get on Count: " + GetOnCount(i, itemSet.getItemSet().size()));
			if (k == 0 || GetOnCount(i, itemSet.getItemSet().size()) == k){
                String binary = DecimalToBinary(i, itemSet.getItemSet().size());
			
			ItemSet subset = new ItemSet();
			for(int charIndex = 0; charIndex < binary.length(); charIndex++){
			//for (int bitIndex = 0; bitIndex < itemSetSize; bitIndex++) {
			//	if (GetBit(i, bitIndex) == 1) {
				if (binary.toCharArray()[charIndex] == '1'){
					System.out.println("in here");
					subset.getItemSet().add(itemSet.getItemSet().get(charIndex));
					//subset.getItemSet().add(itemSet.getItemSet().get(bitIndex));
				}
			}
			//if(binary)
			
			
			//if (subset.getItemSet().size() == k - 1) {//accounting for missing Bit class. Seems to correct off-by indexing
				allSubsets.getTransactionSet().add(new Transaction(subset));//add the new transaction subset
			}
		}

		return (allSubsets);//final combination of all possible subsets based on the size of k
	}
	
		
		
		public static String DecimalToBinary(int value, int length){
            String binary = "";
            for (int position = 0; position < length; position++){
                binary = GetBit(value, position) + binary;
            }
            return binary;
        }

        public static int GetOnCount(int value, int length){
            String binary = DecimalToBinary(value, length);
            char[] binaryChar = binary.toCharArray();
            int count = 0;
           for(int i = 0; i < binary.length(); i++){
            	if(binaryChar[i] == '1'){
            		//return count;
            	//}
            	count++;
            }
           }
           return count;
        }
           
	*/
	
	public static ArrayList<AssociationRule> GenerateRuleSets(TransactionSet transSet, TransactionSet finalLargeItemSet,double confidenceThreshold) {
		ArrayList<AssociationRule> allRules = new ArrayList<AssociationRule>();
		//int i =0;
		for (Transaction itemset : finalLargeItemSet.getTransactionSet()) {
			System.out.println("itemSet ->" + itemset.toString());
			ArrayList<ItemSet> possibleRules = new ArrayList<ItemSet>();
			System.out.println("PASSED INTO FIND RULE SUBSETS: " + itemset.getTransaction());
			possibleRules = findRuleSubsets(itemset.getTransaction(), possibleRules);
			//for(int k = 0; k < possibleRules.size(); k++){
			//	System.out.println("possible rules: " + possibleRules.get(k));
			//}
			System.out.println("POSSIBLE: " + possibleRules);
			//TransactionSet subsets = transSet.findKItemSubsets(itemset.getTransaction(), 0); // get all subsets
			
			
			
			//System.out.println("subsets: \n" + subsets.toString());
			
			for (ItemSet subset : possibleRules) {
				System.out.println("CREATED POSSIBLE RULES: " + possibleRules);
				double confidencePart1 = transSet.findSupport(itemset.getTransaction());
				double confidencePart2 = transSet.findSupport(subset);
				double confidence = (confidencePart1 / confidencePart2) * 100.0;
				System.out.println(subset + "-->" + confidencePart2 +"/"+ confidencePart1);
				if (confidence >= confidenceThreshold) {
					//System.out.println("THIS: " + subset +": " + confidence);
					AssociationRule rule = new AssociationRule();
					//System.out.println("subset before set x: " + subset);
					rule.setX(subset);
					//System.out.println("AFTER SET X: " + rule);
					ArrayList<Item> items = new ArrayList<Item>(itemset.getTransaction().getItemSet());
					ItemSet consequent = new ItemSet(items);
					//System.out.println("consequent after set x:" + consequent);
					//System.out.println("RULE.Y"+rule.y.getItemSet());
					//System.out.println("size: " + subset.getItemSet().size());
					System.out.println("CREATED SUBSET: " + subset);
					for(int j = 0; j < subset.getItemSet().size(); j++){
						//System.out.println("Removing from subset: " + subset.getItemSet().get(j));
						consequent.getItemSet().remove(subset.getItemSet().get(j));
					}
					rule.setY(consequent);
					//System.out.println("AFTER SET Y: " + rule.toString());
					System.out.println("CREATED RULE: " + rule);
					rule.setMinSupportLevel(transSet.findSupport(itemset.getTransaction()));
					rule.setMinConfidenceLevel(confidence);
					if(rule.getX().getItemSet().size() > 0  && rule.getY().getItemSet().size() > 0){
						allRules.add(rule);
				}
				
			}
		
			//i++;
			}
			//i=0;
		}		
		System.out.println("All Rules: \n" + allRules.toString());

		for (int j = 0; j < allRules.size(); j++) {
			System.out.println("Rule " + j + ": " + allRules.get(j).toString());
		}
		return allRules;
	}

	
	
	
	public static ArrayList<ItemSet> findRuleSubsets(ItemSet candidates, ArrayList<ItemSet> sets){
		//System.out.println("set passed in: " + sets);
		ArrayList<ItemSet> allRuleSets = sets;
		//System.out.println("BEFORE: " + allRuleSets);
		System.out.println("candidates: " + candidates);
		int into = 0;
		for(int count = 0; count < allRuleSets.size(); count++){
		System.out.println("CURRENT ALLRULESETS CONTENTS: " + allRuleSets.get(count).getItemSet());
		}
		if(!allRuleSets.contains(candidates)){
			//System.out.println("adding: " + candidates.getItemSet());
			allRuleSets.add(candidates);
			//System.out.println("AFTER:" + allRuleSets);
			into++;
			System.out.println("INTO: " + into);
		}
		
		for(int i = 0; i < candidates.getItemSet().size();i++){
			//System.out.println("candidate size: " + candidates.getItemSet().size());
			ArrayList<Item> subset = new ArrayList<Item>(candidates.getItemSet());
			//System.out.println("subset: " + subset);
			//System.out.println("Removing: " + subset.get(i));
			subset.remove(i);
			System.out.println("INDEX: " + i);
			
			
			ItemSet newSet = new ItemSet(subset);
			findRuleSubsets(newSet, allRuleSets);
			
		}
		//System.out.println("FINAL: " + allRuleSets);
		return allRuleSets;
	}
	
}
