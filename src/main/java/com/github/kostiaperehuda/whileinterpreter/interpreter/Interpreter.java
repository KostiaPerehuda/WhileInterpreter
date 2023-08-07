package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.github.kostiaperehuda.whileinterpreter.ast.aexp.Const;
import com.github.kostiaperehuda.whileinterpreter.ast.aexp.Minus;
import com.github.kostiaperehuda.whileinterpreter.ast.aexp.Plus;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Assign;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Command;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Skip;
import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.math.BigInteger;

public class Interpreter {

    public void execute(Command command, State state) {
        if (command instanceof Skip) return;

        if (command instanceof Assign assign) {
            state.put(assign.variableName(), evaluate(assign.expression()));
        }
    }

    private BigInteger evaluate(ArithmeticExpression expression) {
        if (expression instanceof Const constant) {
            return constant.number();
        } else if (expression instanceof Plus plus) {
            return evaluate(plus.left()).add(evaluate(plus.right()));
        } else if (expression instanceof Minus minus) {
            return evaluate(minus.left()).subtract(evaluate(minus.right()));
        } else {
            throw new IllegalArgumentException(expression.toString());
        }
    }

    public static void run(String[] args) {
        /* TODO: implement */
    }

}
