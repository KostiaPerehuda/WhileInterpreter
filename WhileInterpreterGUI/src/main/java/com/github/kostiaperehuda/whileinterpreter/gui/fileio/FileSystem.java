package com.github.kostiaperehuda.whileinterpreter.gui.fileio;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSystem {
    String readString(Path path) throws IOException;
}
