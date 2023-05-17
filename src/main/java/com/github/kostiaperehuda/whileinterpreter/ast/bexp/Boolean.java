package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

public enum Boolean implements BooleanExpression {

    TRUE(true), FALSE(false);

    private final boolean value;

    Boolean(boolean value) {
        this.value = value;
    }

    @Override
    public boolean eval(State state) {
        return value;
    }

}
