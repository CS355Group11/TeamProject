package edu.cs355.group11.junit_testing;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import edu.cs355.group11.service.Item;
import edu.cs355.group11.service.ItemSet;

public class TestItemSet extends TestCase{

	@Test
	public void testItemSet(){
		ItemSet itemSet = new ItemSet();
		assertNotNull(itemSet);
	}

	@Test
	public void testGetItemSet(){
		ItemSet itemSet = new ItemSet();
		ArrayList<Item> itemArray = new ArrayList<>();
		itemSet.setItemSet(itemArray);
		assertEquals(itemArray, itemSet.getItemSet());
	}

	@Test
	public void testSetItemSet(){
		ItemSet itemSet = new ItemSet();
		ArrayList<Item> itemArray = new ArrayList<>();
		itemSet.setItemSet(itemArray);
		assertEquals(itemArray, itemSet.getItemSet());
	}

	@Test
	public void testGetItemSetSupport(){
		ItemSet itemSet = new ItemSet();
		itemSet.setItemSetSupport(2.0);
		boolean test = (itemSet.getItemSetSupport() == 2.0);
		assertTrue(test);
	}

	@Test
	public void testSetItemSetSupport(){
		ItemSet itemSet = new ItemSet();
		itemSet.setItemSetSupport(2.0);
		boolean test = (itemSet.getItemSetSupport() == 2.0);
		assertTrue(test);
	}

	@Test
	public void testToString(){
		ItemSet i = new ItemSet();
		String test = i.toString();
		assertNotSame(" ", test);
	}

	@Test
	public void testToStringWithoutSupport(){
		ItemSet i = new ItemSet();
		String test = i.toStringWithoutSupport();
		assertNotSame(" ",test);
	}

	@Test
	public void testEquals(){
		Item i1 = new Item("Item1");
		Item i2 = new Item("Item2");
		ArrayList<Item> a1 = new ArrayList<Item>();
		a1.add(i1);
		ArrayList<Item> a2 = new ArrayList<Item>();
		a1.add(i2);
		ItemSet i = new ItemSet();
		ItemSet j = new ItemSet();
		i.setItemSet(a1);
		j.setItemSet(a2);

		boolean test = i.equals(j);
		assertFalse(test);
	}

	@Test
	public void testContainsItemSet(){
		//Transaction t1 = new Transaction();
		ArrayList<Item> sub = new ArrayList<>();
		sub.add(new Item("Item"));
		ItemSet i = new ItemSet(sub);
		boolean test = i.containsItemSet(sub);
		System.out.println("test: " + test);
		assertTrue(test);
	}

	/* 1. MySQL
	 * 2. Generator
	 * 3. ItemSet
	 * 4. Rule
	 * 5. Transaction
	 */


}
