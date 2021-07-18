package com.example.whileinterpreter.parser.lexer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TokenType {

	CONTROL_SYMBOL("[=;()<!&+-]|\\*"),
	KEYWORD("skip|if|then|else|while|do|true|false"),
	IDENTIFIER("[a-zA-Z_][a-zA-Z_0-9]*"),
	NUMBER("\\d+"),
	WHITESPACE("\\p{javaWhitespace}+");

	private String pattern;

	private TokenType(String pattern) {
		this.pattern = pattern;
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
