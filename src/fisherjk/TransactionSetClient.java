package fisherjk;

import org.restlet.resource.ClientResource;

public class TransactionSetClient {
	public static void main (String [] args) throws Exception {

		//Address address = new Address("123 S. Main", "", "54701", "Eau Claire", "USA");
		//Contact contact = new Contact("Zaphod", "Beeblebrox", address, 20);
		TransactionSet transSet = new TransactionSet();
		TransactionSet newTransSet = null;
		
		ClientResource clientResource = new ClientResource("http://localhost:8111/");
        
		TransactionSetResource proxy = clientResource.wrap(TransactionSetResource.class);

		proxy.store(transSet);
		newTransSet = proxy.retrieve();

		if (newTransSet != null) {
            System.out.println("start_date: " + newTransSet.getStart_date());
            System.out.println("end_date: " + newTransSet.getEnd_date());
  
		}
		else {
			System.out.println("got null transactionSet");
		}
	}
}
