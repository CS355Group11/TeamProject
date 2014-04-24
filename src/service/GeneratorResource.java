package service;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import common.Generator;
import common.RuleSet;

public interface GeneratorResource {
	@Get
	public RuleSet retrieve();

	@Put
	public void store(Generator generator);
}
