package com.github.kostiaperehuda.whileinterpreter.ast;

import java.util.Objects;

public record If(BooleanExpression condition, Command ifBranch, Command elseBranch) implements Command {

    public If {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(ifBranch);
        Objects.requireNonNull(elseBranch);
    }

}
