package fisherjk;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		// input minSupportLevel
		// input minConfidenceLevel
		// input name of file holding transaction set
		/*
		 * System.out.println("Running Main"); ItemSet list = new ItemSet();
		 * Item A = new Item("Apples"); Item B= new Item("Beer"); Item C = new
		 * Item("Cheese"); list.getItemSet().add(A);//could be simplified much
		 * further but proving case in point list.getItemSet().add(B);
		 * list.getItemSet().add(A); list.getItemSet().add(C); ItemSetCollection
		 * db = new ItemSetCollection(); db.getItemSetCollection().add(list);
		 */
		// Products
		Item A = new Item("A");
		Item B = new Item("B");
		Item C = new Item("C");
		Item D = new Item("D");
		Item E = new Item("E");
		System.out.println("Part 0: Insert items into each transaction:");
		// Creating Transaction 1
		ArrayList<Item> list1 = new ArrayList<Item>();
		list1.add(A);
		list1.add(B);
		list1.add(E);
		ItemSet set1 = new ItemSet(list1);
		Transaction trans1 = new Transaction(set1);
		System.out.println("T1: " + trans1.printTransactionItems());
		// End of Creating Transaction 1

		// Creating Transaction 2
		ArrayList<Item> list2 = new ArrayList<Item>();
		list2.add(B);
		list2.add(D);
		ItemSet set2 = new ItemSet(list2);
		Transaction trans2 = new Transaction(set2);
		System.out.println("T2: " + trans2.printTransactionItems());
		// End of Transaction 2

		// Creating Transaction 3
		ArrayList<Item> list3 = new ArrayList<Item>();
		list3.add(B);
		list3.add(C);
		ItemSet set3 = new ItemSet(list3);
		Transaction trans3 = new Transaction(set3);
		System.out.println("T3: " + trans3.printTransactionItems());
		// End of Transaction 3

		// Creating Transaction 4
		ArrayList<Item> list4 = new ArrayList<Item>();
		list4.add(A);
		list4.add(B);
		list4.add(D);
		ItemSet set4 = new ItemSet(list4);
		Transaction trans4 = new Transaction(set4);
		System.out.println("T4: " + trans4.printTransactionItems());
		// End of Transaction 4

		// Creating Transaction 5
		ArrayList<Item> list5 = new ArrayList<Item>();
		list5.add(A);
		list5.add(C);
		ItemSet set5 = new ItemSet(list5);
		Transaction trans5 = new Transaction(set5);
		System.out.println("T5: " + trans5.printTransactionItems());
		// End of Transaction 5

		// Creating Transaction 6
		ArrayList<Item> list6 = new ArrayList<Item>();
		list6.add(B);
		list6.add(C);
		ItemSet set6 = new ItemSet(list6);
		Transaction trans6 = new Transaction(set6);
		System.out.println("T6: " + trans6.printTransactionItems());
		// End of Transaction 6

		// Creating Transaction 7
		ArrayList<Item> list7 = new ArrayList<Item>();
		list7.add(A);
		list7.add(C);
		ItemSet set7 = new ItemSet(list7);
		Transaction trans7 = new Transaction(set7);
		System.out.println("T7: " + trans7.printTransactionItems());
		// End of Transaction 7

		// Creating Transaction 8
		ArrayList<Item> list8 = new ArrayList<Item>();
		list8.add(A);
		list8.add(B);
		list8.add(C);
		list8.add(E);
		ItemSet set8 = new ItemSet(list8);
		Transaction trans8 = new Transaction(set8);
		System.out.println("T8: " + trans8.printTransactionItems());
		// End of Transaction 8

		// Creating Transaction 8
		ArrayList<Item> list9 = new ArrayList<Item>();
		list9.add(A);
		list9.add(B);
		list9.add(C);
		ItemSet set9 = new ItemSet(list9);
		Transaction trans9 = new Transaction(set9);
		System.out.println("T9: " + trans9.printTransactionItems());
		// End of Transaction 8

		ArrayList<Transaction> transSet = new ArrayList<Transaction>();
		TransactionSet tSet = new TransactionSet();
		transSet.add(trans1);
		transSet.add(trans2);
		transSet.add(trans3);
		transSet.add(trans4);
		transSet.add(trans5);
		transSet.add(trans6);
		transSet.add(trans7);
		transSet.add(trans8);
		transSet.add(trans9);
		tSet.setTransactionSet(transSet);
		
		//TransactionSet transSet = new TransactionSet();
		/*
		transSet.getTransactionSet().add(trans1);
		transSet.getTransactionSet().add(trans2);
		transSet.getTransactionSet().add(trans3);
		transSet.getTransactionSet().add(trans4);
		transSet.getTransactionSet().add(trans5);
		transSet.getTransactionSet().add(trans6);
		transSet.getTransactionSet().add(trans7);
		transSet.getTransactionSet().add(trans8);
		transSet.getTransactionSet().add(trans9);
		*/
		APrioriAlgorithm.DoApriori(tSet, 2.0);

		// TODO: set up to read input from a file

	}

}
