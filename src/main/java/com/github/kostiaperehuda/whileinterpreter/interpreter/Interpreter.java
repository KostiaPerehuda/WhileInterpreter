package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.*;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Assign;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Command;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Sequence;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Skip;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Interpreter {

    private final Map<String, BigInteger> state;

    public Interpreter() {
        this(new HashMap<>());
    }

    public Interpreter(Map<String, BigInteger> state) {
        this.state = state;
    }

    public Map<String, BigInteger> execute(Command command) {
        if (command instanceof Skip) {
            return state;
        }
        if (command instanceof Assign assign) {
            state.put(assign.variableName(), evaluate(assign.expression()));
            return state;
        }
        if (command instanceof Sequence sequence) {
            execute(sequence.first());
            execute(sequence.second());
            return state;
        }
        return state;
    }

    private BigInteger evaluate(ArithmeticExpression expression) {
        if (expression instanceof Const constant) {
            return constant.number();
        } else if (expression instanceof Variable variable) {
            return Optional.ofNullable(state.get(variable.name())).orElseThrow(() ->
                    new UndefinedVariableException(variable.name()));
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
