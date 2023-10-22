package com.github.kostiaperehuda.whileinterpreter.gui.fileio;

import java.nio.file.Path;
import java.util.Optional;

public interface PathProvider {
    Optional<Path> getPath();
}
