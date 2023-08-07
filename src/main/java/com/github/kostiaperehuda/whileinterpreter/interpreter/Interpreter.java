package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.*;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Assign;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Command;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Sequence;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Skip;
import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.math.BigInteger;

public class Interpreter {

    private State state;

    public void execute(Command command, State state) {
        if (command instanceof Skip) return;

        this.state = state;

        if (command instanceof Assign assign) {
            state.put(assign.variableName(), evaluate(assign.expression()));
        } else if (command instanceof Sequence sequence) {
            execute(sequence.first(), state);
            execute(sequence.second(), state);
        }
    }

    private BigInteger evaluate(ArithmeticExpression expression) {
        if (expression instanceof Const constant) {
            return constant.number();
        } else if (expression instanceof Variable variable) {
            return state.get(variable.name());
        } else if (expression instanceof Plus plus) {
            return evaluate(plus.left()).add(evaluate(plus.right()));
        } else if (expression instanceof Minus minus) {
            return evaluate(minus.left()).subtract(evaluate(minus.right()));
        } else if (expression instanceof Multiply multiply) {
            return evaluate(multiply.left()).multiply(evaluate(multiply.right()));
        } else {
            throw new IllegalArgumentException(expression.toString());
        }
    }

    public static void run(String[] args) {
        /* TODO: implement */
    }

}
