package com.github.kostiaperehuda.whileinterpreter.gui.fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesFileSystem implements FileSystem {

    @Override
    public String readString(Path path) throws IOException {
        return Files.readString(path);
    }

    @Override
    public void writeString(Path path, String string) throws IOException {
        Files.writeString(path, string);
    }

}
