package com.github.kostiaperehuda.whileinterpreter.gui.controllers;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.RunResults.RunResult;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.RunResults.Variable;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class RunResultsController implements Initializable {

    private final ViewModel model;

    @FXML
    private TableView<Variable> root;

    public RunResultsController(ViewModel model) {
        this.model = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model.runResults().getRunResultList().addListener((ListChangeListener<RunResult>) change -> {
            while (change.next()) {
                for (RunResult result : change.getAddedSubList()) {
                    root.getItems().setAll(result.getProgramState());
                }
            }
        });
    }

}
