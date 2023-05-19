package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import java.util.Objects;

public record Sequence(Command left, Command right) implements Command {

    public Sequence {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

}
