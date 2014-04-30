package testing;



import junit.framework.TestCase;

import org.junit.Test;

import service.ItemSet;
import service.Rule;



public class TestRule extends TestCase{

	@Test
	public void testRule(){
		Rule rule = new Rule();
		assertNotNull(rule);
	}

	@Test
	public void testGetX() {
		Rule rule = new Rule();
		ItemSet i = new ItemSet();
		rule.setX(i);
		assertEquals(i, rule.getX());

	}

	@Test
	public void testSetX() {
		Rule rule = new Rule();
		ItemSet i = new ItemSet();
		rule.setX(i);
		assertEquals(i, rule.getX());
	}

	@Test
	public void testGetY() {
		Rule rule = new Rule();
		ItemSet i = new ItemSet();
		rule.setY(i);
		assertEquals(i, rule.getY());
	}

	@Test
	public void testSetY() {
		Rule rule = new Rule();
		ItemSet i = new ItemSet();
		rule.setY(i);
		assertEquals(i, rule.getY());
	}

	@Test
	public void testGetMinSupportLevel() {
		Rule rule = new Rule();
		rule.setMinSupportLevel(.5);
		boolean test = (rule.getMinSupportLevel() == .5);
		assertTrue(test);
	}

	@Test
	public void testSetMinSupportLevel() {
		Rule rule = new Rule();
		rule.setMinSupportLevel(.5);
		boolean test = (rule.getMinSupportLevel() == .5);
		assertTrue(test);
	}

	@Test
	public void testGetActualConfidenceLevel() {
		Rule rule = new Rule();
		rule.setActualConfidenceLevel(.5);
		boolean test = (rule.getActualConfidenceLevel() == .5);
		assertTrue(test);
	}

	@Test
	public void testSetActualConfidenceLevel() {
		Rule rule = new Rule();
		rule.setActualConfidenceLevel(.5);
		boolean test = (rule.getActualConfidenceLevel() == .5);
		assertTrue(test);
	}

	@Test
	public void testGetRuleSet_ID() {
		Rule rule = new Rule();
		rule.setRuleSet_ID(2);
		assertEquals(2, rule.getRuleSet_ID());
	}

	@Test
	public void testSetRuleSet_ID() {
		Rule rule = new Rule();
		rule.setRuleSet_ID(2);
		assertEquals(2, rule.getRuleSet_ID());
	}




	/* 1. MySQL
	 * 2. Generator
	 * 3. ItemSet
	 * 4. Rule
	 * 5. Transaction
	 */


}
