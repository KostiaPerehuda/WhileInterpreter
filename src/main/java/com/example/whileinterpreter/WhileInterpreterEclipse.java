package com.example.whileinterpreter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class WhileInterpreterEclipse {

	public static void main(String[] args) throws IOException, URISyntaxException {
		WhileInterpreter.main(new String[] {getResource("program.while")});
	}

	private static String getResource(String name) throws URISyntaxException {
		return Paths.get(WhileInterpreterEclipse.class.getResource(name).toURI()).toString();
	}

}
