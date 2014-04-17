package edu.uwec.cs355.group11.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import fisherjk.Generator;
import fisherjk.ItemSet;
import fisherjk.RuleSet;
import fisherjk.Transaction;
import fisherjk.TransactionSet;

public class RuleGeneratorTest {
	
	@Test
	public void genTest() {
		Generator gen = new Generator();
		Generator gen1 = new Generator(2.0, 1.00);
		assertNotNull(gen);
		assertNotNull(gen1);
		
	}
	
	@Test 
	public void minSupportLevelGetSetTest() {
		Generator gen = new Generator();
		gen.setGenerator_minConfidenceLevel(1.0);
		boolean test = (gen.getGenerator_minConfidenceLevel() == 1.0);
		assertTrue(test);
	}
	
	@Test
	public void minConfidenceLevelGetSetTest() {
		Generator gen = new Generator();
		gen.setGenerator_minConfidenceLevel(2.0);
		boolean test = (gen.getGenerator_minConfidenceLevel() == 1.0);
		assertTrue(test);
	}
	
	@Test
	public void doAprioriTest() {
		Generator gen = new Generator();
		TransactionSet transSet = new TransactionSet(new ArrayList<Transaction>());
		TransactionSet test = gen.DoApriori(transSet, 1.0);
		assertNotNull(test);
	}
	
	@Test
	public void generateRuleSetTest() {
		Generator gen = new Generator();
		TransactionSet transSet = new TransactionSet();
		TransactionSet set = new TransactionSet();
		TransactionSet finalLargeItemSet = gen.DoApriori(set, 1.0);
		RuleSet ruleSet = gen.GenerateRuleSets(transSet, finalLargeItemSet, 1.0);
		RuleSet test = new RuleSet();
		boolean test_bool = (ruleSet == test);
		assertFalse(test_bool);
	}
	
	@Test
	public void findRuleSubsetsTest() {
		Generator gen = new Generator();
		ItemSet candidates = new ItemSet();
		ArrayList<ItemSet> sets = new ArrayList<ItemSet>();
		ArrayList<ItemSet> test = gen.findRuleSubsets(candidates, sets);
		boolean test_bool = (test != sets);
		assertTrue(test_bool);
		
	}
}
