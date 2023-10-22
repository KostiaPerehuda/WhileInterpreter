package com.github.kostiaperehuda.whileinterpreter.gui.controllers;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.actions.ExecuteProgramAction;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class ToolBarController {

    private final ViewModel model;

    @FXML
    private ToolBar root;

    public ToolBarController(ViewModel model) {
        this.model = model;
    }

    @FXML
    private void onRun() {
        new ExecuteProgramAction(model).run();
    }

}
