package com.github.kostiaperehuda.whileinterpreter.cli;

import com.github.kostiaperehuda.whileinterpreter.ast.Command;
import com.github.kostiaperehuda.whileinterpreter.interpreter.Interpreter;
import com.github.kostiaperehuda.whileinterpreter.parser.ProgramParser;

import java.io.IOException;
import java.nio.file.Paths;

public class WhileInterpreterCLI {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Fatal error! No input file specified as a command line argument!");
            return;
        }

        Command program = ProgramParser.parseFile(Paths.get(args[0]));

        Interpreter interpreter = new Interpreter();
        var executionResult = interpreter.execute(program);

        System.out.println(executionResult);
    }

}
