package com.github.kostiaperehuda.whileinterpreter.gui;

import java.io.IOException;

import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        WhileInterpreterGUIApplication.setRoot("primary");
    }
}