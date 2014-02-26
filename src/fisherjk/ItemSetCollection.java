package fisherjk;

import java.util.ArrayList;

public class ItemSetCollection extends ItemSet {
	
//Based on example code
	/*Part 1: Generate all candidate single-item sets
	 * {A} similar to 	{beer}
	 * {B}				{cheese}
	 * {C}				{bread}
	 * {D}				...
	 * {E}
	 * Essentially generate a list of unique items in a transaction set
	 */
	
	private ArrayList<ItemSet> itemSetCollection;

	
	//2 differenct Constructors
	public ItemSetCollection(ArrayList<ItemSet> itemSetCollection) {
		super();
		this.itemSetCollection = itemSetCollection;
	}
	
	public ItemSetCollection(){
		this.itemSetCollection = new ArrayList<ItemSet>();
	}
	

	public ArrayList<ItemSet> getItemSetCollection() {
		return itemSetCollection;
	}

	public void setItemSetCollection(ArrayList<ItemSet> itemSetCollection) {
		this.itemSetCollection = itemSetCollection;
	}

	
	/*Part 1: Generate all candidate single-item sets
	 * {A} similar to 	{beer}
	 * {B}				{cheese}
	 * {C}				{bread}
	 * {D}				...
	 * {E}
	 * Essentially generate a list of unique items in a transaction set
	 */
	
	public ItemSet GetUniqueItems() {

		this.itemSetCollection  = new ArrayList<ItemSet>();
		ItemSet list = new ItemSet();
		Item A = new Item("Apples");
		Item B= new Item("Beer");
		Item C = new Item("Cheese");
		list.getItemSet().add(A);//could be simplified much further but proving case in point
		list.getItemSet().add(B);
		list.getItemSet().add(A);
		list.getItemSet().add(C);
		this.itemSetCollection.add(list);
		ArrayList<ItemSet> uniqueItems = new ArrayList<ItemSet>();
		
		/*
		for(int i = 0; i < itemSetCollection.getItemSet().size(); i++){
			Item item = itemSetCollection.get(i).getItemSet().get(i);
			System.out.println("item: " + item);
			if(!uniqueItems.contains(item.getItemSet()
		}
		*/
		
		for(ItemSet item :this.itemSetCollection){
			System.out.println("item: " + item);
			if(!uniqueItems.contains(item.getItemSet())){//if doesn't contain item then add it
				uniqueItems.add(item);
			}
		}
		
		
		return null;
	}
	
	
	
}
