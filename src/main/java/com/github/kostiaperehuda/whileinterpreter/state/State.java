package com.github.kostiaperehuda.whileinterpreter.state;

import java.math.BigInteger;

public interface State {

    BigInteger get(String variable) throws UndefinedVariableException;

    void put(String variable, BigInteger value);

    String toString();

}