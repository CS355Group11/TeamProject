package fisherjk;


import java.util.ArrayList;

public class ItemSet {//Is this a transaction?

	private ArrayList<Item> itemSet;
	private double itemSetSupport;

	//List of Items
	public ItemSet(ArrayList<Item> itemSet) {
		this.itemSet = itemSet;
		this.itemSetSupport = 0;
	}
	
	public ItemSet(){
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

	public void setSupport(double itemSetSupport) {
		this.itemSetSupport = itemSetSupport;
	}

	@Override
	public String toString() {
		String itemSetContents = "";
		int num = this.itemSet.size();
		for(int i = 0; i < this.itemSet.size(); i++){
			itemSetContents = itemSetContents + this.itemSet.get(i).toString();
			System.out.println("i: " + i);
			if(i < num-1){
				itemSetContents = itemSetContents +",";
			}
		}
		
		return "{"+itemSetContents+"}";
	}
	
	
	
	public boolean equals(ItemSet obj) {
		// TODO Auto-generated method stub
		ItemSet itemSet = (ItemSet) obj;
		if(this.itemSet.contains(itemSet)){
			System.out.println("true");
			return true;
		}else{
			System.out.println("false");
			return false;
		}
	}

	public boolean contains(ArrayList<Item> itemSet2) {
		System.out.println("In contains");
		boolean match = false;
		for(int i = 0; i < this.itemSet.size(); i++){
			if(this.itemSet.get(i).getItem().equals(itemSet2.get(i).getItem())){
				match = true;
			}else
				match = false;
			
		}
		return match;
	}
	
	
	
	

	
	
}
