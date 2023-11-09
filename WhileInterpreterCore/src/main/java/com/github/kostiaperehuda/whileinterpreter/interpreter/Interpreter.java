package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    private final Map<String, BigInteger> state;

    public Interpreter() {
        this(new HashMap<>());
    }

    public Interpreter(Map<String, BigInteger> state) {
        this.state = state;
    }

    public Map<String, BigInteger> execute(Command command) {
        switch (command) {
            case Skip ignored -> {
            }
            case Assign(var variable, var expression) -> {
                state.put(variable.name(), evaluate(expression));
            }
            case Sequence(var first, var second) -> {
                execute(first);
                execute(second);
            }
            case If(var condition, var ifBranch, var elseBranch) -> {
                execute(evaluate(condition) ? ifBranch : elseBranch);
            }
            case While(var condition, var body) -> {
                while (evaluate(condition)) {
                    execute(body);
                }
            }
        }
        return state;
    }

    private boolean evaluate(BooleanExpression expression) {
        return switch (expression) {
            case Bool bool -> switch (bool) {
                case TRUE -> true;
                case FALSE -> false;
            };
            case Not(var operand) -> !evaluate(operand);
            case And(var left, var right) -> evaluate(left) && evaluate(right);
            case Equals(var left, var right) -> evaluate(left).compareTo(evaluate(right)) == 0;
            case LessThanOrEqual(var left, var right) -> evaluate(left).compareTo(evaluate(right)) <= 0;
        };
    }

    private BigInteger evaluate(ArithmeticExpression expression) {
        return switch (expression) {
            case Const(var number) -> number;
            case Plus(var left, var right) -> evaluate(left).add(evaluate(right));
            case Minus(var left, var right) -> evaluate(left).subtract(evaluate(right));
            case Multiply(var left, var right) -> evaluate(left).multiply(evaluate(right));
            case Variable(var name) -> state.computeIfAbsent(name, unused -> BigInteger.ZERO);
        };
    }

}
