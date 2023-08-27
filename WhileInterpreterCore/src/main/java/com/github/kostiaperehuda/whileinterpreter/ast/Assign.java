package com.github.kostiaperehuda.whileinterpreter.ast;

import java.util.Objects;

public record Assign(Variable variable, ArithmeticExpression expression) implements Command {

    public Assign {
        Objects.requireNonNull(expression);
        Objects.requireNonNull(variable);
    }

}
