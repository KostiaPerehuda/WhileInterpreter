package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

public sealed interface Command permits Assign, Sequence, If, While, Skip {}
