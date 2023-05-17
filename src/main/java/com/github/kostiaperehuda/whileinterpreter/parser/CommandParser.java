package com.github.kostiaperehuda.whileinterpreter.parser;

import com.github.kostiaperehuda.whileinterpreter.ast.bexp.BooleanExpression;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.*;

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

        if (t.getData().equals("{")) {
            return parseSequence(tokens);
        }

        return parseCommand(tokens);
    }

    private static Command parseSequence(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error! Unexpected end of input!");
        }

        Token t = tokens.getFirst();
        if (!t.getData().equals("{")) {
            throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
        }
        tokens.removeFirst();

        Command sequence = new Sequence();

        while (!tokens.isEmpty()) {
            t = tokens.getFirst();

            if (t.getData().equals("}")) {
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

        switch (t.getType()) {
            case IDENTIFIER -> cmd = parseAssign(tokens);
            case KEYWORD -> {
                switch (t.getData()) {
                    case "if" -> cmd = parseIf(tokens);
                    case "while" -> cmd = parseWhile(tokens);
                    case "skip" -> {
                        tokens.removeFirst();
                        cmd = new Sequence();
                    }
                    default ->
                            throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
                }
            }
            default -> throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
        }

        if (tokens.getFirst().getData().equals(";")) tokens.removeFirst();

        return cmd;
    }

    private static Command parseAssign(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error! Unexpected end of input!");
        }

        Token t = tokens.removeFirst();
        if (t.getType() != TokenType.IDENTIFIER) {
            throw new RuntimeException("Syntax error at token " + t + "! IDENTIFIER expected, but got" + t.getType() + "!");
        }

        String varName = t.getData();

        t = tokens.removeFirst();
        if (!t.getData().equals("=")) {
            throw new RuntimeException("Syntax error at token " + t + "! < CONTROL_SYMBOL = > expected!");
        }

        return new Assign(varName, AexpParser.parse(tokens));
    }

    private static Command parseIf(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error! Unexpected end of input!");
        }

        Token t = tokens.removeFirst();
        if (!t.getData().equals("if")) {
            throw new RuntimeException("Syntax error at token " + t + "! < KEYWORD if > expected!");
        }

        BooleanExpression condition = BexpParser.parseBrackets(tokens);

        Command ifClause = parse(tokens);

        t = tokens.getFirst();
        if (!t.getData().equals("else")) {
            return new If(condition, ifClause, new Sequence());
        }

        tokens.removeFirst();
        return new If(condition, ifClause, parse(tokens));
    }

    private static Command parseWhile(Deque<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Syntax error! Unexpected end of input!");
        }

        Token t = tokens.removeFirst();
        if (!t.getData().equals("while"))
            throw new RuntimeException("Syntax error at token " + t + "! < KEYWORD while > expected!");

        return new While(BexpParser.parseBrackets(tokens), parse(tokens));
    }

}
