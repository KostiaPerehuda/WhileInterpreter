package com.example.whileinterpreter.parser;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.example.whileinterpreter.ast.bexp.BooleanExpression;
import com.example.whileinterpreter.ast.cmd.Assign;
import com.example.whileinterpreter.ast.cmd.Command;
import com.example.whileinterpreter.ast.cmd.If;
import com.example.whileinterpreter.ast.cmd.Sequence;
import com.example.whileinterpreter.ast.cmd.While;

public class CommandParser {

	public static Command parseProgram(LinkedList<Token> tokens) {
		if (tokens.isEmpty()) {
			throw new RuntimeException("Nothing to parse!");
		}

		tokens.addFirst(new Token(TokenType.CONTROL_SYMBOL, "{"));
		tokens.addLast(new Token(TokenType.CONTROL_SYMBOL, "}"));

		return parseSequence(tokens);
	}

	public static Command parse(LinkedList<Token> tokens) {
		if (tokens.isEmpty()) {
			throw new RuntimeException("Nothing to parse!");
		}

		Token t = tokens.getFirst();

		if (t.getData().equals("{")) {
			return parseSequence(tokens);
		}

		return parseCommand(tokens);
	}

	private static Command parseSequence(LinkedList<Token> tokens) {
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

	private static Command parseCommand(LinkedList<Token> tokens) {
		if (tokens.isEmpty()) {
			throw new RuntimeException("Syntax error! Unexpected end of input!");
		}

		Token t = tokens.getFirst();

		switch (t.getType()) {
		case IDENTIFIER:
			return parseAssign(tokens);

		case KEYWORD:
			if (t.getData().equals("if")) {
				return parseIf(tokens);
			} else if (t.getData().equals("while")) {
				return parseWhile(tokens);
			} else if (t.getData().equals("skip")) {
				tokens.removeFirst();
				return new Sequence();
			}

		default:
			throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
		}
	}

	private static Command parseAssign(LinkedList<Token> tokens) {
		if (tokens.isEmpty()) {
			throw new RuntimeException("Syntax error! Unexpected end of input!");
		}

		Token t = tokens.removeFirst();
		if (t.getType() != TokenType.IDENTIFIER) {
			throw new RuntimeException(
					"Syntax error at token " + t + "! IDENTIFIER expected, but got" + t.getType() + "!");
		}

		String varName = t.getData();

		t = tokens.removeFirst();
		if (!t.getData().equals("=")) {
			throw new RuntimeException("Syntax error at token " + t + "! < CONTROL_SYMBOL = > expected!");
		}

		return new Assign(varName, AexpParser.parse(tokens));
	}

	private static Command parseIf(LinkedList<Token> tokens) {
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

	private static Command parseWhile(LinkedList<Token> tokens) {

		try {
			Token t = tokens.removeFirst();
			if (!t.getData().equals("while"))
				throw new RuntimeException("Syntax error at token " + t + "! < KEYWORD while > expected!");

			return new While(BexpParser.parseBrackets(tokens), parse(tokens));

		} catch (NoSuchElementException e) {
			throw new RuntimeException("Syntax error! Unexpected end of input!");
		}
	}

}
