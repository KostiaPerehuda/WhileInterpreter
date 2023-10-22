package com.github.kostiaperehuda.whileinterpreter.gui.controllers;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Pair;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

public class RunResultsController implements Initializable {

    private final ViewModel model;

    @FXML
    private TableView<Pair<String, BigInteger>> root;

    public RunResultsController(ViewModel model) {
        this.model = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.itemsProperty().bind(model.runResults().stateProperty());
    }

}
