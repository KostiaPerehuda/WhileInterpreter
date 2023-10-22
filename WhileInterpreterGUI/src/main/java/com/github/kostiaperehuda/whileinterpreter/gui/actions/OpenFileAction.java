package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.FileSystem;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.PathProvider;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class OpenFileAction implements Runnable {

    private final ViewModel model;
    private final FileSystem fileSystem;
    private final PathProvider pathProvider;

    public OpenFileAction(ViewModel model, FileSystem fileSystem, PathProvider pathProvider) {
        this.model = model;
        this.fileSystem = fileSystem;
        this.pathProvider = pathProvider;
    }

    @Override
    public void run() {
        Optional<Path> filepath = pathProvider.getPath();
        if (filepath.isEmpty()) return;

        try {
            String program = fileSystem.readString(filepath.get());

            model.editor().tabs().add(new Editor.Tab(program));
            model.statusBar().statusProperty().set("Opened " + filepath.get());
        } catch (IOException e) {
            e.printStackTrace();
            model.statusBar().statusProperty().set("Failed to open " + filepath.get());
        }
    }

}
