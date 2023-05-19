package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;

import java.util.Objects;

public record Assign(String variableName, ArithmeticExpression expression) implements Command {

    public Assign {
        Objects.requireNonNull(expression);
        Objects.requireNonNull(variableName);
        if (variableName.isBlank()) {
            throw new IllegalArgumentException(variableName);
        }
    }

}
