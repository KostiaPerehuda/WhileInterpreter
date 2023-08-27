package com.github.kostiaperehuda.whileinterpreter.ast;

public sealed interface ArithmeticExpression permits Const, Variable, Plus, Minus, Multiply {}
