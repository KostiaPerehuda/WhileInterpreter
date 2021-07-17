package com.example.whileinterpreter.ast.aexp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.whileinterpreter.state.State;

@RunWith(MockitoJUnitRunner.class)
public class ConstTest {

	@Mock
	private State state;

	@Test
	public void shouldReturnTheValueItWasGivenToHold() {
		long result = new Const(1L).eval(state);

		verifyNoInteractions(state);
		assertEquals(1L, result);
	}

}
