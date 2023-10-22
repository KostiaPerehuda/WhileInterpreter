package com.github.kostiaperehuda.whileinterpreter.gui.viewmodels;

import java.util.Map;

public class Pair<K, V> {

    private final K key;

    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public static <K, V> Pair<K, V> fromMapEntry(Map.Entry<K, V> entry) {
        return new Pair<>(entry.getKey(), entry.getValue());
    }

}
