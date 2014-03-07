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
import java.util.Scanner;
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
            //use a scanner with a delimeter and a hasnext
            
            
            
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
                System.out.println("line is now: " + line);
                
                Pattern p2 = Pattern.compile(findBrackets);
                Matcher m2 = p2.matcher(line);
                line = m2.replaceAll("");//strip brackets
                System.out.println("line is now: " + line);
                
                Pattern p3 = Pattern.compile(findItem);
                Matcher m3 = p3.matcher(line);//find individual items
                int itemCount = 0;
                ItemSet itemSet = new ItemSet();//create a new itemSet
                while(m3.find()){//loop until we don't have any more matches in the groupings
                	
                	Item item = new Item(m3.group(0));
                	System.out.println("Transaction # " + i + " Item # " + itemCount + ": " + item.getItem());
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
	
	
	/*READING FILE WITH NEW METHOD*/
	public static TransactionSet readFile2(String fileInputName){
		fileInputName = "src/"+fileInputName;
		String line = "";
		TransactionSet transactionSet = new TransactionSet();
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileInputName);

            // Always wrap FileReader in BufferedReader.
            Scanner scanner = new Scanner(fileReader);
            int transactionCount = 0;
            //use a scanner with a delimeter and a hasnext
            String patternInBrackets = "(?<=\\{)(.*)(?=\\})";//unused at the moment
            String findVendor = "(?<=\\})?(.*)";//regex to find vendor name
            String findDate = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";//regex to find the date
            String findBrackets = "\\{|\\}";//regex to find brackets for eventual removal
            String findInBrackets = "\\{(.*)\\}";//regex to find content within brackets
            String findCommas = "\\,\\s?";//regex to find commas and any white space immediately after it
            String findItem = "(\\w+)";
            String findDescItem = "(.*)[^\\n]";//regex to find items
            ArrayList<String> dates = new ArrayList<String>();
            
            
            while(scanner.hasNextLine()) {
            	
            	line = scanner.nextLine();
            	
            	//check to find start of a new transaction set
            	//determine if found a new vendor and start/end date
            	//Pattern vendorPattern = Pattern.compile(findVendor);
            	//Matcher vendorMatcher = vendorPattern.matcher(line);
            	//String vendor = vendorMatcher.group(0);
            	//System.out.println("vendor: " + vendor);
            	Pattern datePattern = Pattern.compile(findDate);
            	Matcher dateMatcher = datePattern.matcher(line);
            	
            	Pattern contentPattern = Pattern.compile(findInBrackets);
            	Matcher contentMatcher = contentPattern.matcher(line);
            	if(contentMatcher.find()){
            		
            		System.out.println("line contents"+ line);
            		Pattern bracketPattern = Pattern.compile(findBrackets);
            		Matcher bracketMatcher = bracketPattern.matcher(line);
            		line = bracketMatcher.replaceAll("");//strip brackets
            		System.out.println("line is now: " + line);
            		
            		
            		Pattern commaPattern = Pattern.compile(findCommas);
                    Matcher commaMatcher = commaPattern.matcher(line);
                    line = commaMatcher.replaceAll("\n");//strip commas and replace with new lines
                    System.out.println("line is now: " + line);
                    
            		
                    Pattern itemPattern = Pattern.compile(findDescItem);
                    Matcher itemMatcher = itemPattern.matcher(line);//find individual items based on new line basis
                    int itemCount = 0;
                    ItemSet itemSet = new ItemSet();//create a new itemSet
                    while(itemMatcher.find()){//loop until we don't have any more matches in the groupings
                    	Item item = new Item(itemMatcher.group(0));
                    	System.out.println("Transaction # " + transactionCount + " Item # " + itemCount + ": " + item.getItem());
                    	itemSet.getItemSet().add(item);//add item to new itemset
                    	itemCount++;
                    }
                    Transaction transaction = new Transaction(itemSet);//create a new transaction
                    transactionSet.getTransactionSet().add(transaction);//append transaction to overall transactionSet
                    
                    
                    transactionCount++;
            		
            		
            	}else if(dateMatcher.find()){
            		System.out.println("date is: " + line);
            		
            	}
            	
            	
                //i++;
            }

            System.out.println(transactionSet.getTransactionSet().toString());
            // Always close files.
            fileReader.close();
            scanner.close();			
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





