package fisherjk;

import java.util.ArrayList;

public class TransactionSet {

	private ArrayList<Transaction> transactionSet;

	//List of Transactions
	public TransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
	}

	public ArrayList<Transaction> getTransactionSet() {
		return transactionSet;
	}

	public void setTransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
	}

}
