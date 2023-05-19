package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

public enum Bool implements BooleanExpression {

    TRUE(true), FALSE(false);

    private final boolean value;

    Bool(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return value;
    }

}
