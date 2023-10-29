package com.github.kostiaperehuda.whileinterpreter.gui.viewmodels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class RunResults {

    private final ObservableList<RunResult> runResults = FXCollections.observableArrayList();

    public ObservableList<RunResult> getRunResults() {
        return runResults;
    }

    public static class RunResult {

        private final ObservableList<Pair<String, BigInteger>> programState = FXCollections.observableArrayList();

        public RunResult() {}

        public RunResult(List<Pair<String, BigInteger>> programState) {
            this.programState.setAll(programState);
        }

        public static RunResult of(Map<String, BigInteger> programState) {
            return new RunResult(programState.entrySet().stream().map(Pair::fromMapEntry).toList());
        }

        public ObservableList<Pair<String, BigInteger>> getProgramState() {
            return programState;
        }

    }

}
