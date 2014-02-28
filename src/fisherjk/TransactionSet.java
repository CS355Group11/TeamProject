package fisherjk;

import java.util.ArrayList;

/* Class for holding information about a set of transactions in a TransactionSet*/
public class TransactionSet {

	
	private ArrayList<Transaction> transactionSet;//A transactionSet private instance variable is an ArrayList of individual Transactions

	/*TransactionSet constructors*/
	public TransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
	}

	public TransactionSet() {
		this.transactionSet = new ArrayList<Transaction>();
	}

	/*Respective getters and setters*/
	public ArrayList<Transaction> getTransactionSet() {
		return transactionSet;
	}

	public void setTransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
	}

	
	/*Returns an itemSet containing a set of unique items found in a transactionSet which consists of Transactions*/
	public ItemSet GetUniqueItems() {
		ItemSet uniqueItems = new ItemSet();//uniquely constructed itemSet
		for (int j = 0; j < transactionSet.size(); j++) {//loop through the first transactionSet object
			for (int i = 0; i < transactionSet.get(j).getTransaction()
					.getItemSet().size(); i++) {//doubly loop through each respective itemset in a transactionSet
				Item item = transactionSet.get(j).getTransaction().getItemSet()
						.get(i);
				if (!uniqueItems.getItemSet().contains(item)) {//Only add items if not already within the unique list. Otherwise do nothing.
					//System.out.println("{" + item.getItem() + "}");
					uniqueItems.getItemSet().add(item);
				}

			}
		}

		return uniqueItems;
	}

	/*Override the TransactionSet's toString to call the Transaction's to String*/
	@Override
	public String toString() {

		int num = this.transactionSet.size();
		String transactionSetItems = "";
		for (int i = 0; i < num; i++) {
			String item = this.transactionSet.get(i).getTransaction().toString();
			transactionSetItems = transactionSetItems + "T" + i + ": " + item;
			if (i < num - 1) {
				transactionSetItems = transactionSetItems + "\n";
			}

		}
		return transactionSetItems;

	}
	

	/*Returns the frequency support count for an individual itemSet*/
	/*ItemSet's can be one to many items in size*/
	public int findSupport(ItemSet itemSupport) {// get the individual
		int supportCount = 0;//keep track of the counte
		int num = this.transactionSet.size();
		for (int i = 0; i < num; i++) {
				if (this.transactionSet.get(i).getTransaction().containsItemSet(itemSupport.getItemSet())) {
					supportCount++;

				}
		}
		return supportCount;
	}

	
}
