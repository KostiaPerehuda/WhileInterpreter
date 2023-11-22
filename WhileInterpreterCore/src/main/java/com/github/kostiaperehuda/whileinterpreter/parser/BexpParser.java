package com.github.kostiaperehuda.whileinterpreter.parser;

import com.github.kostiaperehuda.whileinterpreter.ast.*;

import java.util.Deque;

public class BexpParser {

    public static BooleanExpression parse(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Nothing to parse!");
        }
        return parseAnd(tokens);
    }

    private static BooleanExpression parseAnd(Deque<Token> tokens) {
        Token t;
        BooleanExpression exp = parseAtom(tokens);

        while (!tokens.isEmpty()) {
            t = tokens.getFirst();

            if (t.data().equals("&&")) {
                tokens.removeFirst();
                exp = new And(exp, parseAtom(tokens));
            } else {
                break;
            }
        }
        return exp;
    }

    private static BooleanExpression parseAtom(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error!");
        }

        Token t = tokens.getFirst();
        ArithmeticExpression exp;

        switch (t.type()) {
            case KEYWORD:
                if (t.data().equals("true")) {
                    tokens.removeFirst();
                    return Bool.TRUE;
                } else if (t.data().equals("false")) {
                    tokens.removeFirst();
                    return Bool.FALSE;
                }

                throw new RuntimeException("Syntax error!");

            case CONTROL_SYMBOL:
                if (t.data().equals("(")) {
                    return parseBrackets(tokens);
                } else if (t.data().equals("!")) {
                    tokens.removeFirst();
                    return new Not(parseAtom(tokens));
                }

                throw new RuntimeException("Syntax error!");

            default:
                exp = AexpParser.parse(tokens);
                if (tokens.isEmpty()) {
                    throw new RuntimeException("Syntax error!");
                }
                t = tokens.getFirst();

                if (t.data().equals("==")) {
                    tokens.removeFirst();
                    return new Equals(exp, AexpParser.parse(tokens));
                } else if (t.data().equals("<=")) {
                    tokens.removeFirst();
                    return new LessThanOrEqual(exp, AexpParser.parse(tokens));
                }

                throw new RuntimeException("Syntax error!");
        }
    }

    public static BooleanExpression parseBrackets(Deque<Token> tokens) {
        Token t = tokens.getFirst();
        if (t.type() != TokenType.CONTROL_SYMBOL || !t.data().equals("(")) {
            throw new RuntimeException("Syntax error!");
        }

        tokens.removeFirst();
        BooleanExpression exp = parse(tokens);

        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error!");
        }

        t = tokens.getFirst();
        if (t.type() != TokenType.CONTROL_SYMBOL || !t.data().equals(")")) {
            throw new RuntimeException("Syntax error!");
        }

        tokens.removeFirst();
        return exp;
    }

}
