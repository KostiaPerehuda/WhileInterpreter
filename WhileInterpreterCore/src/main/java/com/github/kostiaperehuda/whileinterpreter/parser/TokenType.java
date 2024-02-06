package com.github.kostiaperehuda.whileinterpreter.parser;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TokenType {

    CONTROL_SYMBOL("\\{", "\\}", "<=", "==", "\\*", "&&", "[!=;()+-]"),
    KEYWORD("\\bskip\\b", "\\bif\\b", "\\belse\\b", "\\bwhile\\b", "\\btrue\\b", "\\bfalse\\b"),
    IDENTIFIER("\\b[a-zA-Z_][a-zA-Z_0-9]*\\b"),
    NUMBER("\\b\\d+\\b"),

    COMMENT("//.*$"),
    WHITESPACE("\\p{javaWhitespace}+"),

    INVALID_TOKEN(".+?(?=" + choiceBetween(CONTROL_SYMBOL, WHITESPACE) + "|$)");

    private String pattern;

    TokenType(String pattern, String... patterns) {
        this.pattern = pattern;
        if (patterns.length > 0) {
            this.pattern += ("|" + String.join("|", patterns));
        }
    }

    public String getPattern() {
        return pattern;
    }

    public static String getFullPattern() {
        return choiceBetween(values());
    }

    private static String choiceBetween(TokenType... tokens) {
        return Stream.of(tokens)
                .map(TokenType::getPattern)
                .collect(Collectors.joining("|"));
    }

}
