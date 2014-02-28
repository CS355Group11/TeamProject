package fisherjk;

import java.util.ArrayList;

/* Class for holding information about a set of items known as an ItemSet*/
public class ItemSet {

	private ArrayList<Item> itemSet;//unique ItemSet private variable placeholder
	private double itemSetSupport;//unique ItemSet support level. Essentially a frequency count to be used by the toString and findSupport method

	//ItemSet Constructors with initial values
	public ItemSet(ArrayList<Item> itemSet) {
		this.itemSet = itemSet;
		this.itemSetSupport = 0;
	}

	public ItemSet() {
		this.itemSet = new ArrayList<Item>();
		this.itemSetSupport = 0;
	}

	
	/*ItemSet's respective private instance variable getters and setters*/
	public ArrayList<Item> getItemSet() {
		return itemSet;
	}

	public void setItemSet(ArrayList<Item> itemSet) {

		this.itemSet = itemSet;
	}

	public double getItemSetSupport() {
		return itemSetSupport;
	}

	public void setItemSetSupport(double itemSetSupport) {
		this.itemSetSupport = itemSetSupport;
	}

	/* Override the ItemSet toString to display itemSet contents with support level tagged on at the very end*/
	@Override
	public String toString() {
		String itemSetContents = "";
		int num = this.itemSet.size();
		for (int i = 0; i < this.itemSet.size(); i++) {
			itemSetContents = itemSetContents + this.itemSet.get(i).toString();
			if (i < num - 1) {
				itemSetContents = itemSetContents + ",";
			}
		}

		return "{" + itemSetContents + "}" + "-" + (this.itemSetSupport);
	}

	/*Override the ItemSet equals method to be used for a later contains method call*/
	@Override
	public boolean equals(Object obj) {
		System.out.println("called equals in itemset");
		ItemSet itemSet = (ItemSet) obj;//necessary casting
		for (int i = 0; i < this.itemSet.size(); i++) {
			if (!this.itemSet.get(i).getItem()
					.equalsIgnoreCase(itemSet.getItemSet().get(i).getItem())) {
				return false;
			}
		}

		return true;
	}

	public boolean contains(ArrayList<Item> itemSet2) {// uses the equals method

		
		boolean match = false;
		for (int i = 0; i < this.itemSet.size(); i++) {//
			
			for (int j = 0; j < itemSet2.size(); j++) {
				
				if (itemSet2.get(j).getItem()
						.equals(this.itemSet.get(i).getItem())) {
					match = true;
				}
			}
		}
		
		return match;
	}

	/*Improved contains method to check if a given itemSet2 is a subset of itemSet*/
	public boolean containsItemSet(ArrayList<Item> subItemSet) {
		for (int i = 0; i < this.itemSet.size(); i++) {//loop through biggest itemSet
			for (int j = 0; j < subItemSet.size(); j++) {//check against inner subset
				Item item = subItemSet.get(j);
				if (!this.itemSet.contains(item)) {//does this item set contain this item -> if at any point it fails then we know to return false
					return false;
				}
			}
		}
		return true;
	}

}
