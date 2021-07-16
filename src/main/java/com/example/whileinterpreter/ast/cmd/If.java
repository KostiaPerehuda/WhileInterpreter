package com.example.whileinterpreter.ast.cmd;

import com.example.whileinterpreter.ast.bexp.BooleanExpression;
import com.example.whileinterpreter.state.State;

public class If implements Command {

	BooleanExpression bool;
	Command cmd1;
	Command cmd2;
	
	public If(BooleanExpression bool, Command cmd1, Command cmd2) {
		this.bool = bool;
		this.cmd1 = cmd1;
		this.cmd2 = cmd2;
	}

	@Override
	public void execute(State state) {
		if (bool.eval()) {
			cmd1.execute(state);
		} else {
			cmd2.execute(state);
		}
	}

}
