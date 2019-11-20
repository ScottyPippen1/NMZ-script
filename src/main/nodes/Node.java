package main.nodes;

import org.dreambot.api.methods.MethodContext;

public abstract class Node {
	
	final MethodContext cm;
	
	public Node(final MethodContext cm){
		this.cm = cm;
	}
	
	public abstract boolean validate();
	
	public abstract int execute();
	
	public abstract String getName();
	
}
