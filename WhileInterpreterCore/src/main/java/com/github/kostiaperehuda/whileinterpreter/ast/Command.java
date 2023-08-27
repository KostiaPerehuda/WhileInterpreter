package com.github.kostiaperehuda.whileinterpreter.ast;

public sealed interface Command permits Assign, Sequence, If, While, Skip {}
