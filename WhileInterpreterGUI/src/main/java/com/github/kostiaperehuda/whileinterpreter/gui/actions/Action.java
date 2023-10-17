package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import javafx.application.Platform;

public interface Action extends Runnable {

    void execute();

    void updateViewModel();

    @Override
    default void run() {
        execute();
        Platform.runLater(this::updateViewModel);
    }

}
