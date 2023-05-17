package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.ast.bexp.BooleanExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.util.Objects;

public record If(BooleanExpression condition, Command ifBranch, Command elseBranch) implements Command {

    public If {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(ifBranch);
        Objects.requireNonNull(elseBranch);
    }

    @Override
    public void execute(State state) {
        if (condition.eval(state)) {
            ifBranch.execute(state);
        } else {
            elseBranch.execute(state);
        }
    }

}
