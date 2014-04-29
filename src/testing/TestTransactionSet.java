package testing;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import common.TransactionSet;
import service.Item;
import service.ItemSet;
import service.Vendor;


public class TestTransactionSet extends TestCase{

	@Test
	public void testTransactionSet() {
		TransactionSet transSet = new TransactionSet();
		assertNotNull(transSet);
		ArrayList<Transaction> transSetSet = new ArrayList<Transaction>();
		TransactionSet transSet1 = new TransactionSet(transSet);
		assertNotNull(transSet1);

	}

	@Test
	public void testGetTransactionSet() {
		TransactionSet transSet1 = new TransactionSet();
		TransactionSet transSet2 = new TransactionSet();
		ArrayList<Transaction> transArray = new ArrayList<Transaction>();
		transArray.add(new Transaction());
		//transSet.setTransactionSet(transArray);
		transSet1.setTransactionSet(transSet2.getTransactionSet());
		assertEquals(transSet1.getTransactionSet(), transSet2.getTransactionSet());
	}

	@Test
	public void testSetTransactionSet() {
		TransactionSet transSet1 = new TransactionSet();
		TransactionSet transSet2 = new TransactionSet();
		//transArray.add(new Transaction());
		transSet1.setTransactionSet(transSet2.getTransactionSet());
		assertEquals(transSet1.getTransactionSet(), transSet2.getTransactionSet());
	}

	@Test
	public void testGetStart_Date() {
		TransactionSet transSet = new TransactionSet();
		transSet.setStart_date("4/14/2014");
		assertEquals("4/14/2014", transSet.getStart_date());

	}

	@Test
	public void testSetStart_Date() {
		TransactionSet transSet = new TransactionSet();
		transSet.setStart_date("4/14/2014");
	}


	@Test
	public void testGetEnd_Date() {
		TransactionSet transSet = new TransactionSet();
		transSet.setEnd_date("4/14/2014");
		assertEquals("4/14/2014", transSet.getEnd_date());
	}

	@Test
	public void testSetEnd_Date() {
		TransactionSet transSet = new TransactionSet();
		transSet.setEnd_date("4/14/2014");
	}

	@Test
	public void testGetVendorSet() {
		TransactionSet transSet = new TransactionSet();
		Vendor v1 = new Vendor("v1");
		ArrayList<Vendor>  venArray = new ArrayList<Vendor>();
		venArray.add(v1);
		transSet.setVendorSet(venArray);
		assertEquals(venArray, transSet.getVendorSet());
	}


	@Test
	public void testSetVendorSet() {
		TransactionSet transSet = new TransactionSet();
		ArrayList<Vendor> venArray = new ArrayList<Vendor>();
		transSet.setVendorSet(venArray);
	}

	@Test
	public void testGetTimestamp() {
		TransactionSet transSet = new TransactionSet();
		transSet.setTimestamp();
		String first = transSet.getTimestamp();
		transSet.setTimestamp();
		String second = transSet.getTimestamp();
		boolean test = (first == second);
		assertFalse(test);
	}

	@Test
	public void testSetTimestamp() {
		TransactionSet transSet = new TransactionSet();
		transSet.setTimestamp();
	}

	@Test
	public void testGetErrorLogs(){
		TransactionSet transSet = new TransactionSet();
	}

	@Test
	public void testSetErrorLogs(){

	}

	@Test
	public void testGetUniqueItems() {
		TransactionSet transSet = new TransactionSet();
		ItemSet one = new ItemSet();
		one = transSet.GetUniqueItems();
		ItemSet two = new ItemSet();
		two = transSet.GetUniqueItems();
		boolean test = (one == two);
		assertFalse(test);
	}

	@Test
	public void testToString() {
		TransactionSet transSet = new TransactionSet();
		String test = transSet.toString();
		assertEquals("", test);

	}

	@Test
	public void testFindSupport() {
		TransactionSet transSet = new TransactionSet();
		ArrayList<Item> list = new ArrayList<Item>();
		Item i1 = new Item("Item1");
		Item i2 = new Item("Item2");
		Item i3 = new Item("Item3");
		list.add(i1);
		list.add(i2);
		list.add(i3);
		
		Transaction t1 = new Transaction();
		t1.getTransaction().setItemSet(list);
		Transaction t2 = new Transaction();
		t2.getTransaction().setItemSet(list);
		
		ItemSet one = new ItemSet();
		one = transSet.GetUniqueItems();
		
		double findSupport = transSet.findSupport(one);
		double test = transSet.findSupport(one);
		boolean test_bool = (test == 0);
		assertTrue(test_bool);
	}

	@Test
	public void testFindKItemSubsets() {
		TransactionSet transSet = new TransactionSet();
		ItemSet itemSet = new ItemSet();
		ArrayList<Item> itemSetArray = new ArrayList<Item>();
		Item inner = new Item("Test Item");
		itemSetArray.add(inner);
		itemSet.setItemSet(itemSetArray);

		TransactionSet t = transSet.findKItemSubsets(itemSet, 0); 
		assertNotNull(t);

	}

	@Test
	public void testGetBit() {
		TransactionSet transSet = new TransactionSet();
		int test = TransactionSet.GetBit(8, 2);
		boolean test_bool = (test == 0);
		assertTrue(test_bool);
	}

	@Test
	public void testDecimalToBinary() {
		TransactionSet transSet = new TransactionSet();
		String test = TransactionSet.DecimalToBinary(64, 4);
		boolean test_bool = (test == "0101");
		assertFalse(test_bool);
	}

	@Test
	public void testGetOnCount() {
		TransactionSet transSet = new TransactionSet();
		int n = TransactionSet.GetOnCount(4, 10);
		boolean test = (n == 101);
		assertFalse(test);
	}
}