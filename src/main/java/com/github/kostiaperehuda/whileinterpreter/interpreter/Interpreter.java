package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.*;
import com.github.kostiaperehuda.whileinterpreter.ast.bexp.Bool;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.*;

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
        if (command instanceof If ifStatement) {
            var condition = ifStatement.condition();

            if (condition instanceof Bool bool) {
                switch (bool) {
                    case TRUE -> execute(ifStatement.ifBranch());
                    case FALSE -> execute(ifStatement.elseBranch());
                }
            }
            return state;
        }
        throw new IllegalArgumentException(state.toString());
    }

    private BigInteger evaluate(ArithmeticExpression expression) {
        if (expression instanceof Const constant) {
            return constant.number();
        }
        if (expression instanceof Variable variable) {
            return Optional.ofNullable(state.get(variable.name())).orElseThrow(() ->
                    new UndefinedVariableException(variable.name()));
        }
        if (expression instanceof Plus plus) {
            return evaluate(plus.left()).add(evaluate(plus.right()));
        }
        if (expression instanceof Minus minus) {
            return evaluate(minus.left()).subtract(evaluate(minus.right()));
        }
        if (expression instanceof Multiply multiply) {
            return evaluate(multiply.left()).multiply(evaluate(multiply.right()));
        }
        throw new IllegalArgumentException(expression.toString());
    }

    public static void run(String[] args) {
        /* TODO: implement */
    }

}
