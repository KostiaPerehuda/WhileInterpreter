package com.github.kostiaperehuda.whileinterpreter.gui.controllers;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.actions.ExecuteFileAction;
import com.github.kostiaperehuda.whileinterpreter.gui.actions.editor.OpenFileAction;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.FileChooserPathProvider;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.FilesFileSystem;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.Window;

public class MenuBarController {

    private final ViewModel model;

    @FXML
    private MenuBar root;

    public MenuBarController(ViewModel model) {
        this.model = model;
    }

    @FXML
    private void onFileExit() {
        Platform.exit();
    }

    @FXML
    private void onFileRun() {
        Window window = root.getScene().getWindow();
        new ExecuteFileAction(model, new FilesFileSystem(), FileChooserPathProvider.ofWhilePrograms(window)).run();
    }

    @FXML
    private void onFileOpen() {
        Window window = root.getScene().getWindow();
        new OpenFileAction(model, new FilesFileSystem(), FileChooserPathProvider.ofWhilePrograms(window)).run();
    }

}
