package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

public class Const implements ArithmeticExpression {

    private long number;

    public Const(long number) {
        this.number = number;
    }

    @Override
    public long eval(State state) {
        return number;
    }

}
