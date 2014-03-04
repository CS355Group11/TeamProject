package fisherjk;

import java.util.ArrayList;

/* Class for holding information about a set of transactions in a TransactionSet*/
public class TransactionSet {

	
	private ArrayList<Transaction> transactionSet;//A transactionSet private instance variable is an ArrayList of individual Transactions

	/*TransactionSet constructors*/
	public TransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
	}

	public TransactionSet() {
		this.transactionSet = new ArrayList<Transaction>();
	}

	/*Respective getters and setters*/
	public ArrayList<Transaction> getTransactionSet() {
		return transactionSet;
	}

	public void setTransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
	}

	
	/*Returns an itemSet containing a set of unique items found in a transactionSet which consists of Transactions*/
	public ItemSet GetUniqueItems() {
		ItemSet uniqueItems = new ItemSet();//uniquely constructed itemSet
		for (int j = 0; j < transactionSet.size(); j++) {//loop through the first transactionSet object
			for (int i = 0; i < transactionSet.get(j).getTransaction().getItemSet().size(); i++) {//doubly loop through each respective itemset in a transactionSet
				Item item = transactionSet.get(j).getTransaction().getItemSet()
						.get(i);
				//System.out.println("Get unique Item: " + item);
				if (!uniqueItems.getItemSet().contains(item)) {//Only add items if not already within the unique list. Otherwise do nothing.
					//System.out.println("{" + item.getItem() + "}");
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
	public double findSupport(ItemSet itemSupport) {// get the individual
		double supportCount = 0;//keep track of the counte
		int num = this.transactionSet.size();
		for (int i = 0; i < num; i++) {
				if (this.transactionSet.get(i).getTransaction().containsItemSet(itemSupport.getItemSet())) {
					supportCount++;

				}
		}
		return supportCount;
	}
	
	
	/*Determines and returns all possible combinations of subsets based on a given itemSet. This allows filtering to take place as the next loop iteration starts up */
	public TransactionSet findKItemSubsets(ItemSet itemSet, int k) {
		TransactionSet allSubsets = new TransactionSet();//New subset of transactions to return in a TransactionSet
		int subsetCount = (int) Math.pow(2, itemSet.getItemSet().size());//index control for loop
		int itemSetSize = itemSet.getItemSet().size();//size control for inner loop
		for (int i = 0; i < subsetCount; i++) {
			
			if (k == 0 || GetOnCount(i, itemSet.getItemSet().size()) == k){
                String binary = DecimalToBinary(i, itemSet.getItemSet().size());
			
			ItemSet subset = new ItemSet();
			
			for (int bitIndex = 0; bitIndex < itemSetSize; bitIndex++) {
				if (GetBit(i, bitIndex) == 1) {
					subset.getItemSet().add(itemSet.getItemSet().get(bitIndex));
				}
			}
			//if(binary)
			
			
			//if (subset.getItemSet().size() == k - 1) {//accounting for missing Bit class. Seems to correct off-by indexing
				allSubsets.getTransactionSet().add(new Transaction(subset));//add the new transaction subset
			//}
			}
		}

		return (allSubsets);//final combination of all possible subsets based on the size of k
	}
	
	
	
	

	
	
	
	/*Necessary for determining subsets based on the bits of an index*/
	public static int GetBit(int value, int position) {
		int bit = value & (int) Math.pow(2, position);
		return (bit > 0 ? 1 : 0);
	}
	
	
	public static String DecimalToBinary(int value, int length){
        String binary = "";
        for (int position = 0; position < length; position++){
            binary = GetBit(value, position) + binary;
        }
        return binary;
    }

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
