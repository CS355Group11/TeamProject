package edu.cs355.group11.fisherjk;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
		ArrayList<Transaction> optional = new ArrayList<Transaction>();
		ErrorLogs errorLogs = new ErrorLogs();
		if(!fileInputName.contains("src/")){
		fileInputName = "src/" + fileInputName;
		}
		String line = "";
		TransactionSet transactionSet = new TransactionSet();
		String formatError = "Format Error: ";
		int errorCount = 0;
		int largestTransactionSize = 0;
		int transactionItemCount  = 0;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileInputName);

			
			Scanner scanner = new Scanner(fileReader);
			String findVendor = "(?<=\\})?(.*)";// regex to find vendor name
			String findDate = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";// regex to find the date
			//String findDateTime = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}.*";
			//String findDateTime2 = "(?<=\\}\\s)[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}";
			String findDateTime3 = "([0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2})";
			String findBrackets = "\\{(\\s+)?|(\\s+)?\\}";// regex to find brackets for eventual removal
			String findInBrackets = "\\{(.*)\\}";// regex to find content within  brackets
			//String findInBrackets2 = "\\{\\s+(.*)\\s+\\}";
			//String findInBracketsDate = "(\\{(.*)\\})(\\s[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2})?";
			String findCommas = "\\,\\s?";// regex to find commas and any white space immediately after it
			String findDescItem = "(.*)[^\\n]";// regex to find items
			String findLeftBrace = "\\{";
			String findRightBrace = "\\}";
			//String findRightBraceDate = "(?<=\\}\\s)[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}";
			//String findSpaces = "\\s";
			int lineNum = 0;
			//int transNum = 0;
			String temp ="";
			//int opDates = 0;
			HashMap<Integer, String> hashDate = new HashMap<Integer, String>();
			
			while (scanner.hasNextLine() &&  errorLogs.getErrorCount()==0) {
				
				line = scanner.nextLine();
				lineNum++;
				
				Pattern vendorPattern = Pattern.compile(findVendor);
				Matcher vendorMatcher = vendorPattern.matcher(line);
				
				Pattern datePattern = Pattern.compile(findDate);
				Matcher dateMatcher = datePattern.matcher(line);
				
				Pattern dateTimePattern = Pattern.compile(findDateTime3);
				Matcher dateTimeMatcher = dateTimePattern.matcher(line);
				
				if (lineNum <= 3 && dateMatcher.find()) {/*determine if we found a date string*/
					if(transactionSet.getStart_date() == null){
						transactionSet.setStart_date(line);
					}else{
						transactionSet.setEnd_date(line);
					}

				} else {
					if (dateTimeMatcher.find()) {
						//opDates++;
						temp = line;
						temp = dateTimeMatcher.group(0);
						Transaction transDate = new Transaction();
						transDate.setTransactionDate(temp);
						optional.add(transDate);
						hashDate.put(lineNum, temp);
						line = line.replaceFirst(findDateTime3, "");
						
					}
					
					// Find for missing closing and leading brackets
					Pattern leftBracePattern = Pattern.compile(findLeftBrace);
					Pattern rightBracePattern = Pattern.compile(findRightBrace);

					Matcher leftBraceMatcher = leftBracePattern.matcher(line);
					Matcher rightBraceMatcher = rightBracePattern.matcher(line);

					
					if (leftBraceMatcher.find() && rightBraceMatcher.find()) {/*Determine if a left or right brace is missing*/
						//System.out.println("Found Left and Right Brace");
						Pattern contentPattern = Pattern.compile(findInBrackets);
						Matcher contentMatcher = contentPattern.matcher(line);
						if(rightBraceMatcher.find()){
							errorLogs.getErrorMsgs().add(formatError +"Found Extra Right Brace on Line #" + lineNum);
						}
						if(leftBraceMatcher.find()){
							errorLogs.getErrorMsgs().add(formatError +"Found Extra Left Brace on Line #"+ lineNum);
						}
						if (contentMatcher.find()) {
							if(contentMatcher.group(1).contentEquals(" ") || contentMatcher.group(1).contentEquals("")){//check to see if empty transaction {} or { }
								errorLogs.getErrorMsgs().add(formatError + "Found an empty transaction");
							}else if(errorCount == 0){//while no errors found
								//transNum++;
								Pattern bracketPattern = Pattern.compile(findBrackets);
								Matcher bracketMatcher = bracketPattern.matcher(line);
								line = bracketMatcher.replaceAll("");// strip brackets
	
								Pattern commaPattern = Pattern.compile(findCommas);
								Matcher commaMatcher = commaPattern.matcher(line);
								line = commaMatcher.replaceAll("\n");// strip commas and replace with new lines
								
								Pattern itemPattern = Pattern.compile(findDescItem);
								Matcher itemMatcher = itemPattern.matcher(line);// find individual items on a new line basis
								ItemSet itemSet = new ItemSet();// create a new ItemSet
								//int uniqueItems = transactionSet.GetUniqueItems().getItemSet().size();
								int totalItems = 0;
								while (itemMatcher.find()) {// loop until we don't have any more matches in the groupings
									
									//Pattern spacePattern = Pattern.compile(findSpaces);
									//Matcher spaceMatcher = spacePattern.matcher(itemMatcher.group(0));
									//String itemNow = itemMatcher.group(0).replaceAll(findSpaces, "");//.replaceAll("space");
									Item item = new Item(itemMatcher.group(0));
									itemSet.getItemSet().add(item);// add item to new itemset
									totalItems++;
								}
								Transaction transaction = new Transaction(itemSet);// create a new transaction				
								transaction.setTimestamp();
								String date = transaction.getTimestamp();
								if(temp != ""){
									date = temp;
								}
								transaction.setTransactionDate(date);
								transactionSet.getTransactionSet().add(transaction);// append to overall transaction set
								temp ="";
								transactionItemCount = transaction.getTransaction().getItemSet().size();
								if(transactionItemCount > largestTransactionSize){
									largestTransactionSize = transactionItemCount;
								}
								
								if(largestTransactionSize > 25){
									errorLogs.getErrorMsgs().add(formatError +"Too many items in transaction set (Must be 25 or less items)");
									
								}
								
								//if(uniqueItems > 25){
								//	errorLogs.getErrorMsgs().add(formatError +"Too many unique items in transaction set");
									
								//}
								
								if(totalItems > 1000){
									errorLogs.getErrorMsgs().add(formatError +"Too many items in transaction set");//start here
									
								}
								
							}
						}
					
					} else {
						
						if(vendorMatcher.find()) {//figure out how to set vendors to transactions
							Vendor vendor = new Vendor(line);
							av.add(vendor);
							transactionSet.setVendorSet(av);
						} else {
						
							if (!leftBraceMatcher.find()) {
								errorLogs.getErrorMsgs().add(formatError +"Missing Left brace on Line # "+ lineNum);
							
							}
							
							if (!rightBraceMatcher.find()) {
								errorLogs.getErrorMsgs().add(formatError +"Missing Right brace on Line #"+ lineNum);
							}
						}
					}
				
				}
			}
			fileReader.close();
			scanner.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileInputName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileInputName + "'");
		}
		
		if(av.isEmpty()){
			//System.out.println("Format Error: No Vendor Provided");
			//errorLogs.add("Format Error: No Vendor Provided");
		}
		//System.out.println("Error Count: " + errorCount);
		if(errorLogs.getErrorCount() >0){
		//transactionSet = null;
		//FileUtilities.writeCurrentErrors(errorLogs, "well.txt");
		}
		transactionSet.setErrorLogs(errorLogs);
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
	public static void writeFile(RuleSet ruleSets, String fileOutputName, ErrorLogs errorLogs, String errorFileOutputName) {
		// The name of the file to open.
		if(fileOutputName == "" || errorFileOutputName == ""){
			errorLogs.getErrorMsgs().add("The specified output path(s) are empty");
		}
		
		if(!fileOutputName.contains("src/")){
			fileOutputName = "src/" + fileOutputName;
		}
		if(!errorFileOutputName.contains("src/")){
			errorFileOutputName = "src/"+errorFileOutputName;
		}
		int ruleSize = ruleSets.getRuleSet().size();
		int errorSize = errorLogs.getErrorMsgs().size();
		System.out.println("rule size : " + ruleSize);
		System.out.println("error size: " + errorSize);
		try {
			PrintWriter writer = new PrintWriter(fileOutputName);
			if(ruleSize >0){
			for (int i = 0; i < ruleSize; i++) {
				writer.println(ruleSets.getRuleSet().get(i));// get each rule set and print the result
			}
			}else{
				errorLogs.getErrorMsgs().add("No Rules could be generated under these parameter levels. Increase Transaction Set Size or MCL/MSL.");
			}
			writer.close();
		} catch (IOException ex) {
			System.out
					.println("Error writing to file: '" + fileOutputName + "'");
		}
		try {
			PrintWriter error_writer = new PrintWriter(errorFileOutputName);
		if(errorSize >0){	
		for (int i = 0; i < errorLogs.getErrorMsgs().size(); i++) {
			error_writer.println(errorLogs.getErrorMsgs().get(i));// get each rule set and print the result
		}
		}else{
			error_writer.println("No Error's Occured in Rule Generation. Excellent Job!");
		}
			error_writer.close();
		} catch (IOException ex) {
			System.out
					.println("Error writing to file: '" + errorFileOutputName + "'");
		}
		
	}
	
	
	public static void writeCurrentErrors(ErrorLogs errorLogs, String errorFileOutputName){
		int errorSize = errorLogs.getErrorMsgs().size();
		System.out.println("writing current Errors");
		try {
			PrintWriter error_writer = new PrintWriter(errorFileOutputName);
		
		for (int i = 0; i < errorSize; i++) {
			error_writer.println(errorLogs.getErrorMsgs().get(i));// get each rule set and print the result
		}
	
			error_writer.close();
		} catch (IOException ex) {
			System.out
					.println("Error writing to file: '" + errorFileOutputName + "'");
		}
		
	}
	
	
	

}
