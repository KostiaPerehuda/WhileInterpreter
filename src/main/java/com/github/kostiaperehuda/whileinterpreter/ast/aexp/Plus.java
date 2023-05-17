package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

public class Plus extends BinaryArithmeticExpression {

    public Plus(ArithmeticExpression exp1, ArithmeticExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    public long eval(State state) {
        return getExp1().eval(state) + getExp2().eval(state);
    }

}
