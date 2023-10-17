package com.github.kostiaperehuda.whileinterpreter.gui.controllers;

import com.github.kostiaperehuda.whileinterpreter.gui.actions.ExecuteFileUseCase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigInteger;
import java.util.Map;

public class MainController {

    @FXML
    private TableView<Map.Entry<String, BigInteger>> stateView;

    @FXML
    private TextArea mainTextArea;

    @FXML
    private void onMenuFileClose() {
        System.out.println("MainController.close() running on " + Thread.currentThread().getName());
        Platform.exit();
    }

    @FXML
    private void onMenuFileRun() {

        // Decide Which File to Run
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainTextArea.getScene().getWindow());
        if (file == null) return;

        // Call The application Layer to Run a File
        Map<String, BigInteger> executionResult = new ExecuteFileUseCase(file.toPath()).call();

        // Display the results
        mainTextArea.setText(executionResult.toString());
        stateView.getItems().addAll(executionResult.entrySet());

//        column.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getValues().get(index)));
    }

}
