package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import fisherjk.Item;
import fisherjk.ItemSet;
import fisherjk.Transaction;
import fisherjk.TransactionSet;
import fisherjk.Vendor;

public class TransactionSetTest {
	
	@Test
	public void transSet() {
		TransactionSet transSet = new TransactionSet();
		assertNotNull(transSet);
		ArrayList<Transaction> transSetSet = new ArrayList<Transaction>();
		TransactionSet transSet1 = new TransactionSet(transSetSet);
		assertNotNull(transSet1);
		
	}
	
	@Test
	public void transSetGetSetTest() {
		TransactionSet transSet = new TransactionSet();
		ArrayList<Transaction> transArray = new ArrayList<Transaction>();
		transArray.add(new Transaction());
		transSet.setTransactionSet(transArray);
		assertEquals(transArray, transSet.getTransactionSet());
	}
	
	@Test
	public void startDateGetSetTest() {
		TransactionSet transSet = new TransactionSet();
		transSet.setStart_date("4/14/2014");
		assertEquals("4/14/2014", transSet.getStart_date());
	}
	
	@Test
	public void endDateGetSetTest() {
		TransactionSet transSet = new TransactionSet();
		transSet.setEnd_date("4/14/2014");
		assertEquals("4/14/2014", transSet.getEnd_date());
	}
	
	@Test
	public void vendorTest() {
		TransactionSet transSet = new TransactionSet();
		ArrayList<Vendor> venArray = new ArrayList<Vendor>();
		transSet.setVendorSet(venArray);
		assertEquals(venArray, transSet.getVendorSet());
	}
	
	@Test
	public void timeStampTest() {
		TransactionSet transSet = new TransactionSet();
		transSet.setTimestamp();
		String first = transSet.getTimestamp();
		transSet.setTimestamp();
		String second = transSet.getTimestamp();
		boolean test = (first == second);
		assertFalse(test);
	}
	
	@Test
	public void getUniqueItemsTest() {
		TransactionSet transSet = new TransactionSet();
		ItemSet one = new ItemSet();
		one = transSet.GetUniqueItems();
		ItemSet two = new ItemSet();
		two = transSet.GetUniqueItems();
		boolean test = (one == two);
		assertFalse(test);
	}
	
	@Test
	public void toStringTest() {
		TransactionSet transSet = new TransactionSet();
		String test = transSet.toString();
		assertEquals("", test);
		
	}
	
	@Test
	public void findSupportTest() {
		TransactionSet transSet = new TransactionSet();
		ItemSet one = new ItemSet();
		one = transSet.GetUniqueItems();
		double test = transSet.findSupport(one);
		boolean test_bool = (test != 0);
		assertTrue(test_bool);
	}
	
	@Test
	public void findKItemSubsetsTest() {
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
	public void GetBitTest() {
		TransactionSet transSet = new TransactionSet();
		int test = transSet.GetBit(8, 2);
		boolean test_bool = (test == 0);
		assertFalse(test_bool);
		
	}
	
	@Test
	public void decimalToBinaryTest() {
		TransactionSet transSet = new TransactionSet();
		String test = transSet.DecimalToBinary(64, 4);
		boolean test_bool = (test == "0101");
		assertFalse(test_bool);
	}
	
	@Test
	public void getOnCountTest() {
		TransactionSet transSet = new TransactionSet();
		int n = transSet.GetOnCount(4, 10);
		boolean test = (n == 101);
		assertFalse(test);
	}
}
