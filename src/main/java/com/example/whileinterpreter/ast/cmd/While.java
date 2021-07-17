package com.example.whileinterpreter.ast.cmd;

import com.example.whileinterpreter.ast.bexp.BooleanExpression;
import com.example.whileinterpreter.state.State;

public class While implements Command {
	
	private BooleanExpression bool;
	private Command cmd;
	
	public While(BooleanExpression bool, Command cmd) {
		this.bool = bool;
		this.cmd  = cmd;
	}

	@Override
	public void execute(State state) {
		while (bool.eval(state)) {
			cmd.execute(state);
		}
	}

}
