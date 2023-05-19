package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Command;
import com.github.kostiaperehuda.whileinterpreter.parser.ProgramParser;
import com.github.kostiaperehuda.whileinterpreter.state.InMemoryState;
import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.io.IOException;

public class Interpreter {

    private final State state;
    private final CommandInterpreter commandInterpreter;

    public Interpreter(State state) {
        this.state = state;
        this.commandInterpreter = new CommandInterpreter();
    }

    public void executeFileAndPrintResult(String filename) throws IOException {
        executeFile(filename);
        printState();
    }

    public void executeFile(String filename) throws IOException {
        execute(ProgramParser.parseFile(filename));
    }

    public void executeStringAndPrintResult(String string) {
        executeString(string);
        printState();
    }

    public void executeString(String string) {
        execute(ProgramParser.parseString(string));
    }

    public void execute(Command command) {
        commandInterpreter.execute(command, state);
    }

    public void printState() {
        System.out.println(state);
    }

    public String getState() {
        return state.toString();
    }

    public static void run(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Fatal error! No input file specified as a command line argument!");
            return;
        }
        new Interpreter(new InMemoryState()).executeFileAndPrintResult(args[0]);
    }

}
