package com.github.kostiaperehuda.whileinterpreter.parser;

public class LexicalError extends RuntimeException {

    private static final long serialVersionUID = -3027926033625748490L;

    public LexicalError(String message) {
        super(message);
    }

    public LexicalError(int row, int column, String token) {
        super(String.format("Lexical error at row %d, column %d! Invalid token '%s'!", row, column, token));
    }

}
