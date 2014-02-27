package fisherjk;

import java.util.ArrayList;

public class APrioriAlgorithm {

	public static TransactionSet DoApriori(TransactionSet transSet,
			double supportThreshold) {

		ItemSet I = transSet.GetUniqueItems();// eventual call to database
		TransactionSet L = new TransactionSet(); // resultant large
													// itemsets
		TransactionSet Li = new TransactionSet(); // large itemset in each
													// iteration
		TransactionSet Ci = new TransactionSet(); // candidate itemset in
													// each iteration

		// transSet.getUniqueItemCounts(I);

		// first iteration (1-item itemsets)
		for (int i = 0; i < I.getItemSet().size(); i++) {
			Item candidateItem = I.getItemSet().get(i);
			ItemSet candidateItemSet = new ItemSet();
			candidateItemSet.getItemSet().add(candidateItem);
			// System.out.println(candidateItemSet.getItemSet().get(i));
			Transaction candidateTrans = new Transaction(candidateItemSet);
			candidateTrans.toString();

			Ci.getTransactionSet().add(candidateTrans);
		}

		System.out.println(Ci.toString());
		// next iterations
		int k = 2;

		System.out
				.println("Part 2: Scan transaction set for count of each candidate single-item set");
		for (Transaction transaction : Ci.getTransactionSet()) {
			int findSupport = transSet
					.findSupport(transaction.getTransaction());
			transaction.getTransaction().setItemSetSupport(findSupport);
			System.out.println(transaction.toString() + "-" + findSupport);
		}

		// }

		/*
		 * Part 1: Generate all candidate single-item sets {A} similar to {beer}
		 * {B} {cheese} {C} {bread} {D} ... {E} Essentially generate a list of
		 * unique items in a transaction set
		 */

		/*
		 * Part 2: Scan transaction set for count of each candidate single-item
		 * set {A} – 6 {B} – 7 {C} – 6 {D} – 2 {E} – 2 Read As: A was present 6
		 * times in the transaction set
		 */

		/*
		 * Part 3: Filter candidate one-item sets with min. support to get
		 * frequent one-item sets {A} – 6 {B} – 7 {C} – 6 {D} – 2 {E} – 2 In
		 * this case no items are filtered since support level >=2
		 */

		/*
		 * Part 4: Generate all candidate two-item sets from frequent one-item
		 * sets {A, B} {A, C} {A, D} {A, E} {B, C} {B, D} {B, E} {C, D} {C, E}
		 * {D, E} All combinations
		 */

		/*
		 * Part 5: Remove any candidate two-item sets for which any subset
		 * single-item set is not itself a frequent set {A, B} {A, C} {A, D} {A,
		 * E} {B, C} {B, D} {B, E} {C, D} {C, E} {D, E} Here, no two-item
		 * candidate sets are removed, as all five single-item sets are frequent
		 */

		/*
		 * Part 6: Scan database to get count of all candidate two-item sets {A,
		 * B} - 4 {A, C} - 4 {A, D} - 1 {A, E} - 2 {B, C} - 4 {B, D} - 2 {B, E}
		 * - 2 {C, D} - 0 {C, E} - 1 {D, E} - 0 read: A and B are present
		 * together in 4 transactions
		 */

		/*
		 * Part 7: Can now filter the candidate two-item sets using the support
		 * level to get frequent two-item sets {A, B} - 4 {A, B} - 4 {A, C} - 4
		 * {A, C} - 4 {A, D} - 1 turns into -> {A, E} - 2 {A, E} - 2 {B, C} - 4
		 * {B, C} - 4 {B, D} - 2 {B, D} - 2 {B, E} - 2 {B, E} - 2 {C, D} - 0 {C,
		 * E} - 1 {D, E} - 0
		 */

		/*
		 * Part 8: Keep going for each K-item set... Combine where first K-2
		 * elements are common to avoid duplicates Remove any candidates for
		 * which any possible subset two-item set is not frequent Example – if
		 * candidate is {A, B, C}, then {A, B}, {B, C}, {A, C} must all be
		 * frequent Note: will go from 6 to 2 Scan database (transaction set)
		 * for count of each candidate three-item set
		 * 
		 * Compare candidate three-item sets with min. support count, and remove
		 * those not meeting min.Algorithm stops when you reach a level where
		 * there are no frequent-item sets
		 */

		/*
		 * NOTES: Overall result is the union of all frequent item sets found at
		 * each level k where k >= 2 Remember that for every candidate item set
		 * at level k, each level k-1 sub-combination must also be a frequent
		 * item set
		 */
		return null;
	}

}
