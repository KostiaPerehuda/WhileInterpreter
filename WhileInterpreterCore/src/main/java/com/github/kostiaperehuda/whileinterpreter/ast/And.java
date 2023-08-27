package com.github.kostiaperehuda.whileinterpreter.ast;

import java.util.Objects;

public record And(BooleanExpression left, BooleanExpression right) implements BooleanExpression {

    public And {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

}
