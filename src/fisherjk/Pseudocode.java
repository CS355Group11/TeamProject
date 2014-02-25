package fisherjk;

public class Pseudocode {

}

/*Data Structures
public class Itemset : List<string>
02	{
03	    public double Support { get; set; }
04	}
05	 
06	public class ItemsetCollection : List<Itemset>
07	{
08	}
09	 
10	public class AssociationRule
11	{
12	    public Itemset X { get; set; }
13	    public Itemset Y { get; set; }
14	    public double Support { get; set; }
15	    public double Confidence { get; set; }
16	}

//Finding Large Itemsets
 * public static ItemsetCollection DoApriori(ItemsetCollection db, double supportThreshold)
02	{
03	    Itemset I = db.GetUniqueItems();
04	    ItemsetCollection L = new ItemsetCollection(); //resultant large itemsets
05	    ItemsetCollection Li = new ItemsetCollection(); //large itemset in each iteration
06	    ItemsetCollection Ci = new ItemsetCollection(); //candidate itemset in each iteration
07	 
08	    //first iteration (1-item itemsets)
09	    foreach (string item in I)
10	    {
11	        Ci.Add(new Itemset() { item });
12	    }
13	 
14	    //next iterations
15	    int k = 2;
16	    while (Ci.Count != 0)
17	    {
18	        //set Li from Ci (pruning)
19	        Li.Clear();
20	        foreach (Itemset itemset in Ci)
21	        {
22	            itemset.Support = db.FindSupport(itemset);
23	            if (itemset.Support >= supportThreshold)
24	            {
25	                Li.Add(itemset);
26	                L.Add(itemset);
27	            }
28	        }
29	 
30	        //set Ci for next iteration (find supersets of Li)
31	        Ci.Clear();
32	        Ci.AddRange(Bit.FindSubsets(Li.GetUniqueItems(), k)); //get k-item subsets
33	        k += 1;
34	    }
35	 
36	    return (L);
37	}
 * 
 */



//Finding Association Rules
/*
 public static List<AssociationRule> Mine(ItemsetCollection db, ItemsetCollection L, double confidenceThreshold)
02	{
03	    List<AssociationRule> allRules = new List<AssociationRule>();
04	 
05	    foreach (Itemset itemset in L)
06	    {
07	        ItemsetCollection subsets = Bit.FindSubsets(itemset, 0); //get all subsets
08	        foreach (Itemset subset in subsets)
09	        {
10	            double confidence = (db.FindSupport(itemset) / db.FindSupport(subset)) * 100.0;
11	            if (confidence >= confidenceThreshold)
12	            {
13	                AssociationRule rule = new AssociationRule();
14	                rule.X.AddRange(subset);
15	                rule.Y.AddRange(itemset.Remove(subset));
16	                rule.Support = db.FindSupport(itemset);
17	                rule.Confidence = confidence;
18	                if (rule.X.Count > 0 && rule.Y.Count > 0)
19	                {
20	                    allRules.Add(rule);
21	                }
22	            }
23	        }
24	    }
25	 
26	    return (allRules);
27	}
*/