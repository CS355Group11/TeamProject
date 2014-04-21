package edu.uwec.cs355.group11.service;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import edu.uwec.cs355.group11.common.Generator;
import edu.uwec.cs355.group11.common.RuleSet;

public interface GeneratorResource {
	@Get
	public RuleSet retrieve();

	@Put
	public void store(Generator generator);
}
