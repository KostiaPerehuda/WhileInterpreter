package com.example.whileinterpreter.parser;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TokenType {

	CONTROL_SYMBOL("\\{", "\\}", "<=", "==", "\\*", "&&", "[!=;()+-]"),
	KEYWORD("skip", "if", "else", "while", "true", "false"),
	IDENTIFIER("[a-zA-Z_][a-zA-Z_0-9]*"),
	NUMBER("\\d+"),
	WHITESPACE("\\p{javaWhitespace}+"),

	INVALID_TOKEN(null);

	static {
		CONTROL_SYMBOL.makeDelimiter();
		WHITESPACE.makeDelimiter();

		INVALID_TOKEN.pattern = ".+(?=" + computeDelimiterPattern() + "|$)";
	}

	public static final String fullPattern = computeFullPattern();
	public static final TokenType[] values = TokenType.values();

	private String pattern;
	private boolean delimiter;

	private TokenType(String pattern, String... patterns) {
		this.pattern = pattern;
		if (patterns.length > 0) {
			this.pattern += ("|" + Stream.of(patterns).collect(Collectors.joining("|")));
		}
	}

	private void makeDelimiter() {
		delimiter = true;
	}

	private boolean isDelimiter() {
		return delimiter;
	}

	public String getPattern() {
		return pattern;
	}

	public static TokenType fromInt(int ordinal) {
		try {
			return values[ordinal-1];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	private static String computeFullPattern() {
		String delimiter = computeDelimiterPattern();
		String wrappedPatternTemplate = String.format("(?<=^|%s)%s(?=%s|$)", delimiter, "%s", delimiter);

		return Stream.of(TokenType.values())
				.map(t -> {
					if (t.isDelimiter() || t == TokenType.INVALID_TOKEN) {
						return "(" + t.getPattern() + ")";
					}
					return "(" + String.format(wrappedPatternTemplate, t.getPattern()) + ")";
				})
				.collect(Collectors.joining("|"));
	}

	private static String computeDelimiterPattern() {
		return Stream.of(TokenType.values())
				.filter(TokenType::isDelimiter)
				.map(TokenType::getPattern)
				.collect(Collectors.joining("|"));
	}

}
