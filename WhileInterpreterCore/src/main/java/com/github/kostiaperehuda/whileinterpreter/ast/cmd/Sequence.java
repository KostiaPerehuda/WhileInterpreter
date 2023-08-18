package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import java.util.Objects;

public record Sequence(Command first, Command second) implements Command {

    public Sequence {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
    }

}
