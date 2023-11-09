package com.github.kostiaperehuda.whileinterpreter.gui.viewmodels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class RunResults {

    private final ObservableList<RunResult> runResultList = FXCollections.observableArrayList();

    public ObservableList<RunResult> getRunResultList() {
        return runResultList;
    }

    public static class RunResult {

        private final ObservableList<Variable> programState = FXCollections.observableArrayList();

        public RunResult(List<Variable> programState) {
            this.programState.setAll(programState);
        }

        public static RunResult of(Map<String, BigInteger> programState) {
            return new RunResult(programState.entrySet().stream().map(Variable::fromMapEntry).toList());
        }

        public ObservableList<Variable> getProgramState() {
            return FXCollections.unmodifiableObservableList(programState);
        }

    }

    public record Variable(String name, BigInteger value) {

        private static Variable fromMapEntry(Map.Entry<String, BigInteger> entry) {
            return new Variable(entry.getKey(), entry.getValue());
        }

        /**
         * For JavaFX interoperability
         */
        public String getName() {
            return name;
        }

        /**
         * For JavaFX interoperability
         */
        public BigInteger getValue() {
            return value;
        }

    }

}
