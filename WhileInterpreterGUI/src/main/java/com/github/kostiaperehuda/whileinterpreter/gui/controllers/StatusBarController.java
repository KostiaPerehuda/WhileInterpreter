package com.github.kostiaperehuda.whileinterpreter.gui.controllers;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StatusBarController implements Initializable {

    private final ViewModel model;

    @FXML
    private Parent root;

    @FXML
    private Label status;

    public StatusBarController(ViewModel model) {
        this.model = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        status.textProperty().bind(model.statusBar().statusProperty());
    }

}
