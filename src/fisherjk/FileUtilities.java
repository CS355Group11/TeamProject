package fisherjk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*IMPLEMENTED TO WITHSTANd TEST CASES AS BEST AS POSSIBLE*/
public class FileUtilities {

	
		// TODO Auto-generated method stub

		/*
		 * input parameters 1. a minimum support level (as a real number,
		 * representing the number of transactions containing an item set
		 * divided by the total number of transactions), 2. a minimum confidence
		 * level (a real number, to be used in rule generation/filtering), 3.
		 * the name of a file holding a transaction set in the specified format
		 * previously discussed
		 */
		
	public static TransactionSet readFile(String fileInputName){
		fileInputName = "src/"+fileInputName;
		String line = "";
		TransactionSet transactionSet = new TransactionSet();
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileInputName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 0;
            while((line = bufferedReader.readLine()) != null) {
               // System.out.println("line # "+ i + " " + line);
                //read input to ultimately return a TransactionSet
                String patternInsideBrackets = "(?<=\\{)(.*)(?=\\})";
                String findBrackets = "\\{|\\}";
                String findCommas = "\\,";
                String findItem = "(\\w)";
               
                Pattern p = Pattern.compile(findCommas);
                Matcher m = p.matcher(line);
                line = m.replaceAll(" ");//strip commas
                //System.out.println("line is now: " + line);
                
                Pattern p2 = Pattern.compile(findBrackets);
                Matcher m2 = p2.matcher(line);
                line = m2.replaceAll("");//strip brackets
                //System.out.println("line is now: " + line);
                
                Pattern p3 = Pattern.compile(findItem);
                Matcher m3 = p3.matcher(line);//find individual items
                int itemCount = 0;
                ItemSet itemSet = new ItemSet();//create a new itemSet
                while(m3.find()){//loop until we don't have any more matches in the groupings
                	
                	Item item = new Item(m3.group(0));
                	//System.out.println("Transaction # " + i + " Item # " + itemCount + ": " + item.getItem());
                	itemSet.getItemSet().add(item);//add item to new itemset
                	itemCount++;
                }
                Transaction transaction = new Transaction(itemSet);//create a new transaction
                transactionSet.getTransactionSet().add(transaction);//append transaction to overall transactionSet
                
                
                i++;
            }
            
            System.out.println(transactionSet.getTransactionSet().toString());
            // Always close files.
            bufferedReader.close();			
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileInputName + "'");				
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileInputName + "'");					
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		return transactionSet;
		
    }
	
	/*
	 * Output items
	 * 
	 * 1. a collection of rule objects within the program, where each rule
	 * has an antecedent and consequent that contain one or more items
	 * 2. a
	 * text file containing the rules in the format: If ProductA and
	 * ProductB THEN ProductC and ProductD (confidence: 0.78), where again
	 * the antecedent and consequent may each contain one or more product
	 * names.
	 */
	
	public static void writeFile(RuleSet ruleSets, String fileOutputName){
		// The name of the file to open.
        fileOutputName = "src/" +fileOutputName;

        try {
        	PrintWriter writer = new PrintWriter(fileOutputName);
        	for(int i = 0; i < ruleSets.getRuleSet().size(); i++){
        		writer.println(ruleSets.getRuleSet().get(i));
        	}
        	//writer.println("The first line");
        	//writer.println("The second line");
        	writer.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileOutputName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
		
	}





