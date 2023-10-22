package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.ast.Command;
import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.PathProvider;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;
import com.github.kostiaperehuda.whileinterpreter.interpreter.Interpreter;
import com.github.kostiaperehuda.whileinterpreter.parser.ProgramParser;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

public class ExecuteFileAction implements Runnable {

    private final ViewModel view;
    private final PathProvider pathProvider;

    public ExecuteFileAction(ViewModel view, PathProvider pathProvider) {
        this.view = view;
        this.pathProvider = pathProvider;
    }

    @Override
    public void run() {
        Optional<Path> filepath = pathProvider.getPath();
        if (filepath.isEmpty()) return;
        try {
            Command program = ProgramParser.parseFile(filepath.get());
            Interpreter interpreter = new Interpreter();
            Map<String, BigInteger> result = interpreter.execute(program);

            view.editor().tabs().add(new Editor.Tab(result.toString()));
            view.statusBar().statusProperty().set("Executed " + filepath.get());
        } catch (IOException e) {
            e.printStackTrace();
            view.statusBar().statusProperty().set("Failed to execute " + filepath.get() + " due to " + e.getMessage());
        }
    }

}
