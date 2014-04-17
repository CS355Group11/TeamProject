package edu.uwec.cs355.group11.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import fisherjk.ItemSet;
import fisherjk.Rule;

public class RuleTest {
	
	@Test
	public void RuleTest() {
		Rule rule = new Rule();
		Rule rule1 = new Rule(new ItemSet(), new ItemSet(), 1.0, 1.0);
		assertNotNull(rule);
		assertNotNull(rule1);
		
	}
	
	@Test
	public void AntecedentSetGetTest() {
		Rule rule = new Rule();
		ItemSet x = new ItemSet();
		rule.setX(x);
		assertEquals(x, rule.getX());
	}
	
	@Test
	public void consequentSetGetTest() {
		Rule rule = new Rule();
		ItemSet y = new ItemSet();
		rule.setY(y);
		assertEquals(y, rule.getY());
	}
	
	@Test
	public void minSupportGetSetTest() {
		Rule rule = new Rule();
		rule.setMinSupportLevel(2.0);
		boolean test = (rule.getMinSupportLevel() == 2.0);
		assertTrue(test);
	}
	
	@Test
	public void actualConLevelGetSetTest() {
		Rule rule = new Rule();
		rule.setActualConfidenceLevel(3.0);
		boolean test = (rule.getActualConfidenceLevel() == 3.0);
		assertTrue(test);
	}
	
	@Test
	public void ruleSet_IDGetSetTest() {
		Rule rule = new Rule();
		rule.setRuleSet_ID(3245);
		assertEquals(3245, rule.getRuleSet_ID());
	}
	
	@Test
	public void toStringTest() {
		Rule rule = new Rule();
		String test = rule.toString();
		boolean test_bool = (test == " ");
		assertFalse(test_bool);
	}
}
