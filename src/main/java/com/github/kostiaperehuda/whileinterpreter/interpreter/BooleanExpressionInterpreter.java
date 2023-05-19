package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.bexp.*;
import com.github.kostiaperehuda.whileinterpreter.state.State;

public class BooleanExpressionInterpreter {

    private final ArithmeticExpressionInterpreter aexpInterpreter;

    public BooleanExpressionInterpreter(ArithmeticExpressionInterpreter aexpInterpreter) {
        this.aexpInterpreter = aexpInterpreter;
    }

    public BooleanExpressionInterpreter() {
        this(new ArithmeticExpressionInterpreter());
    }

    public boolean evaluate(BooleanExpression expression, State state) {
        if (expression instanceof Bool bool) {
            return bool.value();
        } else if (expression instanceof Not not) {
            return !evaluate(not.operand(), state);
        } else if (expression instanceof And and) {
            return evaluate(and.left(), state) && evaluate(and.right(), state);
        } else if (expression instanceof Equals equals) {
            return aexpInterpreter.evaluate(equals.left(), state) == aexpInterpreter.evaluate(equals.right(), state);
        } else if (expression instanceof LessThanOrEqual lessThanOrEqual) {
            return aexpInterpreter.evaluate(lessThanOrEqual.left(), state) <= aexpInterpreter.evaluate(lessThanOrEqual.right(), state);
        } else {
            throw new IllegalArgumentException(expression.toString());
        }
    }

}
