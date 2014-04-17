package edu.uwec.cs355.group11.all;


import java.text.SimpleDateFormat;
import java.util.Date;

/*Class for holding singular ItemSet records*/
public class Transaction {

	private ItemSet transaction;//A transaction is essentially an itemSet
	private String transactionDate;//A transaction's timestamp variable 
	private double transactionTotalPrice;//A transaction total price
	private int transactionSet_ID = 0;
	private Vendor vendor;
	private String timestamp;

	/*Constructors for Transaction Class*/
	public Transaction(ItemSet transaction) {
		this.transaction = transaction;
		//this.transactionDate = transactionDate;//unused at the moment
	}

	/*Creating a new transaction creates a new ItemSet. An ItemSet is a list of Items*/
	public Transaction() {
		this.transaction = new ItemSet();
	}

	/*Respective getters and setters*/
	public ItemSet getTransaction() {
		return transaction;
	}

	public void setTransaction(ItemSet transaction) {
		this.transaction = transaction;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getTransactionTotalPrice(Transaction transaction) {
		return transactionTotalPrice;

	}
	
	public void setTransactionTotalPrice(double transactionTotalPrice) {
		this.transactionTotalPrice = transactionTotalPrice;

	}
	

	public int getTransactionSet_ID() {
		return transactionSet_ID;
	}

	public void setTransactionSet_ID(int transactionSet_ID) {
		this.transactionSet_ID = transactionSet_ID;
	}
	
	

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	public String getTimestamp() {
		return this.timestamp;
	}
	
	public void setTimestamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		this.timestamp = strDate+"12:00:00";
	}
	

	/*Override the Transaction's toString to call the ItemSet's to String which calls an Item toString*/
	@Override
	public String toString() {
		String transactionItems = "";
		int num = this.transaction.getItemSet().size();
		for (int i = 0; i < num; i++) {
			String item = this.transaction.getItemSet().get(i).toString();
			transactionItems = transactionItems + item;
			if (i < num - 1) {
				transactionItems = transactionItems + ",";
			}

		}
		return "{" + transactionItems + "}";//don't forget the opening and closing braces

	}

	public ItemSet remove(Transaction subset) {
			this.transaction.getItemSet().remove(subset);
			return this.transaction;
			
	}

	
	
}
