package testing;

import java.io.Serializable;

/* Class for holding singular Item information*/
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	private String itemName;//unique item name
	private String itemDesc;//unique item description
	private double itemPrice;//unique item price

	public Item(String itemName) {
		this.itemName = itemName;
		//this.itemDesc = itemDesc;	//unused at the moment
		//this.itemPrice = itemPrice; //unused at the moment

	}
	
/*Respective getter's and setters for each item's private instance variables*/
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

	/*Override the Item's class toString since it will be called by later classes*/
	@Override
	public String toString() {
		return itemName;
	}

	/*Override the Item's equals method to allow the ItemSet class contain's method to be run*/
	@Override
	public boolean equals(Object obj) {
		//System.out.println("calling overridden equals method");
		Item item = (Item) obj;
		if (this.itemName.equals(item.itemName)) {
			return true;
		} else {
			return false;
		}
	}

}
