package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Command;
import com.github.kostiaperehuda.whileinterpreter.state.State;

public class CommandInterpreter {
    public CommandInterpreter(ArithmeticExpressionInterpreter arithmeticExpressionInterpreter) {
    }

    public CommandInterpreter() {
        this(new ArithmeticExpressionInterpreter());
    }

    public void execute(Command command, State state) {
    }
}
