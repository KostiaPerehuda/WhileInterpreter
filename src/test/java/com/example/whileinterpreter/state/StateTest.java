package com.example.whileinterpreter.state;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StateTest {
	
	private State state;

	@Before
	public void setUp() throws Exception {
		state = new StateImpl();
	}

	@Test
	public void shouldSuccessfullyPutAndRetrieveDataFromTheMap() {
		String varName = "dummy";
		long dataIn = 1;
		long dataOut;
		
		state.put(varName, dataIn);
		dataOut = state.get(varName);
		
		assertEquals(dataIn, dataOut);
	}
	
	@Test(expected = UndefinedVariableException.class)
	public void shouldThrowErrorWhenGettingUndefinedVariableFromTheMap() {
		String varName = "dummy";
		
		state.get(varName);
	}
	
	@Test
	public void shouldPrintEmptyMapIfMapIsEmpty() {
		String stateString = state.toString();
		
		assertEquals("{}", stateString);
	}
	
	@Test
	public void shouldPrintAllEntriesInTheNonEmptyMap() {
		String var1 = "dummy1";
		String var2 = "dummy2";
		long data1 = 1;
		long data2 = 2;
		
		state.put(var1, data1);
		state.put(var2, data2);
		
		String expectedString = "{\n\tdummy1 : 1;\n\tdummy2 : 2;\n}";
		String stateString = state.toString();
		
		assertEquals(expectedString, stateString);
	}

}
