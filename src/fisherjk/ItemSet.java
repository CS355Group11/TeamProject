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
		System.out.println("called equals in itemset");
		// TODO Auto-generated method stub
		ItemSet itemSet = (ItemSet) obj;
		// if (this.itemSet.size() == itemSet.getItemSet().size()) {
		for (int i = 0; i < this.itemSet.size(); i++) {
			if (!this.itemSet.get(i).getItem()
					.equalsIgnoreCase(itemSet.getItemSet().get(i).getItem())) {
				return false;
			}
		}

		return true;
		// }
		// return false;
	}

	public boolean contains(ArrayList<Item> itemSet2) {// uses the equals method

		// System.out.println("In contains");
		// System.out.println("First: "+ this.itemSet);
		// System.out.println("Next: "+ itemSet2);
		boolean match = false;
		for (int i = 0; i < this.itemSet.size(); i++) {
			// System.out.println("i: " + i + " " +this.
			// itemSet.get(i).getItem());
			for (int j = 0; j < itemSet2.size(); j++) {
				// System.out.println("j: " + j + " " +
				// itemSet2.get(j).getItem());
				// System.out.println(this.itemSet.get(i).getItem() + " " +
				// itemSet2.get(i).getItem());
				if (itemSet2.get(j).getItem()
						.equals(this.itemSet.get(i).getItem())) {
					// System.out.println(this.itemSet.get(i).getItem() +
					// " vs. " + itemSet2.get(j).getItem());
					match = true;
				}
			}
		}
		// System.out.print("-> ItemSet Content Match?: " + match +"\n");
		return match;
	}

	public boolean containsItemSet(ArrayList<Item> itemSet2) {
		for (int i = 0; i < this.itemSet.size(); i++) {
		
			for (int j = 0; j < itemSet2.size(); j++) {
				Item item = itemSet2.get(j);
				if (!this.itemSet.contains(item)) {
					return false;
				}
			}
		}
		// System.out.print("-> ItemSet Content Match?: " + match +"\n");
		return true;
		
	}

}
