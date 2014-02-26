package fisherjk;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {

	private ItemSet transaction;
	private Date transactionDate;
	private double transactionTotalPrice;

	//List of ItemSets
	public Transaction(ItemSet transaction) {
		this.transaction = transaction;
		this.transactionDate = transactionDate;
	}
	
	public Transaction(){
		this.transaction =  new ItemSet();
	}

	public ItemSet getTransaction() {
		return transaction;
	}

	public void setTransaction(ItemSet transaction) {
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
	
	
	

	public String getTransactionItems() {//Essentially a specializied toString
		// TODO Auto-generated method stub
		int num = this.transaction.getItemSet().size();
		String transactionItems  = "";
		//for(int j = 0; j < this.transaction.size(); j++){
			for(int i = 0; i < num; i++){
				String item = this.transaction.getItemSet().get(i).getItem();
				transactionItems = transactionItems + item;
				if(i < num-1){
					transactionItems = transactionItems +",";
				}
		//}
		
	}
		return "{"+transactionItems+"}";
	
	
	}
}
