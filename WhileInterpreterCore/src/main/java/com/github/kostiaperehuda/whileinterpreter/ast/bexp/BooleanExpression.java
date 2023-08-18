package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

public sealed interface BooleanExpression permits Bool, Not, And, Equals, LessThanOrEqual {}
