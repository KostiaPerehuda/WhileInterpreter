package com.example.whileinterpreter.parser;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TokenType {

	CONTROL_SYMBOL("\\{", "\\}", "<=", "==", "\\*", "[=;()+-]"),
	KEYWORD("skip", "if", "then", "else", "while", "do", "true", "false", "and", "not"),
	IDENTIFIER("[a-zA-Z_][a-zA-Z_0-9]*"),
	NUMBER("\\d+"),
	WHITESPACE("\\p{javaWhitespace}+"),
	INVALID_TOKEN(".");

	private String pattern;

	private TokenType(String pattern, String... patterns) {
		this.pattern = pattern;
		if (patterns.length > 0) {
			this.pattern += ("|" + Stream.of(patterns).collect(Collectors.joining("|")));
		}
	}

	public String getPattern() {
		return pattern;
	}
	
	public static String getFullPattern() {
		return Stream.of(TokenType.values())
				.map(t -> "(" + t.getPattern() + ")")
				.collect(Collectors.joining("|"));
	}

}
