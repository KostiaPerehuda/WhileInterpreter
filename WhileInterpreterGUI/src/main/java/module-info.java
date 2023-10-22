module com.github.kostiaperehuda.whileinterpreter.gui {
    requires com.github.kostiaperehuda.whileinterpreter.core;

    requires javafx.controls;
    requires javafx.fxml;

    exports com.github.kostiaperehuda.whileinterpreter.gui to javafx.graphics;
    opens com.github.kostiaperehuda.whileinterpreter.gui.controllers to javafx.fxml;
    exports com.github.kostiaperehuda.whileinterpreter.gui.experimental to javafx.graphics;
}
