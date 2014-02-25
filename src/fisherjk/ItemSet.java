package fisherjk;


import java.util.ArrayList;

public class ItemSet {//Is this a transaction?

	private ArrayList<Item> itemSet;

	public ItemSet(ArrayList<Item> itemSet) {
		this.itemSet = itemSet;
	}

	public ArrayList<Item> getItemSet() {
		return itemSet;
	}

	public void setItemSet(ArrayList<Item> itemSet) {
		
		this.itemSet = itemSet;
	}

	
	
}
