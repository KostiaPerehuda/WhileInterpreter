package com.github.kostiaperehuda.whileinterpreter.parser;

import com.github.kostiaperehuda.whileinterpreter.ast.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProgramParser {

    public static Command parseFile(Path filepath) throws IOException {
        return parseString(Files.readString(filepath));
    }

    public static Command parseString(String program) {
        return CommandParser.parseProgram(Lexer.lex(program));
    }

}
