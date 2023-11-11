package com.github.kostiaperehuda.whileinterpreter.gui.viewmodels;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Editor {

    private final ObservableList<Tab> tabs = FXCollections.observableArrayList();
    private final ObjectProperty<Tab> selectedTab = new SimpleObjectProperty<>();

    public ObservableList<Tab> getTabs() {
        return FXCollections.unmodifiableObservableList(tabs);
    }

    public ReadOnlyObjectProperty<Tab> selectedTabProperty() {
        return selectedTab;
    }

    public void select(Tab tab) {
        if (tab == null || tabs.contains(tab)) {
            selectedTab.set(tab);
            return;
        }
        throw new IllegalArgumentException("Cannot select %s because it does not belong to the editor!".formatted(tab));
    }

    public Tab getSelectedTab() {
        return selectedTab.get();
    }

    public Tab openNewTab() {
        Tab tab = new Tab();
        tabs.add(tab);
        return tab;
    }

    public static class Tab {

        private final StringProperty text = new SimpleStringProperty("");

        private Tab() {}

        public StringProperty textProperty() {
            return text;
        }

        public String getText() {
            return text.get();
        }

        public void setText(String text) {
            this.text.set(text);
        }

    }

}
