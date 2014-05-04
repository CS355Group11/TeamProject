package edu.cs355.group11.service;

import org.restlet.Server;
import org.restlet.data.Protocol;

public class GeneratorServer {
	public static void main (String [] args) throws Exception {
		Server generatorServer = new Server(Protocol.HTTP, 8111, GeneratorServerResource.class);
		generatorServer.start();
	}
}
