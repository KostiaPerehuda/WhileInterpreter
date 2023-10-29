package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.ast.Command;
import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.FileSystem;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.PathProvider;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.RunResults.RunResult;
import com.github.kostiaperehuda.whileinterpreter.interpreter.Interpreter;
import com.github.kostiaperehuda.whileinterpreter.parser.ProgramParser;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

public class ExecuteFileAction implements Runnable {

    private final ViewModel view;
    private final FileSystem fileSystem;
    private final PathProvider pathProvider;

    public ExecuteFileAction(ViewModel view, FileSystem fileSystem, PathProvider pathProvider) {
        this.view = view;
        this.fileSystem = fileSystem;
        this.pathProvider = pathProvider;
    }

    @Override
    public void run() {
        pathProvider.getPath().ifPresent(filepath -> {
            try {
                String programString = fileSystem.readString(filepath);

                Command program = ProgramParser.parseString(programString);
                Interpreter interpreter = new Interpreter();
                Map<String, BigInteger> result = interpreter.execute(program);

                view.runResults().getRunResults().add(RunResult.of(result));
                view.statusBar().setStatus("Executed " + filepath);
            }
            catch (IOException | RuntimeException e) {
                e.printStackTrace();
                view.statusBar().setStatus("Failed to execute " + filepath + " due to " + e.getMessage());
            }
        });
    }

}
