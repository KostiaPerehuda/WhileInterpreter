package com.github.kostiaperehuda.whileinterpreter.state;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryState implements State {

    private Map<String, Long> map;

    public InMemoryState() {
        map = new HashMap<>();
    }

    @Override
    public long get(String variable) throws UndefinedVariableException {
        if (!map.containsKey(variable)) {
            throw new UndefinedVariableException("Variable " + variable + " is not defined!");
        }
        return map.get(variable);
    }

    @Override
    public void put(String variable, long value) {
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
