package fisherjk;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface GeneratorResource {
	@Get
	public RuleSet retrieve();

	@Put
	public void store(Generator generator);
}
