package testing;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import service.Item;
import service.ItemSet;
import service.Transaction;
import service.Vendor;


public class TestTransaction extends TestCase{

	@Test
	public void testTransaction(){
		Transaction trans = new Transaction();
		assertNotNull(trans);
		Transaction trans1 = new Transaction(new ItemSet());
		assertNotNull(trans1);
	}

	@Test
	public void testGetTransaction(){
		Transaction trans = new Transaction();
		ItemSet t = new ItemSet();
		trans.setTransaction(t);
		assertEquals(t, trans.getTransaction());
	}

	@Test
	public void testSetTransaction(){
		Transaction trans = new Transaction();
		trans.setTransactionDate("4/14/2014");
		assertEquals("4/14/2014", trans.getTransactionDate());
	}

	@Test
	public void testGetTransactionDate(){
		Transaction trans = new Transaction();
		trans.setTransactionDate("4/14/2014");
		assertEquals("4/14/2014", trans.getTransactionDate());
	}

	@Test
	public void testSetTransactionDate(){
		Transaction trans = new Transaction();
		trans.setTransactionDate("4/14/2014");
	}

	@Test
	public void testGetTransactionSet_ID(){
		Transaction trans = new Transaction();
		int id = 232423;
		trans.setTransactionSet_ID(id);
		assertEquals(id, trans.getTransactionSet_ID());
	}


	@Test
	public void testSetTransactionSet_ID(){
		Transaction trans = new Transaction();
		trans.setTransactionSet_ID(232423);
		assertEquals(232423, trans.getTransactionSet_ID());
	}

	@Test
	public void testGetVendor(){
		Transaction trans = new Transaction();
		Vendor vendor = new Vendor("Test_Vendor");
		trans.setVendor(vendor);
		assertEquals(vendor, trans.getVendor());
	}

	@Test
	public void testSetVendor(){
		Transaction trans = new Transaction();
		Vendor vendor = new Vendor("Test_Vendor");
		trans.setVendor(vendor);
		assertEquals(vendor, trans.getVendor());
	}

	@Test
	public void testGetTimeStamp(){
		Transaction trans = new Transaction();
		Transaction trans2 = new Transaction();
		trans.setTimestamp();
		trans.getTimestamp();
		trans2.setTimestamp();
		assertEquals(trans.getTimestamp(), trans2.getTimestamp());
	}


	@Test
	public void testSetTimeStamp(){
		Transaction trans = new Transaction();
		Transaction trans2 = new Transaction();
		trans.setTimestamp();
		trans.getTimestamp();
		trans2.setTimestamp();
		assertEquals(trans.getTimestamp(), trans2.getTimestamp());
	}

	@Test
	public void testToString(){
		Transaction trans = new Transaction();
		String string = trans.toString();
		boolean test = (string == " ");
		assertFalse(test);
	}

	@Test
	public void testRemove() {
		Transaction trans = new Transaction();
		ItemSet itemSet = new ItemSet();
		ArrayList<Item> arraySet = new ArrayList<Item>();
		arraySet.add(new Item("Test Item"));
		itemSet.setItemSet(arraySet);
		trans.setTransaction(itemSet);
		assertEquals(1, trans.getTransaction().getItemSet().size());
		trans.getTransaction().getItemSet().remove(trans.getTransaction().getItemSet().get(0));
		assertEquals(0, trans.getTransaction().getItemSet().size());
	}

	/* 1. MySQL
	 * 2. Generator
	 * 3. ItemSet
	 * 4. Rule
	 * 5. Transaction
	 */


}
