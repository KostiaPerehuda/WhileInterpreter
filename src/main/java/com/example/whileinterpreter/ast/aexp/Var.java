package com.example.whileinterpreter.ast.aexp;

public class Var implements ArithmeticExpression {
	
	private String name;
	
	public Var(String name) {
		this.name = name;
	}

	@Override
	public long eval() {
		// TODO Connect this to internal program state map to fetch the value of the variable
		// return State.get(name);
		return 0;
	}

}
