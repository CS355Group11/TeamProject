package fisherjk;

import org.restlet.resource.ClientResource;

public class RuleSetClient {
	public static void main (String [] args) throws Exception {

		//Address address = new Address("123 S. Main", "", "54701", "Eau Claire", "USA");
		//Contact contact = new Contact("Zaphod", "Beeblebrox", address, 20);
		RuleSet ruleSet = new RuleSet();
		RuleSet newRuleSet = null;
		
		ClientResource clientResource = new ClientResource("http://localhost:8111/");
        
		RuleSetResource proxy = clientResource.wrap(RuleSetResource.class);

		proxy.store(ruleSet);
		newRuleSet = proxy.retrieve();

		if (newRuleSet != null) {
            System.out.println("time_stamp: " + newRuleSet.getDefTimeStamp());
  
		}
		else {
			System.out.println("got null RuleSet");
		}
	}
}
