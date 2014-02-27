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
		this.transactionSet = transactionSet;
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
					System.out.println("{"+item.getItem()+"}");
					uniqueItems.getItemSet().add(item);
				}

			}
		}

		return uniqueItems;
	}
	
	
	
public void getUniqueItemCounts(ItemSet I){
		System.out.println("Part 2: Scan transaction set for count of each candidate single-item set");
		ArrayList<Integer> itemCounts = new ArrayList<Integer>();
		String itemToCount = "";
		String examinedItem = "";
		int uniqueSize = I.getItemSet().size();
		for (int i = 0; i < uniqueSize; i++) {
			itemToCount = I.getItemSet().get(i).getItem();
			itemCounts.add(0);
			for (int j = 0; j < this.transactionSet.size(); j++) {
				for (int k = 0; k < this.transactionSet.get(j).getTransaction().getItemSet().size(); k++) {
					examinedItem =this.transactionSet.get(j).getTransaction().getItemSet().get(k).getItem();
					if (itemToCount.equalsIgnoreCase(examinedItem)) {
						itemCounts.set(i, itemCounts.get(i) + 1);
					}
				}
				
			}
			System.out.println("{"+I.getItemSet().get(i).getItem()
					+ "}-" + (itemCounts.get(i)));
		}
	}

}
