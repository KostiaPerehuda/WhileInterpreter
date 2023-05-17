package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.util.Objects;

public record Variable(String name) implements ArithmeticExpression {

    public Variable {
        Objects.requireNonNull(name);
        if (name.isBlank()) {
            throw new IllegalArgumentException(name);
        }
    }

    @Override
    public long eval(State state) {
        return state.get(name);
    }

}
