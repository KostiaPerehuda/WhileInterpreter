package com.github.kostiaperehuda.whileinterpreter.state;

public interface State {

	long get(String variable) throws UndefinedVariableException;

	void put(String variable, long value);

	String toString();

}