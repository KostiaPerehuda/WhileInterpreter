package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.*;
import com.github.kostiaperehuda.whileinterpreter.state.State;

public class ArithmeticExpressionInterpreter {

    public long evaluate(ArithmeticExpression expression, State state) {
        if (expression instanceof Const constant) {
            return constant.number();
        } else if (expression instanceof Variable variable) {
            return state.get(variable.name());
        } else if (expression instanceof Plus plus) {
            return evaluate(plus.left(), state) + evaluate(plus.right(), state);
        } else if (expression instanceof Minus minus) {
            return evaluate(minus.left(), state) - evaluate(minus.right(), state);
        } else if (expression instanceof Times times) {
            return evaluate(times.left(), state) * evaluate(times.right(), state);
        } else {
            throw new IllegalArgumentException(expression.toString());
        }
    }

}
