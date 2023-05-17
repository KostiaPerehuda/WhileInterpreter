package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.ast.bexp.BooleanExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;

public class If implements Command {

    private BooleanExpression bool;
    private Command cmd1;
    private Command cmd2;

    public If(BooleanExpression bool, Command cmd1, Command cmd2) {
        this.bool = bool;
        this.cmd1 = cmd1;
        this.cmd2 = cmd2;
    }

    @Override
    public void execute(State state) {
        if (bool.eval(state)) {
            cmd1.execute(state);
        } else {
            cmd2.execute(state);
        }
    }

}
