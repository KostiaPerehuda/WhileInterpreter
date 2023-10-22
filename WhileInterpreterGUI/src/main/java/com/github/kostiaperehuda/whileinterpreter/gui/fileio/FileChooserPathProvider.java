package com.github.kostiaperehuda.whileinterpreter.gui.fileio;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;

public class FileChooserPathProvider implements PathProvider {

    private final FileChooser fileChooser;
    private final Window window;

    public FileChooserPathProvider(Window window) {
        this(new FileChooser(), window);
    }

    public FileChooserPathProvider(FileChooser fileChooser, Window window) {
        this.fileChooser = fileChooser;
        this.window = window;
    }

    public FileChooserPathProvider configure(Consumer<FileChooser> fileChooserConfigurator) {
        fileChooserConfigurator.accept(fileChooser);
        return this;
    }

    public static FileChooserPathProvider ofWhilePrograms(Window window) {
        FileChooserPathProvider provider = new FileChooserPathProvider(window);
        provider.fileChooser.getExtensionFilters().add(new ExtensionFilter("While Programs", "*.while"));
        return provider;
    }

    @Override
    public Optional<Path> getPath() {
        File file = fileChooser.showOpenDialog(window);
        if (file == null) return Optional.empty();
        return Optional.of(file.toPath());
    }

}
