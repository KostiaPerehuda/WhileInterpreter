package com.github.kostiaperehuda.whileinterpreter.parser;

import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private static Pattern pattern = Pattern.compile(TokenType.getFullPattern());

    public static Deque<Token> lex(String input) {
        return lex(input.split("\n"));
    }

    private static Deque<Token> lex(String... input) {
        LinkedList<Token> tokens = new LinkedList<>();

        Matcher m;
        String group;

        for (int row = 0; row < input.length; row++) {
            m = pattern.matcher(input[row]);

            while (true) {
                if (!m.find()) {
                    if (m.hitEnd()) break;
                    else throw new LexicalError("Unexpected Symbol at row " + (row + 1) + "!");
                }

                for (TokenType t : TokenType.values()) {

                    group = m.group();

                    if (group.matches(t.getPattern())) {
                        if (t == TokenType.INVALID_TOKEN)
                            throw new LexicalError(row + 1, m.start() + 1, group);

                        if (t != TokenType.WHITESPACE && t != TokenType.COMMENT)
                            tokens.add(new Token(t, group));

                        break;
                    }
                }
            }
        }
        return tokens;
    }

}
