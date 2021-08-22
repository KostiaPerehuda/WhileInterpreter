package com.example.whileinterpreter.parser;

import java.util.LinkedList;

import com.example.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.example.whileinterpreter.ast.bexp.And;
import com.example.whileinterpreter.ast.bexp.Bool;
import com.example.whileinterpreter.ast.bexp.BooleanExpression;
import com.example.whileinterpreter.ast.bexp.Equals;
import com.example.whileinterpreter.ast.bexp.LessThanOrEqual;
import com.example.whileinterpreter.ast.bexp.Not;

public class BexpParser {

	public static BooleanExpression parse(LinkedList<Token> tokens) {
		if (tokens.isEmpty()) {
			throw new RuntimeException("Nothing to parse!");
		}
		return parseAnd(tokens);
	}

	private static BooleanExpression parseAnd(LinkedList<Token> tokens) {
		Token t;
		BooleanExpression exp = parseAtom(tokens);

		while (!tokens.isEmpty()) {
			t = tokens.getFirst();

			if (t.getData().equals("and")) {
				tokens.removeFirst();
				exp = new And(exp, parseAtom(tokens));
			} else {
				break;
			}
		}
		return exp;
	}

	private static BooleanExpression parseAtom(LinkedList<Token> tokens) {
		if (tokens.isEmpty()) {
			throw new RuntimeException("Syntax error!");
		}

		Token t = tokens.getFirst();
		ArithmeticExpression exp;

		switch (t.getType()) {
			case KEYWORD:
				if (t.getData().equals("true")) {
					tokens.removeFirst();
					return Bool.TRUE;
				} else if (t.getData().equals("false")) {
					tokens.removeFirst();
					return Bool.FALSE;
				} else if (t.getData().equals("not")) {
					tokens.removeFirst();
					return new Not(parseAtom(tokens));
				}

				throw new RuntimeException("Syntax error!");

			case CONTROL_SYMBOL:
				if (t.getData().equals("(")) {
					return parseBrackets(tokens);
				}

				throw new RuntimeException("Syntax error!");

			default:
				exp = AexpParser.parse(tokens);
				if (tokens.isEmpty()) {
					throw new RuntimeException("Syntax error!");
				}
				t = tokens.getFirst();

				if (t.getData().equals("==")) {
					tokens.removeFirst();
					return new Equals(exp, AexpParser.parse(tokens));
				} else if (t.getData().equals("<=")) {
					tokens.removeFirst();
					return new LessThanOrEqual(exp, AexpParser.parse(tokens));
				}

				throw new RuntimeException("Syntax error!");
		}
	}

	public static BooleanExpression parseBrackets(LinkedList<Token> tokens) {
		Token t = tokens.getFirst();
		if (t.getType() != TokenType.CONTROL_SYMBOL || !t.getData().equals("(")) {
			throw new RuntimeException("Syntax error!");
		}

		tokens.removeFirst();
		BooleanExpression exp = parse(tokens);

		if (tokens.isEmpty()) {
			throw new RuntimeException("Syntax error!");
		}

		t = tokens.getFirst();
		if (t.getType() != TokenType.CONTROL_SYMBOL || !t.getData().equals(")")) {
			throw new RuntimeException("Syntax error!");
		}

		tokens.removeFirst();
		return exp;
	}

}
