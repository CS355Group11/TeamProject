package fisherjk;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {

	private ArrayList<ItemSet> transaction;
	private Date transactionDate;
	private double transactionTotalPrice;

	//List of ItemSets
	public Transaction(ArrayList<ItemSet> transaction, Date transactionDate) {
		this.transaction = transaction;
		this.transactionDate = transactionDate;
	}
	
	public Transaction(){
		this.transaction =  new ArrayList<ItemSet>();
	}

	public ArrayList<ItemSet> getTransaction() {
		return transaction;
	}

	public void setTransaction(ArrayList<ItemSet> transaction) {
		this.transaction = transaction;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public double getTransactionTotalPrice(Transaction transaction){
		return transactionTotalPrice;
		
	}


	public ItemSet getUniqueItems() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
