package edu.cs355.group11.fisherjk;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface GeneratorResource {
	@Get
	public RuleSet retrieve();

	@Put
	public void store(Generator generator);
}
