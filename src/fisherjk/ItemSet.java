package fisherjk;

import java.util.ArrayList;

public class ItemSet {// Is this a transaction?

	private ArrayList<Item> itemSet;
	private double itemSetSupport;

	// List of Items
	public ItemSet(ArrayList<Item> itemSet) {
		this.itemSet = itemSet;
		this.itemSetSupport = 0;
	}

	public ItemSet() {
		this.itemSet = new ArrayList<Item>();
		this.itemSetSupport = 0;
	}

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

	@Override
	public boolean equals(Object obj) {// necessary for the contains method
		// TODO Auto-generated method stub
		ItemSet itemSet = (ItemSet) obj;
		if (this.itemSet.size() == itemSet.getItemSet().size()) {
			for (int i = 0; i < this.itemSet.size(); i++) {
				if (!this.itemSet
						.get(i)
						.getItem()
						.equalsIgnoreCase(itemSet.getItemSet().get(i).getItem())) {
					return false;
				}
			}

			return true;
		}
		return false;
	}

	public boolean contains(ArrayList<Item> itemSet2) {// uses the equals method
		System.out.println("In contains");
		boolean match = false;
		for (int i = 0; i < this.itemSet.size(); i++) {
			if (this.itemSet.get(i).getItem().equals(itemSet2.get(i).getItem())) {
				match = true;
			} else
				match = false;

		}
		return match;
	}

}
