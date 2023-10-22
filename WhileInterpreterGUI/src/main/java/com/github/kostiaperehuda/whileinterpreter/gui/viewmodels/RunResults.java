package com.github.kostiaperehuda.whileinterpreter.gui.viewmodels;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigInteger;
import java.util.Map;

public class RunResults {

    private final ObjectProperty<ObservableList<Pair<String, BigInteger>>> state =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());

    public ObjectProperty<ObservableList<Pair<String, BigInteger>>> stateProperty() {
        return state;
    }

    public void set(Map<String, BigInteger> result) {
        state.get().setAll(result.entrySet().stream().map(Pair::fromMapEntry).toList());
    }

}
