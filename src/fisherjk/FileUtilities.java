package fisherjk;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
		fileInputName = "src/" + fileInputName;
		String line = "";
		TransactionSet transactionSet = new TransactionSet();
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
			
			
			while (scanner.hasNextLine()) {

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

				} else {

					// Find for missing closing and leading brackets
					Pattern leftBracePattern = Pattern.compile(findLeftBrace);
					Pattern rightBracePattern = Pattern.compile(findRightBrace);

					Matcher leftBraceMatcher = leftBracePattern.matcher(line);
					Matcher rightBraceMatcher = rightBracePattern.matcher(line);

					if (leftBraceMatcher.find() && rightBraceMatcher.find()) {/*Determine if a left or right brace is missing*/
						
						Pattern contentPattern = Pattern.compile(findInBrackets);
						Matcher contentMatcher = contentPattern.matcher(line);
						if (contentMatcher.find()) {
							//May need to fix spacing check
							if(contentMatcher.group(1).contentEquals(" ") || contentMatcher.group(1).contentEquals("")){//check to see if empty transaction {} or { }
								System.out.println(contentMatcher.group(1).contentEquals(" "));//space
								System.out.println(contentMatcher.group(1).contentEquals(""));//empty
								System.out.println("Content: " + contentMatcher.group(1));
								System.out.println("Found an empty transaction");
							}else{
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
								while (itemMatcher.find()) {// loop until we don't have any more matches in the groupings
									Item item = new Item(itemMatcher.group(0));
									// System.out.println("Transaction # " +
									// transactionCount + " Item # " + itemCount +
									// ": " + item.getItem());
									itemSet.getItemSet().add(item);// add item to
																	// new itemset
									//itemCount++;
								}
								Transaction transaction = new Transaction(itemSet);// create a new transaction
								transactionSet.getTransactionSet().add(transaction);// append to overall transaction set

							//transactionCount++;// increment the transcation
												// count

							}
						}
					
					} else {
						
						if(vendorMatcher.find()) {
							System.out.println("Vendor is: " + line);
						} else {
						
							if (!leftBraceMatcher.find()) {
								System.out.println("Missing Left brace");
							}
							
							if (!rightBraceMatcher.find()) {
								System.out.println("Missing right brace");
							}
						}
					}
				
				}
			}

			System.out.println(transactionSet.getTransactionSet().toString());
			// Always close files.
			fileReader.close();
			scanner.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileInputName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileInputName + "'");
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
	public static void writeFile(RuleSet ruleSets, String fileOutputName) {
		// The name of the file to open.
		fileOutputName = "src/" + fileOutputName;

		try {
			PrintWriter writer = new PrintWriter(fileOutputName);
			for (int i = 0; i < ruleSets.getRuleSet().size(); i++) {
				writer.println(ruleSets.getRuleSet().get(i));// get each rule set and print the result
			}
			writer.close();
		} catch (IOException ex) {
			System.out
					.println("Error writing to file '" + fileOutputName + "'");
		}
	}

}
