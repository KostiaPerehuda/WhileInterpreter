package com.example.whileinterpreter;

import java.io.IOException;

import com.example.whileinterpreter.ast.cmd.Command;
import com.example.whileinterpreter.parser.ProgramParser;
import com.example.whileinterpreter.state.State;
import com.example.whileinterpreter.state.StateImpl;

public class WhileInterpreter {

	public static void main(String[] args) throws IOException {
		Command cmd = ProgramParser.parseFile("C:\\users\\home\\Desktop\\test.txt");
		State state = new StateImpl();
		cmd.execute(state);
		System.out.println(state);
	}

}
