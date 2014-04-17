package fisherjk;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface TransactionSetResource {
	@Get
	public TransactionSet retrieve();

	@Put
	public void store(TransactionSet transactionSet);
}
