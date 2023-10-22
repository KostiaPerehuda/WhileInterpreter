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
        try {
            Optional<Path> filepath = pathProvider.getPath();
            if (filepath.isEmpty()) return;

            String program = fileSystem.readString(filepath.get());
            model.editor().tabs().add(new Editor.Tab(program));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
