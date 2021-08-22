package com.example.whileinterpreter.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.example.whileinterpreter.ast.cmd.Command;

public class ProgramParser {

	public static Command parseFile(String filename) throws IOException {
		return parseString(Files.readString(Paths.get(filename)));
	}

	public static Command parseString(String program) {
		return CommandParser.parseProgram(Lexer.lex(program));
	}

}
