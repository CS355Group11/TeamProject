package fisherjk;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*IMPLEMENTED TO WITHSTANd TEST CASES AS BEST AS POSSIBLE*/
public class FileUtilities {

	/*
	 * input parameters 1. a minimum support level (as a real number,
	 * representing the number of transactions containing an item set divided by
	 * the total number of transactions), 2. a minimum confidence level (a real
	 * number, to be used in rule generation/filtering), 3. the name of a file
	 * holding a transaction set in the specified format previously discussed
	 */


	/* READING FILE CONTENTS*/
	public static TransactionSet readFile(String fileInputName) {
		ArrayList<Vendor> av = new ArrayList<Vendor>();
		ErrorLogs errorLogs = new ErrorLogs();
		fileInputName = "src/" + fileInputName;
		String line = "";
		TransactionSet transactionSet = new TransactionSet();
		String formatError = "Format Error: ";
		int errorCount = 0;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileInputName);

			
			Scanner scanner = new Scanner(fileReader);
			//int transactionCount = 0;
			//String patternInBrackets = "(?<=\\{)(.*)(?=\\})";// unused at the moment
			String findVendor = "(?<=\\})?(.*)";// regex to find vendor name
			String findDate = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";// regex to find the date
			String findBrackets = "\\{|\\}";// regex to find brackets for eventual removal
			String findInBrackets = "\\{(.*)\\}";// regex to find content within  brackets
			String findCommas = "\\,\\s?";// regex to find commas and any white space immediately after it
			//String findItem = "(\\w+)";
			String findDescItem = "(.*)[^\\n]";// regex to find items
			String findLeftBrace = "\\{";
			String findRightBrace = "\\}";
			
			
			while (scanner.hasNextLine() &&  errorLogs.getErrorCount()==0) {
				System.out.println("Current ErrorCount = " + errorCount);
				line = scanner.nextLine();
				System.out.println("line->" + line);
				// check to find start of a new transaction set
				// determine if found a new vendor and start/end date
				Pattern vendorPattern = Pattern.compile(findVendor);
				Matcher vendorMatcher = vendorPattern.matcher(line);
				// String vendor = vendorMatcher.group(0);
				// System.out.println("vendor: " + vendor);
				Pattern datePattern = Pattern.compile(findDate);
				Matcher dateMatcher = datePattern.matcher(line);
				
				if (dateMatcher.find()) {/*determine if we found a date string*/
					System.out.println("date is: " + line);
					System.out.println("GETTER: " + transactionSet.getStart_date());
					if(transactionSet.getStart_date() == null){
						transactionSet.setStart_date(line);
						System.out.println("start_date is: " + line);
					}else{
						transactionSet.setEnd_date(line);
						System.out.println("end_date is: " + line);
					}

				} else {

					// Find for missing closing and leading brackets
					Pattern leftBracePattern = Pattern.compile(findLeftBrace);
					Pattern rightBracePattern = Pattern.compile(findRightBrace);

					Matcher leftBraceMatcher = leftBracePattern.matcher(line);
					Matcher rightBraceMatcher = rightBracePattern.matcher(line);

					if (leftBraceMatcher.find() && rightBraceMatcher.find()) {/*Determine if a left or right brace is missing*/
						System.out.println("Found Left and Right Brace");
						Pattern contentPattern = Pattern.compile(findInBrackets);
						Matcher contentMatcher = contentPattern.matcher(line);
						
						if(rightBraceMatcher.find()){
							System.out.println("Found Extra Right Brace");
							errorLogs.getErrorMsgs().add(formatError +"Found Extra Right Brace");
							//errorCount++;
						}
						
						
						if(leftBraceMatcher.find()){
							System.out.println("Found Extra Left Brace");
							errorLogs.getErrorMsgs().add(formatError +"Found Extra Left Brace");
							//errorCount++;
						}
						
						
						if (contentMatcher.find()) {
							//May need to fix spacing check
							if(contentMatcher.group(1).contentEquals(" ") || contentMatcher.group(1).contentEquals("")){//check to see if empty transaction {} or { }
								System.out.println(contentMatcher.group(1).contentEquals(" "));//space
								System.out.println(contentMatcher.group(1).contentEquals(""));//empty
								System.out.println("Content: " + contentMatcher.group(1));
								System.out.println("Found an empty transaction");
								errorLogs.getErrorMsgs().add(formatError + "Found an empty transaction");
								//errorCount++;
							}else if(errorCount == 0){//while no errors found
							// System.out.println("line contents"+ line);
								Pattern bracketPattern = Pattern.compile(findBrackets);
								Matcher bracketMatcher = bracketPattern.matcher(line);
								line = bracketMatcher.replaceAll("");// strip brackets
								// System.out.println("line is now: " + line);
	
								Pattern commaPattern = Pattern.compile(findCommas);
								Matcher commaMatcher = commaPattern.matcher(line);
								line = commaMatcher.replaceAll("\n");// strip commas and replace with new lines
								// System.out.println("line is now: " + line);
	
								Pattern itemPattern = Pattern.compile(findDescItem);
								Matcher itemMatcher = itemPattern.matcher(line);// find individual items on a new line basis
								//int itemCount = 0;
								ItemSet itemSet = new ItemSet();// create a new ItemSet
								int uniqueItems = transactionSet.GetUniqueItems().getItemSet().size();
								int totalItems = 0;
								while (itemMatcher.find()) {// loop until we don't have any more matches in the groupings
									
									Item item = new Item(itemMatcher.group(0));
									// System.out.println("Transaction # " +
									// transactionCount + " Item # " + itemCount +
									// ": " + item.getItem());
									itemSet.getItemSet().add(item);// add item to
																	// new itemset
									totalItems++;//uniqueItems = transactionSet.GetUniqueItems().getItemSet().size();
								}
								System.out.println("Creating new Transaction Set");
								Transaction transaction = new Transaction(itemSet);// create a new transaction
								transactionSet.getTransactionSet().add(transaction);// append to overall transaction set
								uniqueItems = transactionSet.GetUniqueItems().getItemSet().size();
								//int totalItems = transactionSet.getTransactionSet().size();
								System.out.println("Unique Items Count: " + uniqueItems);
								System.out.println("Total Items Count: " + uniqueItems);
							//transactionCount++;// increment the transcation
												// count
								if(uniqueItems > 25){
									errorLogs.getErrorMsgs().add(formatError +"Too many unique items in transaction set");
									
								}
								
								if(totalItems > 1000){
									errorLogs.getErrorMsgs().add(formatError +"Too many items in transaction set");//start here
									
								}
								
							}
						}
					
					} else {
						
						if(vendorMatcher.find()) {//figure out how to set vendors to transactions
							System.out.println("Vendor is: " + line);
							Vendor vendor = new Vendor(line);
							av.add(vendor);
							transactionSet.setVendorSet(av);
							//transaction.setVendor(vendor);
							System.out.println("MATCHER Vendor in vendor set: " + vendor.getVendor_name());
						} else {
						
							if (!leftBraceMatcher.find()) {
								System.out.println("Missing Left brace");
								errorLogs.getErrorMsgs().add(formatError +"Missing Left brace");
								//errorCount++;
							}
							
							if (!rightBraceMatcher.find()) {
								System.out.println("Missing right brace");
								errorLogs.getErrorMsgs().add(formatError +"Missing Right brace");
								//errorCount++;
							}
						}
					}
				
				}
			}
			System.out.println("Error Count: " + errorLogs.getErrorCount());
			System.out.println("Transaction Set: " + transactionSet.getTransactionSet().toString());
			System.out.println("Error Logs: " + errorLogs.toString());
			// Always close files.
			/*USE A FINALLY?*/
			fileReader.close();
			scanner.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileInputName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileInputName + "'");
		}
		//System.out.println("Error Count: " + errorCount);
		if(errorLogs.getErrorCount() >0){
		transactionSet = null;
		}
		
		return transactionSet;
		

	}

	/*
	 * Output items
	 * 
	 * 1. a collection of rule objects within the program, where each rule has
	 * an antecedent and consequent that contain one or more items 2. a text
	 * file containing the rules in the format: If ProductA and ProductB THEN
	 * ProductC and ProductD (confidence: 0.78), where again the antecedent and
	 * consequent may each contain one or more product names.
	 */

	/* Method to write to a text file */
	public static void writeFile(RuleSet ruleSets, String fileOutputName, ErrorLogs errorLogs) {
		// The name of the file to open.
		String errorFileName = "src/errorLogs_"+fileOutputName;
		fileOutputName = "src/" + fileOutputName;
		
		try {
			PrintWriter writer = new PrintWriter(fileOutputName);
			for (int i = 0; i < ruleSets.getRuleSet().size(); i++) {
				writer.println(ruleSets.getRuleSet().get(i));// get each rule set and print the result
			}
			writer.close();
		} catch (IOException ex) {
			System.out
					.println("Error writing to file: '" + fileOutputName + "'");
		}
		try {
			PrintWriter error_writer = new PrintWriter(errorFileName);
		for (int i = 0; i < errorLogs.getErrorMsgs().size(); i++) {
			error_writer.println(errorLogs.getErrorMsgs().get(i));// get each rule set and print the result
		}
			error_writer.close();
		} catch (IOException ex) {
			System.out
					.println("Error writing to file: '" + errorFileName + "'");
		}
		
	}

}
