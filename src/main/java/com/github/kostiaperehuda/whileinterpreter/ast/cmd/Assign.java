package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.util.Objects;

public record Assign(String variableName, ArithmeticExpression expression) implements Command {

    public Assign {
        Objects.requireNonNull(expression);
        Objects.requireNonNull(variableName);
        if (variableName.isBlank()) {
            throw new IllegalArgumentException(variableName);
        }
    }

    @Override
    public void execute(State state) {
        state.put(variableName, expression.eval(state));
    }

}
