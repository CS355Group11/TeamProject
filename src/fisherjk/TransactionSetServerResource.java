package fisherjk;

import org.restlet.resource.ServerResource;

public class TransactionSetServerResource extends ServerResource
									implements TransactionSetResource {
	// data
	private static TransactionSet transactionSet = new TransactionSet();
	
	// methods
	public TransactionSetServerResource () {
		System.out.println("TransactionSetServerResource constructor");
	}

	public TransactionSet retrieve() {
		TransactionSetServerResource.transactionSet.setStart_date("2014-17-04 12:00:00");
		TransactionSetServerResource.transactionSet.setEnd_date("2014-17-04 12:01:00");
		System.out.println("Set TransactionSet start_date to: " + TransactionSetServerResource.transactionSet.getStart_date());
		System.out.println("Set TransactionSet end_date to: " + TransactionSetServerResource.transactionSet.getEnd_date());
		return TransactionSetServerResource.transactionSet; 
	}
	
	public void store (TransactionSet transactionSet) {
		TransactionSetServerResource.transactionSet = new TransactionSet(transactionSet);
		System.out.println("Start Date: " + TransactionSetServerResource.transactionSet.getStart_date());
		System.out.println("End Date  : " + TransactionSetServerResource.transactionSet.getEnd_date());
	}


}
