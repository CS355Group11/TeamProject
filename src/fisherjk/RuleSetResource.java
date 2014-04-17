package fisherjk;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface RuleSetResource {
	@Get
	public RuleSet retrieve();

	@Put
	public void store(RuleSet ruleSet);
}
