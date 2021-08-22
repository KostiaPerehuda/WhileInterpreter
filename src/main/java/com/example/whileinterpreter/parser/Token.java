package com.example.whileinterpreter.parser;

public class Token {

	private TokenType type;
	private String data;

	public Token(TokenType type, String data) {
		this.type = type;
		this.data = data;
	}

	public TokenType getType() {
		return type;
	}

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return String.format("< %s %s >", type.name(), data);
	}

}
