package fisherjk;


import java.util.Date;

/*Class for holding singular ItemSet records*/
public class Transaction {

	private ItemSet transaction;//A transaction is essentially an itemSet
	private Date transactionDate;//A transaction's timestamp variable 
	private double transactionTotalPrice;//A transaction total price

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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getTransactionTotalPrice(Transaction transaction) {
		return transactionTotalPrice;

	}
	
	public void setTransactionTotalPrice(double transactionTotalPrice) {
		this.transactionTotalPrice = transactionTotalPrice;

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
