package com.github.kostiaperehuda.whileinterpreter.gui;

import java.io.IOException;

import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        WhileInterpreterGUIApplication.setRoot("secondary");
    }
}
