package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.ast.Command;
import com.github.kostiaperehuda.whileinterpreter.interpreter.Interpreter;
import com.github.kostiaperehuda.whileinterpreter.parser.ProgramParser;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

public class ExecuteFileUseCase implements Callable<Map<String, BigInteger>> {

    private final Path filepath;

    public ExecuteFileUseCase(Path filepath) {
        this.filepath = filepath;
    }

    @Override
    public Map<String, BigInteger> call() {
        try {
            Command program = ProgramParser.parseFile(filepath);
            Interpreter interpreter = new Interpreter();
            return interpreter.execute(program);
        } catch (IOException e) {
            return Collections.emptyMap();
        }
    }

}
