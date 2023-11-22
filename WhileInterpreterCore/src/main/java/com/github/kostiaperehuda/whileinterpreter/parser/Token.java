package com.github.kostiaperehuda.whileinterpreter.parser;

public record Token(TokenType type, String data) {

    @Override
    public String toString() {
        return String.format("< %s %s >", type.name(), data);
    }

}
