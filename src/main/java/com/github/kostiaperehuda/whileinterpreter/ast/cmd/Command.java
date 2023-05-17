package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.state.State;

public interface Command {
	public void execute(State state);
}
