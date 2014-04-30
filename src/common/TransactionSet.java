package common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import service.Item;
import service.ItemSet;
import service.Transaction;
import service.Vendor;

//import java.util.Date;
/* Class for holding information about a set of transactions in a TransactionSet*/
public class TransactionSet implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Transaction> transactionSet;/*A transactionSet private instance variable is an ArrayList of individual Transactions*/
	private ArrayList<Vendor> vendorSet;
	private String start_date;
	private String end_date;
	private String timestamp;
	private ErrorLogs errorLogs;
	
	/*TransactionSet constructors*/
	public TransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
	}

	public TransactionSet() {
		this.transactionSet = new ArrayList<Transaction>();
	}
	
	public TransactionSet(TransactionSet aTransactionSet) {
		//TransactionSet copy = new TransactionSet();
		setVendorSet(aTransactionSet.getVendorSet());
		setStart_date(aTransactionSet.getStart_date());
		setEnd_date(aTransactionSet.getEnd_date());
		//setTimestamp();
		setTransactionSet(aTransactionSet.getTransactionSet());		
	}
	
	/*Respective getters and setters*/
	public ArrayList<Transaction> getTransactionSet() {
		return transactionSet;
	}

	public void setTransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
	}
	
	

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public ArrayList<Vendor> getVendorSet() {
		return vendorSet;
	}

	public void setVendorSet(ArrayList<Vendor> vendorSet) {
		this.vendorSet = vendorSet;
	}

	public String getTimestamp() {
		return this.timestamp;
	}
	
	public void setTimestamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		this.timestamp = strDate+" 12:00:00";
	}
	
	
	
	
	
	public ErrorLogs getErrorLogs() {
		return errorLogs;
	}

	public void setErrorLogs(ErrorLogs errorLogs) {
		this.errorLogs = errorLogs;
	}

	/*Returns an itemSet containing a set of unique items found in a transactionSet which consists of Transactions*/
	public ItemSet GetUniqueItems() {
		ItemSet uniqueItems = new ItemSet();/*uniquely constructed itemSet*/
		for (int j = 0; j < transactionSet.size(); j++) {/*loop through the first transactionSet object*/
			for (int i = 0; i < transactionSet.get(j).getTransaction().getItemSet().size(); i++) {/*doubly loop through each respective itemset in a transactionSet*/
				Item item = transactionSet.get(j).getTransaction().getItemSet()
						.get(i);
				
				if (!uniqueItems.getItemSet().contains(item)) {/*Only add items if not already within the unique list. Otherwise do nothing.*/
					uniqueItems.getItemSet().add(item);
				}

			}
		}
		return uniqueItems;
	}
	
	
	

	/*Override the TransactionSet's toString to call the Transaction's to String*/
	@Override
	public String toString() {

		int num = this.transactionSet.size();
		String transactionSetItems = "";
		for (int i = 0; i < num; i++) {
			String item = this.transactionSet.get(i).getTransaction().toString();
			transactionSetItems = transactionSetItems + "T" + i + ": " + item;
			if (i < num - 1) {
				transactionSetItems = transactionSetItems + "\n";
			}

		}
		return transactionSetItems;

	}
	

	/*Returns the frequency support count for an individual itemSet*/
	/*ItemSet's can be one to many items in size*/
	public double findSupport(ItemSet itemSupport) {
		Timer timer = new Timer();
		timer.startTimer();
		double supportCount = 0;/*keep track of the count for support of an itemSet*/
		int num = this.transactionSet.size();
		for (int i = 0; i < num; i++) {
				if (this.transactionSet.get(i).getTransaction().containsItemSet(itemSupport.getItemSet())) {
					supportCount++;

				}
		}
		timer.stopTimer();
		//System.out.println(("TransactionSet.findSupport Time in Milliseconds: " + timer.getTotal()));
		return supportCount;
	}
	
	
	/*Determines and returns all possible combinations of  k-item subsets based on a given itemSet. This allows filtering to take place as the next loop iteration starts up */
	public TransactionSet findKItemSubsets(ItemSet itemSet, int k) {
		TransactionSet allSubsets = new TransactionSet();/*New subset of transactions to return in a TransactionSet*/
		int subsetCount = (int) Math.pow(2, itemSet.getItemSet().size());/*index control for loop*/
		int itemSetSize = itemSet.getItemSet().size();/*size control for inner loop*/
		for (int i = 0; i < subsetCount; i++) {
			
			if (k == 0 || GetOnCount(i, itemSet.getItemSet().size()) == k){
			
			ItemSet subset = new ItemSet();
			
			for (int bitIndex = 0; bitIndex < itemSetSize; bitIndex++) {
				if (GetBit(i, bitIndex) == 1) {
					subset.getItemSet().add(itemSet.getItemSet().get(bitIndex));
				}
			}
			
				allSubsets.getTransactionSet().add(new Transaction(subset));//add the new transaction subset
			}
		}
		return (allSubsets);/*final combination of all possible subsets based on the size of k*/
	}
	
	
	/*Determines and returns all possible combinations of  k-item subsets based on a given itemSet. This allows filtering to take place as the next loop iteration starts up */
	/*
	public TransactionSet twoItemSubsets(ItemSet itemSet, double minSupportLevel, TransactionSet transSet) {
		//System.out.println("2 ItemSubsets starting");
		//System.out.println("Starting ItemSet to make 2 item subsets");
		TransactionSet allSubsets = new TransactionSet();/*New subset of transactions to return in a TransactionSet
		int size = itemSet.getItemSet().size();
		//System.out.println("Passed in ItemSet:");
		System.out.println(itemSet.toString());
		//System.out.println("size: " + size);
		for(int i = 0; i < size; i++){
			Item currItem = new Item(itemSet.getItemSet().get(i).getItem());
			//System.out.println("CURRENT ITEM: " + currItem.toString());
			for(int j =i; j < size-1; j++){
				ItemSet iSet = new ItemSet();
				Item newItem = new Item(itemSet.getItemSet().get(j+1).getItem());
				//System.out.println("NEW ITEM?: " + newItem.toString());
				if(!currItem.equals(newItem)){
					iSet.getItemSet().add(currItem);
					iSet.getItemSet().add(newItem);
				}
				
				//System.out.println("ItemSet to check:\n:" + iSet.toString());
				double findSupport = transSet.findSupport(iSet);
				
				
				
				iSet.setItemSetSupport(findSupport);
				//System.out.println("findSupport: " + (findSupport/transSet.getTransactionSet().size()) + "vs.  minSupport: " + minSupportLevel);
				if(iSet.getItemSet().size() >1){
				if (iSet.getItemSetSupport()/transSet.getTransactionSet().size() >= minSupportLevel) {
					
					Transaction ts = new Transaction(iSet);
					allSubsets.getTransactionSet().add(ts);
				}
				}
			}
		}
		
		*/
		public TransactionSet twoItemSubsets(TransactionSet candidSet, double minSupportLevel, TransactionSet transSet) {
			//System.out.println("2 ItemSubsets starting");
			//System.out.println("Starting ItemSet to make 2 item subsets");
			TransactionSet allSubsets = new TransactionSet();/*New subset of transactions to return in a TransactionSet*/
			
			int size = candidSet.getTransactionSet().size();
			//System.out.println("SIZE: " + size);
			for(int i = 0; i < size; i++){
				//System.out.println("i: " + i);
				Item currItem = new Item(candidSet.getTransactionSet().get(i).getTransaction().getItemSet().get(0).toString());
				for(int j = i+1; j < size; j++){
					//System.out.println("j: " + j);
					Item nextItem = new Item(candidSet.getTransactionSet().get(j).getTransaction().getItemSet().get(0).toString());
					ItemSet iSet = new ItemSet();
					iSet.getItemSet().add(currItem);
					iSet.getItemSet().add(nextItem);
					//System.out.println("ItemSet to check:\n:" + iSet.toString());
					double supportLevel = transSet.findSupport(iSet);
					//iSet.setItemSetSupport(findSupport);
					iSet.setItemSetSupport(supportLevel/transSet.getTransactionSet().size());
					//System.out.println("findSupport: " + (findSupport/transSet.getTransactionSet().size()) + "vs.  minSupport: " + minSupportLevel);
					//if(iSet.getItemSet().size() >1){
						if (iSet.getItemSetSupport() >= minSupportLevel) {
							//System.out.println("Passed: ");
							Transaction ts = new Transaction(iSet);
							allSubsets.getTransactionSet().add(ts);
						}
					//}
				}
			}
		return (allSubsets);/*final combination of all possible subsets based on the size of k*/
	}
	
	

	/*Necessary for functions to determine subsets based on the bits of an index*/
	public static int GetBit(int value, int position) {
		int bit = value & (int) Math.pow(2, position);
		return (bit > 0 ? 1 : 0);
	}
	
	/*helper method to findKItemSubsets which are based on an index of the item*/
	public static String DecimalToBinary(int value, int length){
        String binary = "";
        for (int position = 0; position < length; position++){
            binary = GetBit(value, position) + binary;
        }
        return binary;
    }

	/*helper method to findKItemSubsets which look at the items in the set*/
    public static int GetOnCount(int value, int length){
        String binary = DecimalToBinary(value, length);
        char[] binaryChar = binary.toCharArray();
        int count = 0;
       for(int i = 0; i < binary.length(); i++){
        	if(binaryChar[i] == '1'){
        		//return count;
        	//}
        	count++;
        }
       }
       return count;
    }


	

	
	
	

	
}
