package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import java.math.BigInteger;

public record Const(BigInteger number) implements ArithmeticExpression {}
