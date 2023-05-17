package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.ast.bexp.BooleanExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.util.Objects;

public record While(BooleanExpression condition, Command body) implements Command {

    public While {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(body);
    }

    @Override
    public void execute(State state) {
        while (condition.eval(state)) {
            body.execute(state);
        }
    }

}
