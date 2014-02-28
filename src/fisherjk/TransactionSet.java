package fisherjk;

import java.util.ArrayList;

public class TransactionSet {

	private ArrayList<Transaction> transactionSet;

	// List of Transactions
	public TransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
	}

	public TransactionSet() {
		// TODO Auto-generated constructor stub
		this.transactionSet = new ArrayList<Transaction>();
	}

	public ArrayList<Transaction> getTransactionSet() {
		return transactionSet;
	}

	public void setTransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
	}

	// START WORKING HERE
	public ItemSet GetUniqueItems() {
		System.out.println("Part 1: Generate all candidate single-item sets");
		ItemSet uniqueItems = new ItemSet();
		for (int j = 0; j < transactionSet.size(); j++) {
			for (int i = 0; i < transactionSet.get(j).getTransaction()
					.getItemSet().size(); i++) {
				Item item = transactionSet.get(j).getTransaction().getItemSet()
						.get(i);
				if (!uniqueItems.getItemSet().contains(item)) {
					System.out.println("{" + item.getItem() + "}");
					uniqueItems.getItemSet().add(item);
				}

			}
		}

		return uniqueItems;
	}

	public void getUniqueItemCounts(ItemSet I) {
		System.out
				.println("Part 2: Scan transaction set for count of each candidate single-item set");
		ArrayList<Integer> itemCounts = new ArrayList<Integer>();
		String itemToCount = "";
		String examinedItem = "";
		int uniqueSize = I.getItemSet().size();
		for (int i = 0; i < uniqueSize; i++) {
			itemToCount = I.getItemSet().get(i).getItem();
			itemCounts.add(0);
			for (int j = 0; j < this.transactionSet.size(); j++) {
				for (int k = 0; k < this.transactionSet.get(j).getTransaction()
						.getItemSet().size(); k++) {
					examinedItem = this.transactionSet.get(j).getTransaction()
							.getItemSet().get(k).getItem();
					if (itemToCount.equalsIgnoreCase(examinedItem)) {
						itemCounts.set(i, itemCounts.get(i) + 1);
					}
				}

			}
			// System.out.println("{" + I.getItemSet().get(i).getItem() + "}-"
			// + (itemCounts.get(i)));
		}
	}

	@Override
	public String toString() {// Essentially a specializied toString

		int num = this.transactionSet.size();
		String transactionSetItems = "";
		// for(int j = 0; j < this.transaction.size(); j++){
		for (int i = 0; i < num; i++) {
			String item = this.transactionSet.get(i).toString();
			transactionSetItems = transactionSetItems + "T" + i + ": " + item;
			if (i < num - 1) {
				transactionSetItems = transactionSetItems + "\n";
			}
			// }

		}
		return transactionSetItems;

	}

	public int findSupportOld1(ItemSet itemSupport) {// get the individual
													// support
													// for each itemSet
		// System.out.println("itemSuppot: " + itemSupport.toString());
		int count = 0;
		boolean found = false;
		int num = this.transactionSet.size();
		// System.out.println("Transaction set size: " + num);
		for (int i = 0; i < transactionSet.size(); i++) {
			for (int k = 0; k < itemSupport.getItemSet().size(); k++) {
				// System.out.println("Transaction: " +
				// this.transactionSet.get(k).getTransaction().toString() +
				// " ");
				System.out.println(this.transactionSet.get(i).getTransaction()
						.getItemSet()
						+ " contains? " + itemSupport.getItemSet());
				if (this.transactionSet.get(i).getTransaction().getItemSet()
						.contains(itemSupport.getItemSet().get(k))) {
					count++;

				}

			}

		}
		return count;
	}

	public int findSupportOld(ItemSet itemSupport) {// get the individual
													// support
		// for each itemSet
		// System.out.println("itemSuppot: " + itemSupport.toString());
		int count = 0;
		boolean found = false;
		int num = this.transactionSet.size();
		// System.out.println("Transaction set size: " + num);
		for (int i = 0; i < transactionSet.size(); i++) {
			for (int k = 0; k < itemSupport.getItemSet().size(); k++) {
				// System.out.println("Transaction: " +
				// this.transactionSet.get(k).getTransaction().toString() +
				// " ");
				System.out.println(this.transactionSet.get(i).getTransaction()
						+ " contains? " + itemSupport.getItemSet());
				/*
				if (this.transactionSet.get(i)
						.contains(itemSupport.getTransaction())) {
				*/
				if (this.transactionSet.get(i).getTransaction().contains(itemSupport.getItemSet())) {
					
					count++;

				}

			}

		}
		return count;
	}

	public int findSupport(Transaction itemSupport) {// get the individual
														// support
		// for each itemSet
		// System.out.println("itemSuppot: " + itemSupport.toString());
		int count = 0;
		int num = this.transactionSet.size();
		// System.out.println("Transaction set size: " + num);
		for (int i = 0; i < transactionSet.size(); i++) {
			for (int k = 0; k < itemSupport.getTransaction().getItemSet()
					.size(); k++) {
				// System.out.println("Transaction: " +
				// this.transactionSet.get(k).getTransaction().toString() +
				// " ");
				// System.out.println(this.transactionSet.get(i).getTransaction()
				// .getItemSet()
				// + " contains? " + itemSupport.getTransaction().getItemSet());

				System.out.println(this.transactionSet.get(i).getTransaction()
						+ " contains? "
						+ itemSupport.getTransaction().getItemSet());
				if (this.transactionSet
						.get(i)
						.getTransaction()
						.getItemSet()
						.contains(
								itemSupport.getTransaction().getItemSet()
										.get(k))) {
					count++;

				}

			}

		}
		return count;
	}

	public int Count() {
		return this.transactionSet.size();

	}

}
