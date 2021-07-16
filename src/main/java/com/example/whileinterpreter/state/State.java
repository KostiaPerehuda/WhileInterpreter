package com.example.whileinterpreter.state;

public interface State {

	public long get(String var) throws UndefinedVariableException;

	public void put(String var, long value);

	public String toString();

}