package com.github.kostiaperehuda.whileinterpreter.gui.testdoubles.fileio;

import com.github.kostiaperehuda.whileinterpreter.gui.fileio.FileSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FakeInMemoryFileSystem implements FileSystem {

    private final Map<Path, String> files = new HashMap<>();

    @Override
    public String readString(Path path) throws IOException {
        if (files.containsKey(path))
            return files.get(path);
        throw new FileNotFoundException(path.toString());
    }

    @Override
    public void writeString(Path path, String string) {
        files.put(path, string);
    }

}
