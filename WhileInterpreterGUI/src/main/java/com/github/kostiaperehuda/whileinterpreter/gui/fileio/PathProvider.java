package com.github.kostiaperehuda.whileinterpreter.gui.fileio;

import java.nio.file.Path;
import java.util.Optional;

@FunctionalInterface
public interface PathProvider {
    Optional<Path> getPath();
}
