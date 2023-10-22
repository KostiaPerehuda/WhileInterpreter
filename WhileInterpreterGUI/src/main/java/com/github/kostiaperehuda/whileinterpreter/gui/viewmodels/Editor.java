package com.github.kostiaperehuda.whileinterpreter.gui.viewmodels;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public record Editor(
        ObservableList<Tab> tabs,
        ObjectProperty<Tab> activeTab
) {

    public Editor() {
        this(
                FXCollections.observableArrayList(),
                new SimpleObjectProperty<>()
        );
    }


    public record Tab(StringProperty text) {

        public Tab() {
            this(new SimpleStringProperty(""));
        }

        public Tab(String text) {
            this(new SimpleStringProperty(text));
        }
    }
}
