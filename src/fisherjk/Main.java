package fisherjk;

import java.util.ArrayList;

public class Main {
 
	
	public static void main(String[] args) {
		
		//input minSupportLevel
		//input minConfidenceLevel
		//input name of file holding transaction set
		
		System.out.println("Running Main");
		ItemSet list = new ItemSet();
		Item A = new Item("Apples");
		Item B= new Item("Beer");
		Item C = new Item("Cheese");
		list.getItemSet().add(A);//could be simplified much further but proving case in point
		list.getItemSet().add(B);
		list.getItemSet().add(A);
		list.getItemSet().add(C);
		ItemSetCollection  db = new ItemSetCollection();
		db.getItemSetCollection().add(list);
		APrioriAlgorithm.DoApriori(db, 0);
		
		//TODO: set up to read input from a file
		
		
	}
	
}
