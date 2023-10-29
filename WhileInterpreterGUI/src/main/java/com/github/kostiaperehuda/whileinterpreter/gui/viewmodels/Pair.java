package com.github.kostiaperehuda.whileinterpreter.gui.viewmodels;

import java.util.Map;

public record Pair<K, V>(K key, V value) {

    public static <K, V> Pair<K, V> fromMapEntry(Map.Entry<K, V> entry) {
        return new Pair<>(entry.getKey(), entry.getValue());
    }

    /**
     * For JavaFX interoperability
     */
    public K getKey() {
        return key;
    }

    /**
     * For JavaFX interoperability
     */
    public V getValue() {
        return value;
    }

}
