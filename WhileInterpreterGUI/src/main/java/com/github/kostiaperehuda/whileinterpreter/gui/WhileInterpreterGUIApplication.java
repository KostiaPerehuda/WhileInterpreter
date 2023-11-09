package com.github.kostiaperehuda.whileinterpreter.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WhileInterpreterGUIApplication extends Application {

    private Scene scene;

    private ApplicationContext context;

    @Override
    public void init() {
        context = new ApplicationContext(new ViewModel());
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Main"), 960, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WhileInterpreterGUI.class.getResource("/views/" + fxml + ".fxml"));
        fxmlLoader.setControllerFactory(context::getNewInstanceOf);
        return fxmlLoader.load();
    }

}
