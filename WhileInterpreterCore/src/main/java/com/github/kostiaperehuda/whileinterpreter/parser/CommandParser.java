package com.github.kostiaperehuda.whileinterpreter.parser;

import com.github.kostiaperehuda.whileinterpreter.ast.*;

import java.util.Deque;

public class CommandParser {

    public static Command parseProgram(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Nothing to parse!");
        }

        tokens.addFirst(new Token(TokenType.CONTROL_SYMBOL, "{"));
        tokens.addLast(new Token(TokenType.CONTROL_SYMBOL, "}"));

        return parseSequence(tokens);
    }

    public static Command parse(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Nothing to parse!");
        }

        Token t = tokens.getFirst();

        if (t.data().equals("{")) {
            return parseSequence(tokens);
        }

        return parseCommand(tokens);
    }

    private static Command parseSequence(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error! Unexpected end of input!");
        }

        Token t = tokens.getFirst();
        if (!t.data().equals("{")) {
            throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
        }
        tokens.removeFirst();

        Command sequence = new Skip();

        while (!tokens.isEmpty()) {
            t = tokens.getFirst();

            if (t.data().equals("}")) {
                tokens.removeFirst();
                return sequence;
            }

            sequence = new Sequence(sequence, parse(tokens));
        }

        throw new RuntimeException("Syntax error after token " + t + "! Missing closing bracket '}'!");
    }

    private static Command parseCommand(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error! Unexpected end of input!");
        }

        Token t = tokens.getFirst();
        Command cmd;

        switch (t.type()) {
            case IDENTIFIER -> cmd = parseAssign(tokens);
            case KEYWORD -> {
                switch (t.data()) {
                    case "if" -> cmd = parseIf(tokens);
                    case "while" -> cmd = parseWhile(tokens);
                    case "skip" -> {
                        tokens.removeFirst();
                        cmd = new Skip();
                    }
                    default ->
                            throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
                }
            }
            default -> throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
        }

        if (tokens.getFirst().data().equals(";")) tokens.removeFirst();

        return cmd;
    }

    private static Command parseAssign(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error! Unexpected end of input!");
        }

        Token t = tokens.removeFirst();
        if (t.type() != TokenType.IDENTIFIER) {
            throw new RuntimeException("Syntax error at token " + t + "! IDENTIFIER expected, but got" + t.type() + "!");
        }

        String varName = t.data();

        t = tokens.removeFirst();
        if (!t.data().equals("=")) {
            throw new RuntimeException("Syntax error at token " + t + "! < CONTROL_SYMBOL = > expected!");
        }

        return new Assign(new Variable(varName), AexpParser.parse(tokens));
    }

    private static Command parseIf(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error! Unexpected end of input!");
        }

        Token t = tokens.removeFirst();
        if (!t.data().equals("if")) {
            throw new RuntimeException("Syntax error at token " + t + "! < KEYWORD if > expected!");
        }

        BooleanExpression condition = BexpParser.parseBrackets(tokens);

        Command ifClause = parse(tokens);

        t = tokens.getFirst();
        if (!t.data().equals("else")) {
            return new If(condition, ifClause, new Skip());
        }

        tokens.removeFirst();
        return new If(condition, ifClause, parse(tokens));
    }

    private static Command parseWhile(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error! Unexpected end of input!");
        }

        Token t = tokens.removeFirst();
        if (!t.data().equals("while"))
            throw new RuntimeException("Syntax error at token " + t + "! < KEYWORD while > expected!");

        return new While(BexpParser.parseBrackets(tokens), parse(tokens));
    }

}
