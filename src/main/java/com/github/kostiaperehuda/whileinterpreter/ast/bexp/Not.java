package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import java.util.Objects;

public record Not(BooleanExpression operand) implements BooleanExpression {

    public Not {
        Objects.requireNonNull(operand);
    }

}
