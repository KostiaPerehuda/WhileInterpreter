package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

public sealed interface ArithmeticExpression permits Const, Variable, Plus, Minus, Times {}
