package com.example.whileinterpreter.ast.cmd;

import com.example.whileinterpreter.state.State;

public class Sequence implements Command {

	private Command[] commands;

	public Sequence(Command... commands) {
		this.commands = commands.clone();
	}

	@Override
	public void execute(State state) {
		for	(Command command : commands) {
			command.execute(state);
		}
	}

}
