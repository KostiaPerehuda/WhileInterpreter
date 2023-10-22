package com.github.kostiaperehuda.whileinterpreter.gui.controllers;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.actions.ExecuteFileAction;
import com.github.kostiaperehuda.whileinterpreter.gui.actions.ExecuteProgramAction;
import com.github.kostiaperehuda.whileinterpreter.gui.actions.OpenFileAction;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.FileChooserPathProvider;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.FilesFileSystem;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Window;

import java.math.BigInteger;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final ViewModel model;

    @FXML
    private TableView<Map.Entry<String, BigInteger>> stateView;

    @FXML
    private TextArea mainTextArea;

    public MainController(ViewModel model) {
        this.model = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model.editor().tabs().addListener((ListChangeListener<Editor.Tab>) change -> {
            while (change.next()) {
                for (Editor.Tab tab : change.getAddedSubList()) {
                    mainTextArea.setText(tab.text().get());
                    model.editor().activeTab().set(tab);
                }
            }
        });
    }

    @FXML
    private void onMenuFileClose() {
        Platform.exit();
    }

    @FXML
    private void onMenuFileRun() {
        Window window = mainTextArea.getScene().getWindow();
        new ExecuteFileAction(model, FileChooserPathProvider.ofWhilePrograms(window)).run();

//        // Display the results
//        stateView.getItems().addAll(executionResult.entrySet());

//        column.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getValues().get(index)));
    }

    @FXML
    private void onMenuFileOpen() {
        Window window = mainTextArea.getScene().getWindow();
        new OpenFileAction(model, new FilesFileSystem(), FileChooserPathProvider.ofWhilePrograms(window)).run();
    }

    public void onToolbarRun() {
        new ExecuteProgramAction(model).run();
    }

}
