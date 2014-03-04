package fisherjk;

import java.util.ArrayList;
import java.util.List;

public class AssociationRule {

	private ItemSet x;
	private ItemSet y;
	private double minSupportLevel;// must have 4 digits of precision
	private double minConfidenceLevel;// must have 4 digits of precision

	public AssociationRule(ItemSet x, ItemSet y, double minSupportLevel,
			double minConfidenceLevel) {
		this.x = x;
		this.y = y;
		this.minSupportLevel = minSupportLevel;
		this.minConfidenceLevel = minConfidenceLevel;
	}

	public AssociationRule() {
		this.x = new ItemSet();
		this.y = new ItemSet();
		this.minSupportLevel = 0;
		this.minConfidenceLevel = 0;
	}

	public ItemSet getX() {
		return x;
	}

	public void setX(ItemSet x) {
		this.x = x;
	}

	public ItemSet getY() {
		return y;
	}

	public void setY(ItemSet y) {
		this.y = y;
	}

	public double getMinSupportLevel() {
		return minSupportLevel;
	}

	public void setMinSupportLevel(double minSupportLevel) {
		this.minSupportLevel = minSupportLevel;
	}

	public double getMinConfidenceLevel() {
		return minConfidenceLevel;
	}

	public void setMinConfidenceLevel(double minConfidenceLevel) {
		this.minConfidenceLevel = minConfidenceLevel;
	}

	@Override
	public String toString() {
		return "IF " + this.x + " THEN " + this.y + " (" + this.minConfidenceLevel
				+ ")";
	}

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

				double confidencePart1 = transSet.findSupport(itemset.getTransaction());
				double confidencePart2 = transSet.findSupport(subset);
				double confidence = (confidencePart2 / confidencePart1) * 100.0;
				System.out.println(subset + "-->" + confidencePart2 +"/"+ confidencePart1);
				if (confidence >= confidenceThreshold) {
					System.out.println("THIS: " + subset +": " + confidence);
					AssociationRule rule = new AssociationRule();
					rule.x = subset;
					System.out.println("AFTER SET X: " + rule.x  +" vs. "+ rule.getX());
					ItemSet consequent = new ItemSet(itemset.getTransaction().getItemSet());
					
					//System.out.println("RULE.Y"+rule.y.getItemSet());
					for(int j = 0; j < subset.getItemSet().size(); j ++){
						//System.out.println("Removing: " + subset.getItemSet().get(j));
						consequent.getItemSet().remove(subset.getItemSet().get(j));
					}
					rule.y = consequent;
					System.out.println("AFTER SET Y: " + rule.toString());
					rule.minSupportLevel = transSet.findSupport(itemset.getTransaction());
					rule.minConfidenceLevel = confidence;
					if(rule.x.getItemSet().size() > 0  && rule.y.getItemSet().size() > 0){
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

	public static ArrayList<AssociationRule> GenerateRules(
			TransactionSet transSet, TransactionSet finalLargeItemSet,
			double confidenceThreshold) {
		System.out.println("Inside Generating rule's method");
		ArrayList<AssociationRule> allRules = new ArrayList<AssociationRule>();

		for (Transaction itemset : finalLargeItemSet.getTransactionSet()) {
			TransactionSet subsets = transSet.findKItemSubsets(
					itemset.getTransaction(), 0); // get all subsets
			System.out.println("subsets: \n" + subsets.toString());
			for (Transaction subset : subsets.getTransactionSet()) {

				double confidencePart1 = transSet.findSupport(itemset
						.getTransaction());
				double confidencePart2 = transSet.findSupport(subset
						.getTransaction());
				double confidence = (confidencePart1 / confidencePart2) * 100.0;
				if (confidence >= confidenceThreshold) {
					AssociationRule rule = new AssociationRule();
					ItemSet X = subset.getTransaction();
					rule.getX().getItemSet()
							.addAll((subset.getTransaction().getItemSet()));
					// System.out.println("TO REMOVE: " +
					// itemset.remove(subset));
					rule.getY().setItemSet(itemset.remove(subset).getItemSet());
					// System.out.println("RULE GET X\n" +
					// rule.getX().toString());
					rule.setMinSupportLevel(transSet.findSupport(itemset
							.getTransaction()));
					rule.setMinConfidenceLevel(confidence);

					if (rule.getX().getItemSet().size() > 0
							&& rule.getY().getItemSet().size() > 0) {// may need
																		// to
																		// fix{
						allRules.add(rule);
						System.out.println(rule.toString());
					}
				}
			}
		}
		return allRules;
	}

	public static ArrayList<AssociationRule> GenerateRulesOld(
			TransactionSet transSet, TransactionSet finalLargeItemSet,
			double confidenceThreshold) {
		System.out.println("Inside Generating rule's method");
		ArrayList<AssociationRule> allRules = new ArrayList<AssociationRule>();

		for (Transaction itemset : finalLargeItemSet.getTransactionSet()) {
			System.out.println("itemset: " + itemset.toString());
			TransactionSet subsets = transSet.findKItemSubsets(
					itemset.getTransaction(), 0); // get all subsets
			System.out.println("subsets: \n" + subsets.toString());
			for (Transaction subset : subsets.getTransactionSet()) {
				// System.out.println("Inner for loop: ");
				double confidence = ((transSet.findSupport(itemset
						.getTransaction())) / transSet.findSupport(subset
						.getTransaction())) * 100.0;
				// System.out.println("confidence: " + confidence);
				if (confidence >= confidenceThreshold) {
					AssociationRule rule = new AssociationRule();
					ItemSet X = subset.getTransaction();
					System.out.println("X is: " + X.toString());
					rule.getX().getItemSet().addAll(X.getItemSet());
					ItemSet Y = itemset.remove(subset);// may need to fix
					System.out.println("Y is " + Y.toString());
					rule.getY().getItemSet().addAll(Y.getItemSet());
					rule.setMinSupportLevel(transSet.findSupport(itemset
							.getTransaction()));
					rule.setMinConfidenceLevel(confidence);
					if (rule.getX().getItemSet().size() > 0
							&& rule.getY().getItemSet().size() > 0)// may need
																	// to fix
					{
						allRules.add(rule);
						System.out.println(rule.toString());
					}
				}
			}
		}
		System.out.println("Reached return statement");

		System.out.println("tranSet: \n" + transSet.toString());
		System.out.println("finalSet: \n" + finalLargeItemSet.toString());

		for (int i = 0; i < allRules.size(); i++) {
			System.out.println("Rule " + i + ": " + allRules.get(i).toString());
		}

		return (allRules);
	}
	
	public static ArrayList<ItemSet> findRuleSubsets(ItemSet candidates, ArrayList<ItemSet> sets){
		System.out.println("set passed in: " + sets);
		ArrayList<ItemSet> allRuleSets = sets;
		System.out.println("BEFORE: " + allRuleSets);
		System.out.println("candidates: " + candidates);
		if(!allRuleSets.contains(candidates)){
			System.out.println("adding: " + candidates.getItemSet());
			allRuleSets.add(candidates);
			System.out.println("AFTER:" + allRuleSets);
		}
		
		
		for(int i = 0; i < candidates.getItemSet().size();i++){
			System.out.println("candidate size: " + candidates.getItemSet().size());
			ArrayList<Item> subset = new ArrayList<Item>(candidates.getItemSet());
			System.out.println("subset: " + subset);
			System.out.println("Removing: " + subset.get(i));
			subset.remove(i);
			
			
			
			ItemSet newSet = new ItemSet(subset);
			findRuleSubsets(newSet, allRuleSets);
			
		}
		System.out.println("FINAL: " + allRuleSets);
		return allRuleSets;
	}
	
	
	/*

	public static ArrayList<ItemSet> findRuleSubsets(ItemSet itemSet) {
		TransactionSet allSubsets = new TransactionSet();// New subset of
															// transactions to
															// return in a
															// TransactionSet
		allSubsets = (transSet.findKItemSubsets(finalLargeItemSet.GetUniqueItems(), k));
		//System.out.println("all subsets: " + allSubsets.toString());
		ArrayList<ItemSet> itemSetPossible = new ArrayList<ItemSet>();
		for (int i = 0; i < allSubsets.getTransactionSet().size(); i++) {
			ItemSet toAdd = allSubsets.getTransactionSet().get(i)
					.getTransaction();
			itemSetPossible.add(toAdd);
		}

		for (int j = 0; j < itemSetPossible.size(); j++) {
			System.out.println("Possible Rule #" + j + "->"
					+ itemSetPossible.get(j));
		}
		return itemSetPossible;// final combination of all possible subsets
								// based on the size of k
	}
*/
}
