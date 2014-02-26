package fisherjk;


import java.util.ArrayList;

public class ItemSet {//Is this a transaction?

	private ArrayList<Item> itemSet;
	private double itemSetSupport;

	//List of Items
	public ItemSet(ArrayList<Item> itemSet) {
		this.itemSet = itemSet;
	}
	
	public ItemSet(){
		this.itemSet = new ArrayList<Item>();
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

	public void setSupport(double itemSetSupport) {
		this.itemSetSupport = itemSetSupport;
	}
	
	
	

	
	
}
