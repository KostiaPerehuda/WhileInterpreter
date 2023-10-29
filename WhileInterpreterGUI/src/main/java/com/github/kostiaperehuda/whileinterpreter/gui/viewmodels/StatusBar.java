package com.github.kostiaperehuda.whileinterpreter.gui.viewmodels;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StatusBar {

    private final StringProperty status = new SimpleStringProperty("");

    public ReadOnlyStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getStatus() {
        return status.get();
    }

}
