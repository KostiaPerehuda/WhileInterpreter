package com.example.whileinterpreter.ast.aexp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.whileinterpreter.state.State;

@RunWith(MockitoJUnitRunner.class)
public class TimesTest {

	@Mock
	private State state;

	@Mock
	private ArithmeticExpression exp1;

	@Mock
	private ArithmeticExpression exp2;

	@Test
	public void shouldEvaluateBothChildExpressionsAndReturnTheProductOfTheirResults() {
		when(exp1.eval(state)).thenReturn(3L);
		when(exp2.eval(state)).thenReturn(5L);

		long result = new Times(exp1, exp2).eval(state);

		verify(exp1, times(1)).eval(state);
		verify(exp2, times(1)).eval(state);
		verifyNoInteractions(state);

		assertEquals(15L, result);
	}

}
