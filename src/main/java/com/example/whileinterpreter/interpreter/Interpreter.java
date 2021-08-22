package com.example.whileinterpreter.interpreter;

import java.io.IOException;

import com.example.whileinterpreter.parser.ProgramParser;
import com.example.whileinterpreter.state.State;
import com.example.whileinterpreter.state.StateImpl;

public class Interpreter {

	private State state = new StateImpl();

	public Interpreter() {
	}

	public void executeFileAndPrintResult(String filename) throws IOException {
		executeFile(filename);
		printState();
	}

	public void executeFile(String filename) throws IOException {
		ProgramParser.parseFile(filename).execute(state);
	}

	public void executeStringAndPrintResult(String string) {
		executeString(string);
		printState();
	}

	public void executeString(String string) {
		ProgramParser.parseString(string).execute(state);
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
		new Interpreter().executeFileAndPrintResult(args[0]);
	}

}
