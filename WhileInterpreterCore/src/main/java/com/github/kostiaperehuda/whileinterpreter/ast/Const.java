package com.github.kostiaperehuda.whileinterpreter.ast;

import java.math.BigInteger;

public record Const(BigInteger number) implements ArithmeticExpression {}
