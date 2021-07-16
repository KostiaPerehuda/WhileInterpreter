package com.example.whileinterpreter.ast.cmd;

import com.example.whileinterpreter.ast.bexp.BooleanExpression;
import com.example.whileinterpreter.state.State;

public class While implements Command {
	
	BooleanExpression bool;
	Command cmd;
	
	public While(BooleanExpression bool, Command cmd) {
		this.bool = bool;
		this.cmd  = cmd;
	}

	@Override
	public void execute(State state) {
		if (!bool.eval()) {
			cmd.execute(state);
			execute(state);
		}
	}

}
