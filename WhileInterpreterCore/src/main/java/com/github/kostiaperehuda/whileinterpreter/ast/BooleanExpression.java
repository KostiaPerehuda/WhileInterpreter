package com.github.kostiaperehuda.whileinterpreter.ast;

public sealed interface BooleanExpression permits Bool, Not, And, Equals, LessThanOrEqual {}
