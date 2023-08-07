package com.github.kostiaperehuda.whileinterpreter.state;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryState implements State {

    private final Map<String, BigInteger> map = new HashMap<>();

    @Override
    public BigInteger get(String variable) throws UndefinedVariableException {
        if (!map.containsKey(variable)) {
            throw new UndefinedVariableException("Variable " + variable + " is not defined!");
        }
        return map.get(variable);
    }

    @Override
    public void put(String variable, BigInteger value) {
        map.put(variable, value);
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
