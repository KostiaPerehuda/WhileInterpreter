package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.util.Objects;

public record Sequence(Command left, Command right) implements Command {

    public Sequence {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

    @Override
    public void execute(State state) {
        left.execute(state);
        right.execute(state);
    }

}
