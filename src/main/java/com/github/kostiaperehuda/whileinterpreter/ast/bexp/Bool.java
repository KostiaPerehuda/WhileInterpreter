package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

public enum Bool implements BooleanExpression {

    TRUE(true), FALSE(false);

    private final boolean value;

    Bool(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return value;
    }

    @Override
    public boolean eval(State state) {
        return value;
    }

}
