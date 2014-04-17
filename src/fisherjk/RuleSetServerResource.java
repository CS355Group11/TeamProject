package fisherjk;

import org.restlet.resource.ServerResource;

public class RuleSetServerResource extends ServerResource
									implements RuleSetResource {
	// data
	private static RuleSet ruleSet = new RuleSet();
	
	// methods
	public RuleSetServerResource () {
		System.out.println("RuleSetServerResource constructor");
	}

	public RuleSet retrieve() {
		RuleSetServerResource.ruleSet.setDefTimeStamp("2014-17-04 12:00:00");
		System.out.println("Set RuleSet time_stamp to: " + RuleSetServerResource.ruleSet.getDefTimeStamp());
		return RuleSetServerResource.ruleSet; 
	}
	
	public void store (RuleSet ruleSet) {
		RuleSetServerResource.ruleSet = new RuleSet(ruleSet);
		System.out.println("Time Stamp: " + RuleSetServerResource.ruleSet.getDefTimeStamp());
	}



}
