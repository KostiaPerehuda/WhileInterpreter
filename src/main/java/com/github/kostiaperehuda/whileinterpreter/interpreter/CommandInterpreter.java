package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Command;
import com.github.kostiaperehuda.whileinterpreter.state.State;

public class CommandInterpreter {

    private final ArithmeticExpressionInterpreter arithmeticExpressionInterpreter;
    private final BooleanExpressionInterpreter booleanExpressionInterpreter;

    public CommandInterpreter(ArithmeticExpressionInterpreter arithmeticExpressionInterpreter, BooleanExpressionInterpreter booleanExpressionInterpreter) {
        this.arithmeticExpressionInterpreter = arithmeticExpressionInterpreter;
        this.booleanExpressionInterpreter = booleanExpressionInterpreter;
    }

    public CommandInterpreter() {
        this.arithmeticExpressionInterpreter = new ArithmeticExpressionInterpreter();
        this.booleanExpressionInterpreter = new BooleanExpressionInterpreter(this.arithmeticExpressionInterpreter);
    }

    public void execute(Command command, State state) {
    }
}
