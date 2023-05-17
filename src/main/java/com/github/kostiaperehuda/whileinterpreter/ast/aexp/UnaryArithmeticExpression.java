package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

public abstract class UnaryArithmeticExpression implements ArithmeticExpression {

    private ArithmeticExpression exp;

    protected UnaryArithmeticExpression(ArithmeticExpression exp) {
        this.exp = exp;
    }

    public ArithmeticExpression getExp() {
        return exp;
    }

}
