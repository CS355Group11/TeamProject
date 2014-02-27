package fisherjk;

public class Item{

	private String itemName;
	private String itemDesc;
	private double itemPrice;
	
	
	public Item(String itemName){
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemPrice = itemPrice;
		
	}
	
	public String getItem() {
		return itemName;
	}

	public void setItem(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return itemName;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Item item = (Item) obj;
		if(this.itemName.equalsIgnoreCase(item.itemName)){
			return true;
		}else{
			return false;
		}
	}
	
}
