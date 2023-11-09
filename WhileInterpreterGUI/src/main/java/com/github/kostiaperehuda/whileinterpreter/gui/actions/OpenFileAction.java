package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.FileSystem;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.PathProvider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class OpenFileAction implements Runnable {

    private final ViewModel view;
    private final FileSystem fileSystem;
    private final PathProvider pathProvider;

    public OpenFileAction(ViewModel view, FileSystem fileSystem, PathProvider pathProvider) {
        this.view = view;
        this.fileSystem = fileSystem;
        this.pathProvider = pathProvider;
    }

    @Override
    public void run() {
        Optional<Path> filepath = pathProvider.getPath();
        if (filepath.isEmpty()) return;

        try {
            String program = fileSystem.readString(filepath.get());

            view.editor().openNewTab().setText(program);
            view.statusBar().setStatus("Opened " + filepath.get());
        }
        catch (IOException e) {
            view.statusBar().setStatus("Failed to open %s! Got error: %s".formatted(filepath.get(), e.getMessage()));
        }
    }

}
