package com.example.whileinterpreter.state;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

public class State {
	
	public static final State GLOBALS = new State();
	
	private Map<String, Long> map = new HashMap<String, Long>();
	
	public State() {	
	}
	
	public long get(String var) {
		if (!map.containsKey(var)) {
			throw new UndefinedVariableException("Variable " + var + " is not defined!");
		}
		return map.get(var);
	}
	
	public void put(String var, long value) {
		map.put(var, value);
	}
	
	@Override
	public String toString() {
		if (map.isEmpty()) {
			return "{}";
		}
		return map.entrySet().stream()
				.map(e -> "\t" + e.getKey() + " : " + e.getValue() + ";")
				.collect(Collectors.joining("\n", "{\n", "\n}"));
	}
}
