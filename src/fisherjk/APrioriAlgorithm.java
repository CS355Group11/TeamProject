package fisherjk;


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
			for (Transaction transaction : CandidateItemSet.getTransactionSet()) {//loop through each transaction in each TransactionSet
				int findSupport = transSet.findSupport(transaction.getTransaction());//calculate and find each successive support level for an transaction (remember it is an itemSet)
				transaction.getTransaction().setItemSetSupport(findSupport);//Set each successive support level for its respective transaction (remember it is an itemSet)

				
				if (transaction.getTransaction().getItemSetSupport() >= supportThreshold) {//Determine if the itemSet's support level meets or exceeds the supportThreshold to filter out
					
					System.out.println(transaction.toString() + "-" + findSupport);//debugging info
					
					LargeItemSet.getTransactionSet().add(transaction);
					if (transaction.getTransaction().getItemSet().size() > 1) {// this might not work in the long run
						finalLargeItemSet.getTransactionSet().add(transaction);//add the resultingLargeItemSet of final 
					}
				}

			}


			CandidateItemSet.getTransactionSet().clear();//clear up the candidates to prep for finding all subsets
			CandidateItemSet = (FindAllSubsets(LargeItemSet.GetUniqueItems(), k));//Add all the combinations of subsets for a given set of unique items/ItemSets
			k += 1;

		}
		System.out.println("Final Set: \n" + finalLargeItemSet.toString());
		return finalLargeItemSet;//final returned value


	}

	/*Necessary for determining subsets based on the bits of an index*/
	public static int GetBit(int value, int position) {
		int bit = value & (int) Math.pow(2, position);
		return (bit > 0 ? 1 : 0);
	}

	
	/*Determines and returns all possible combinations of subsets based on a given itemSet. This allows filtering to take place as the next loop iteration starts up */
	public static TransactionSet FindAllSubsets(ItemSet itemSet, int k) {
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

}
