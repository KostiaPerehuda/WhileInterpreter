package com.github.kostiaperehuda.whileinterpreter.gui.viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StatusBar {

    private final StringProperty status = new SimpleStringProperty("");

    public StringProperty statusProperty() {
        return status;
    }

}
