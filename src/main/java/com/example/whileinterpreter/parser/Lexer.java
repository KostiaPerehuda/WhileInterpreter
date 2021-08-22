package com.example.whileinterpreter.parser;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

	public static LinkedList<Token> lex(String input) {
		LinkedList<Token> tokens = new LinkedList<Token>();

		Pattern p = Pattern.compile(TokenType.getFullPattern());
		Matcher m = p.matcher(input);
		String group;

		while (true) {
			if (!m.find()) {
				if (m.hitEnd()) break;
				else throw new RuntimeException("Unexpected Symbol!");
			}

			for (TokenType t : TokenType.values()) {

				group = m.group();

				if (group.matches(t.getPattern())) {
					if (t == TokenType.INVALID_TOKEN)
						throw new RuntimeException("Lexical Error at " + group + "!");

					if (t != TokenType.WHITESPACE)
						tokens.add(new Token(t, group));

					break;
				}
			}
		}
		return tokens;
	}

}
