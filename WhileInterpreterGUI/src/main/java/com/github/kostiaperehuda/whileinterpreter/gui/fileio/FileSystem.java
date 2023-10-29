package com.github.kostiaperehuda.whileinterpreter.gui.fileio;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSystem {

    String readString(Path path) throws IOException;

    void writeString(Path path, String string) throws IOException;

}
