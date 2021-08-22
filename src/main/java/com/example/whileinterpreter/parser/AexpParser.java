package com.example.whileinterpreter.parser;

import java.util.LinkedList;

import com.example.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.example.whileinterpreter.ast.aexp.Const;
import com.example.whileinterpreter.ast.aexp.Minus;
import com.example.whileinterpreter.ast.aexp.Plus;
import com.example.whileinterpreter.ast.aexp.Times;
import com.example.whileinterpreter.ast.aexp.Var;

public class AexpParser {

	public static ArithmeticExpression parse(LinkedList<Token> tokens) {
		if (tokens.isEmpty()) {
			throw new RuntimeException("Nothing to parse!");
		}
		return parseExp(tokens);
	}

	private static ArithmeticExpression parseExp(LinkedList<Token> tokens) {
		Token t;
		ArithmeticExpression exp = parseProduct(tokens);

		while (!tokens.isEmpty()) {
			t = tokens.getFirst();

			if (t.getType() != TokenType.CONTROL_SYMBOL) {
				break;
			}

			if (t.getData().equals("+")) {
				tokens.removeFirst();
				exp = new Plus(exp, parseProduct(tokens));
			} else if (t.getData().equals("-")) {
				tokens.removeFirst();
				exp = new Minus(exp, parseProduct(tokens));
			} else {
				break;
			}
		}
		return exp;
	}

	private static ArithmeticExpression parseProduct(LinkedList<Token> tokens) {
		Token t;
		ArithmeticExpression exp = parseAtom(tokens);

		while (!tokens.isEmpty()) {
			t = tokens.getFirst();

			if (t.getType() != TokenType.CONTROL_SYMBOL) {
				break;
			}

			if (t.getData().equals("*")) {
				tokens.removeFirst();
				exp = new Times(exp, parseAtom(tokens));
			} else {
				break;
			}
		}
		return exp;
	}

	private static ArithmeticExpression parseAtom(LinkedList<Token> tokens) {
		if (tokens.isEmpty()) {
			throw new RuntimeException("Syntax error! Unexpected end of input!");
		}
		Token t = tokens.getFirst();

		switch (t.getType()) {
			case IDENTIFIER:
				tokens.removeFirst();
				return new Var(t.getData());

			case NUMBER:
				tokens.removeFirst();
				return new Const(Long.valueOf(t.getData()));

			case CONTROL_SYMBOL:
				if (t.getData().equals("(")) {
					return parseBrackets(tokens);
				}

			default:
				throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
		}
	}

	public static ArithmeticExpression parseBrackets(LinkedList<Token> tokens) {
		Token t = tokens.getFirst();
		if (t.getType() != TokenType.CONTROL_SYMBOL || !t.getData().equals("(")) {
			throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
		}

		tokens.removeFirst();
		ArithmeticExpression exp = parseExp(tokens);

		if (tokens.isEmpty()) {
			throw new RuntimeException("Syntax error after token " + t + "! Unexpected end of input!");
		}

		t = tokens.getFirst();
		if (t.getType() != TokenType.CONTROL_SYMBOL || !t.getData().equals(")")) {
			throw new RuntimeException("Syntax error at token " + t + "! Unexpected token at that place!");
		}

		tokens.removeFirst();
		return exp;
	}

}
