package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.cmd.*;
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
        if (command instanceof Skip) return;

        if (command instanceof Assign assign) {
            state.put(assign.variableName(), arithmeticExpressionInterpreter.evaluate(assign.expression(), state));
        } else if (command instanceof Sequence sequence) {
            execute(sequence.left(), state);
            execute(sequence.right(), state);
        } else if (command instanceof If ifStatement) {
            if (booleanExpressionInterpreter.evaluate(ifStatement.condition(), state)) {
                execute(ifStatement.ifBranch(), state);
            } else {
                execute(ifStatement.elseBranch(), state);
            }
        } else if (command instanceof While whileStatement) {
            while (booleanExpressionInterpreter.evaluate(whileStatement.condition(), state)) {
                execute(whileStatement.body(), state);
            }
        } else {
            throw new IllegalArgumentException(command.toString());
        }

    }
}
