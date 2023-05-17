package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;

public class Assign implements Command {

    private String name;
    private ArithmeticExpression exp;

    public Assign(String name, ArithmeticExpression exp) {
        this.name = name;
        this.exp = exp;
    }

    @Override
    public void execute(State state) {
        state.put(name, exp.eval(state));
    }

}
