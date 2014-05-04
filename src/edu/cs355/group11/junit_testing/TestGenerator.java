package edu.cs355.group11.junit_testing;


import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import edu.cs355.group11.common.FileUtilities;
import edu.cs355.group11.common.Generator;
import edu.cs355.group11.common.RuleSet;
import edu.cs355.group11.common.TransactionSet;
import edu.cs355.group11.service.ItemSet;
import edu.cs355.group11.service.Transaction;

public class TestGenerator extends TestCase{
	
	@Test
	public void testGenerator(){
		String filePath = "test";
		Generator gen = new Generator(0.5, 0.5, filePath);
		assertNotNull(gen);
	}
	
	/*GETTERS AND SETTERS*/
	@Test
	public void testGetGenerator_minSupportLevel(){
		Generator gen = new Generator();
		gen.setGenerator_minSupportLevel(0.5);
		double msl = gen.getGenerator_minSupportLevel();
		assertEquals(0.5, msl);
	}
	
	@Test
	public void testSetGenerator_minSupportLevel(){
		Generator gen = new Generator();
		gen.setGenerator_minSupportLevel(0.5);
		double msl = gen.getGenerator_minSupportLevel();
		assertEquals(0.5, msl);
	}
	
	
	@Test
	public void testGetGenerator_minConfidenceLevel(){
		Generator gen = new Generator();
		gen.setGenerator_minConfidenceLevel(0.5);
		double mcl = gen.getGenerator_minConfidenceLevel();
		assertEquals(0.5, mcl);
	}
	
	@Test
	public void testSetGenerator_minConfidenceLevel(){
		Generator gen = new Generator();
		gen.setGenerator_minConfidenceLevel(0.5);
		double mcl = gen.getGenerator_minConfidenceLevel();
		assertEquals(0.5, mcl);
	}
	
	@Test
	public void testGetGenerator_filePath(){
		Generator gen = new Generator();
		gen.setGenerator_filePath("file_path.txt");
		String filePath = gen.getGenerator_filePath();
		assertEquals("file_path.txt", filePath);
	}
	
	@Test
	public void testSetGenerator_filePath(){
		Generator gen = new Generator();
		gen.setGenerator_filePath("file_path.txt");
		String filePath = gen.getGenerator_filePath();
		assertEquals("file_path.txt", filePath);
	}
	
	/*END OF GETTER AND SETTERS*/
	
	@Test
	public void testDoAPriori(){
		TransactionSet transSet = FileUtilities.readFile("test.txt");
		TransactionSet candidates = Generator.DoApriori(transSet, 0.5);
		assertNotNull(candidates);
	}
	
	@Test
	public void testGenerateRuleSets(){
		TransactionSet transSet = FileUtilities.readFile("test.txt");
		TransactionSet candidates = Generator.DoApriori(transSet, 0.5);
		RuleSet ruleSet = Generator.GenerateRuleSets(transSet, candidates, 0.5);
		assertNotNull(ruleSet);
	}
	
	@Test
	public void testFindRuleSubsets(){
		TransactionSet transSet = FileUtilities.readFile("test.txt");
		TransactionSet candidates = Generator.DoApriori(transSet, 0.5);
		ArrayList<ItemSet> possibleRules = new ArrayList<ItemSet>();
		for (Transaction itemset :candidates.getTransactionSet()) {
			possibleRules = new ArrayList<ItemSet>();
			possibleRules = Generator.findRuleSubsets(itemset.getTransaction(), possibleRules);
		}
		assertNotNull(possibleRules);
	}

}
