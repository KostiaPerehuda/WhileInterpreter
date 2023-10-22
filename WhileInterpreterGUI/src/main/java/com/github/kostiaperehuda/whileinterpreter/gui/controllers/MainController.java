package com.github.kostiaperehuda.whileinterpreter.gui.controllers;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.math.BigInteger;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final ViewModel model;

    @FXML
    private Parent root;

    @FXML
    private TableView<Map.Entry<String, BigInteger>> stateView;

    @FXML
    private TextArea mainTextArea;

    public MainController(ViewModel model) {
        this.model = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainTextArea.textProperty().bind(Bindings.select(model.editor().activeTabProperty(), "text"));
    }

}
