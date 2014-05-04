package edu.cs355.group11.junit_testing;



import junit.framework.TestCase;

import org.junit.Test;

import edu.cs355.group11.common.RuleSet;





public class TestRuleSet extends TestCase{

	@Test
	public void testRuleSet(){
		RuleSet ruleSet = new RuleSet();
		assertNotNull(ruleSet);
		RuleSet ruleSet2 = new RuleSet(ruleSet);
		assertNotNull(ruleSet2);

	}

	@Test
	public void testGetRuleSet() {
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSet2 = new RuleSet(ruleSet);
		assertEquals(ruleSet.getRuleSet(), ruleSet2.getRuleSet());

	}

	@Test
	public void testSetRuleSet() {
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSet2 = new RuleSet();
		ruleSet.setRuleSet(ruleSet2.getRuleSet());
		assertEquals(ruleSet.getRuleSet(), ruleSet2.getRuleSet());
	}

	@Test
	public void testGetTimeStamp() {
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSet2 = new RuleSet();
		ruleSet.setTimestamp();
		ruleSet2.setTimestamp();
		ruleSet.getTimestamp();
		boolean test = ruleSet.getTimestamp().equals(ruleSet2.getTimestamp());
		assertTrue(test);
		assertEquals(ruleSet.getTimestamp(), ruleSet2.getTimestamp());
	}

	@Test
	public void testSetTimeStamp() {
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSet2 = new RuleSet();
		ruleSet.setTimestamp();
		ruleSet2.setTimestamp();
		ruleSet.getTimestamp();
		boolean test = ruleSet.getTimestamp().equals(ruleSet2.getTimestamp());
		assertTrue(test);
		assertEquals(ruleSet.getTimestamp(), ruleSet2.getTimestamp());
	}
	
	
	@Test
	public void testGetDefTimeStamp() {
		RuleSet ruleSet = new RuleSet();
		String date = "05-01-2014 12:00:00";
		ruleSet.setDefTimeStamp(date);
		assertEquals(date, ruleSet.getDefTimeStamp());
	
	}

	@Test
	public void testSetDefTimeStamp() {
		RuleSet ruleSet = new RuleSet();
		String date = "05-01-2014 12:00:00";
		ruleSet.setDefTimeStamp(date);
		assertEquals(date, ruleSet.getDefTimeStamp());
	}



}
