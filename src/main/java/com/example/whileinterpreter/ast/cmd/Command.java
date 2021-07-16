package com.example.whileinterpreter.ast.cmd;

import com.example.whileinterpreter.state.State;

public interface Command {
	public void execute(State state);
}
