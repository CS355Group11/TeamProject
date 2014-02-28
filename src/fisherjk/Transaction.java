package fisherjk;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {

	private ItemSet transaction;
	private Date transactionDate;
	private double transactionTotalPrice;

	// List of ItemSets
	public Transaction(ItemSet transaction) {
		this.transaction = transaction;
		this.transactionDate = transactionDate;
	}

	public Transaction() {
		this.transaction = new ItemSet();
	}

	public ItemSet getTransaction() {
		return transaction;
	}

	public void setTransaction(ItemSet transaction) {
		this.transaction = transaction;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getTransactionTotalPrice(Transaction transaction) {
		return transactionTotalPrice;

	}

	public ItemSet getUniqueItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {// Essentially a specializied toString
		// TODO Auto-generated method stub

		String transactionItems = "";
		int num = this.transaction.getItemSet().size();
		for (int i = 0; i < num; i++) {
			String item = this.transaction.getItemSet().get(i).toString();
			transactionItems = transactionItems + item;
			if (i < num - 1) {
				transactionItems = transactionItems + ",";
			}

		}
		return "{" + transactionItems + "}";

	}
	
	
	
	
	@Override
	public boolean equals(Object obj) {// necessary for the contains method
		// TODO Auto-generated method stub
		boolean equivalent = false;
		Transaction transaction = (Transaction) obj;
		//if (this.transaction.getItemSet().size() == itemSet.getItemSet().size()) {
			for (int i = 0; i < this.transaction.getItemSet().size(); i++) {
				if (!this.transaction.getItemSet()
						.equals(transaction.getTransaction())) {
					equivalent =  false;
				}
			}

			equivalent =  true;
		//}
			System.out.println("equivalent");
		return equivalent;
	}
	/*
	public boolean contains(ArrayList<ItemSet> itemSet2) {// uses the equals method

		System.out.println("In contains");
		boolean match = false;
		for (int i = 0; i < this.transaction.getItemSet().size(); i++) {
			if (this.transaction.getItemSet().get(i).getItem().equals(itemSet2.get(i).getItemSet().get(i).getItem())) {
				match = true;
			} else
				match = false;

		}
		System.out.println("Match?: " + match);
		return match;
	}
	*/
	public boolean contains(ItemSet itemSupport) {
		//System.out.println("In contains");
		boolean match = false;
		//for (int i = 0; i < this.transaction.getItemSet().size(); i++) {
			//System.out.println("in for loop");
			if (this.transaction.equals(itemSupport)) {
				match = true;
			} else
				match = false;
		//}
		System.out.println("Trans Match?: " + match);
		return match;
	
	}

	

}
