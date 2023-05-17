package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.state.State;

public record Skip() implements Command {
    @Override
    public void execute(State state) {
    }
}
