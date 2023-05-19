package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.bexp.BooleanExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;

public class BooleanExpressionInterpreter {

    private final ArithmeticExpressionInterpreter arithmeticExpressionInterpreter;

    public BooleanExpressionInterpreter(ArithmeticExpressionInterpreter arithmeticExpressionInterpreter) {
        this.arithmeticExpressionInterpreter = arithmeticExpressionInterpreter;
    }

    public BooleanExpressionInterpreter() {
        this(new ArithmeticExpressionInterpreter());
    }

    public boolean evaluate(BooleanExpression expression, State state) {
        return false;
    }

}
