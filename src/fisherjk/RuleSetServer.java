package fisherjk;

import org.restlet.Server;
import org.restlet.data.Protocol;

public class RuleSetServer {
	public static void main (String [] args) throws Exception {
		Server ruleSetServer = new Server(Protocol.HTTP, 8111, RuleSetServerResource.class);
		ruleSetServer.start();
	}
}
