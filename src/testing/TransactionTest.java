package testing;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import fisherjk.Item;
import fisherjk.ItemSet;
import fisherjk.Transaction;
import fisherjk.Vendor;

public class TransactionTest {
	@Test
	public void TransactionTest() {
		Transaction trans = new Transaction();
		assertNotNull(trans);
		Transaction trans1 = new Transaction(new ItemSet());
		assertNotNull(trans1);
	}
	
	@Test
	public void transactionSetGetTest() {
		Transaction trans = new Transaction();
		ItemSet t = new ItemSet();
		trans.setTransaction(t);
		assertEquals(t, trans.getTransaction());
	}
	
	@Test
	public void transactionDateSetGetTest() {
		Transaction trans = new Transaction();
		trans.setTransactionDate("4/14/2014");
		assertEquals("4/14/2014", trans.getTransactionDate());
	}
	
	@Test
	public void transactionTotalPriceGetSet() {
		Transaction trans = new Transaction();
		trans.setTransactionTotalPrice(1323.232);
		boolean test = (trans.getTransactionTotalPrice(trans) == 1323.232);
		assertTrue(test);
	}
	
	@Test
	public void transactionSet_IDGetSetTest() {
		Transaction trans = new Transaction();
		trans.setTransactionSet_ID(232423);
		assertEquals(232423, trans.getTransactionSet_ID());
		
	}
	
	@Test
	public void vendorGetSetTest() {
		Transaction trans = new Transaction();
		Vendor vendor = new Vendor("Test_Vendor");
		trans.setVendor(vendor);
		assertEquals(vendor, trans.getVendor());
	}
	
	@Test
	public void getTimeStampTest() {
		Transaction trans = new Transaction();
		trans.setTimestamp();
		
	}
	
	@Test
	public void toStringTest() {
		Transaction trans = new Transaction();
		String string = trans.toString();
		boolean test = (string == " ");
		assertFalse(test);
	}
	
	@Test
	public void removeTest() {
		Transaction trans = new Transaction();
		ItemSet itemSet = new ItemSet();
		ArrayList<Item> arraySet = new ArrayList<Item>();
		arraySet.add(new Item("Test Item"));
		itemSet.setItemSet(arraySet);
		trans.setTransaction(itemSet);
		trans.remove(trans);
		boolean test = (trans.getTransaction() != itemSet);
		assertTrue(test);
	}
}
