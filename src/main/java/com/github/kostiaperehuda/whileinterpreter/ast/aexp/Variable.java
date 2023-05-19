package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import java.util.Objects;

public record Variable(String name) implements ArithmeticExpression {

    public Variable {
        Objects.requireNonNull(name);
        if (name.isBlank()) {
            throw new IllegalArgumentException(name);
        }
    }

}
