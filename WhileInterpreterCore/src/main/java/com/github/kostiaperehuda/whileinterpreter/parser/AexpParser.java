package com.github.kostiaperehuda.whileinterpreter.parser;

import com.github.kostiaperehuda.whileinterpreter.ast.*;

import java.math.BigInteger;
import java.util.Deque;

public class AexpParser {

    public static ArithmeticExpression parse(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Nothing to parse!");
        }
        return parseExp(tokens);
    }

    private static ArithmeticExpression parseExp(Deque<Token> tokens) {
        Token t;
        ArithmeticExpression exp = parseProduct(tokens);

        while (!tokens.isEmpty()) {
            t = tokens.getFirst();

            if (t.type() != TokenType.CONTROL_SYMBOL) {
                break;
            }

            if (t.data().equals("+")) {
                tokens.removeFirst();
                exp = new Plus(exp, parseProduct(tokens));
            } else if (t.data().equals("-")) {
                tokens.removeFirst();
                exp = new Minus(exp, parseProduct(tokens));
            } else {
                break;
            }
        }
        return exp;
    }

    private static ArithmeticExpression parseProduct(Deque<Token> tokens) {
        Token t;
        ArithmeticExpression exp = parseAtom(tokens);

        while (!tokens.isEmpty()) {
            t = tokens.getFirst();

            if (t.type() != TokenType.CONTROL_SYMBOL) {
                break;
            }

            if (t.data().equals("*")) {
                tokens.removeFirst();
                exp = new Multiply(exp, parseAtom(tokens));
            } else {
                break;
            }
        }
        return exp;
    }

    private static ArithmeticExpression parseAtom(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error! Unexpected end of input!");
        }
        Token t = tokens.getFirst();

        switch (t.type()) {
            case IDENTIFIER:
                tokens.removeFirst();
                return new Variable(t.data());

            case NUMBER:
                tokens.removeFirst();
                return new Const(new BigInteger(t.data()));

            case CONTROL_SYMBOL:
                if (t.data().equals("(")) {
                    return parseBrackets(tokens);
                }

            default:
                throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
        }
    }

    public static ArithmeticExpression parseBrackets(Deque<Token> tokens) {
        Token t = tokens.getFirst();
        if (t.type() != TokenType.CONTROL_SYMBOL || !t.data().equals("(")) {
            throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
        }

        tokens.removeFirst();
        ArithmeticExpression exp = parseExp(tokens);

        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error after token " + t + "! Unexpected end of input!");
        }

        t = tokens.getFirst();
        if (t.type() != TokenType.CONTROL_SYMBOL || !t.data().equals(")")) {
            throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
        }

        tokens.removeFirst();
        return exp;
    }

}
