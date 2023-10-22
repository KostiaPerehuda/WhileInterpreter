package com.github.kostiaperehuda.whileinterpreter.gui.viewmodels;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;


public class Editor {

    private final ObservableList<Tab> tabs = FXCollections.observableArrayList();
    private final ObjectProperty<Tab> activeTab = new SimpleObjectProperty<>();

    public Editor() {
        this.tabs.addListener((ListChangeListener<Tab>) change -> {
            while (change.next()) {
                for (Tab tab : change.getAddedSubList()) {
                    activeTab.set(tab);
                }
            }
        });
    }

    public ObservableList<Tab> tabs() {
        return tabs;
    }

    public ObjectProperty<Tab> activeTabProperty() {
        return activeTab;
    }

    public static class Tab {

        private final StringProperty text = new SimpleStringProperty("");

        public Tab(String text) {
            this.text.set(text);
        }

        public StringProperty textProperty() {
            return text;
        }

    }

}
