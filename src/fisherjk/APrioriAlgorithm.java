package fisherjk;

public class APrioriAlgorithm {


public static Transaction APriori(Transaction transaction, double minSupportLevel){
	
	ItemSet I = transaction.getUniqueItems();
	Transaction L = new Transaction();//resultant large itemsets
	Transaction Li = new Transaction();//large itemset in each iteration
	Transaction Ci = new Transaction();//candidate item set in each iteration
	
	
	/*
	 * Start working here next:
	 */
	
	/*
	//first iteration (1-item Itemsets)
	for(int i = 0; i < I.size(); i++){//convert to foreach loop
		Ci.add(new ItemSet());
	}
	
	int k = 2;
	while (Ci.size()!=0){
		Li.clear();
		
		for(int j = 0; j < Ci.size(); j++){
			Ci.get(k).Support = transaction.FindSupport(itemSet)
		}
	}
	
	
	*/
	
}

	
}
