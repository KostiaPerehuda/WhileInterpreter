package com.github.kostiaperehuda.whileinterpreter.ast;

import java.util.Objects;

public record While(BooleanExpression condition, Command body) implements Command {

    public While {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(body);
    }

}
