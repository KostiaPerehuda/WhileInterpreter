package com.example.whileinterpreter.ast.bexp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.example.whileinterpreter.state.State;

@RunWith(MockitoJUnitRunner.class)
public class LessThanOrEqualTest {

	@Mock
	private State state;

	@Mock
	private ArithmeticExpression exp1;

	@Mock
	private ArithmeticExpression exp2;

	@Test
	public void shouldEvaluateToTrueWhenBothChildExpressionsEvaluateToTheSameValue() {
		when(exp1.eval(state)).thenReturn(1L);
		when(exp2.eval(state)).thenReturn(1L);

		boolean result = new LessThanOrEqual(exp1, exp2).eval(state);

		verify(exp1, times(1)).eval(state);
		verify(exp2, times(1)).eval(state);
		verifyNoInteractions(state);

		assertTrue(result);
	}

	@Test
	public void shouldEvaluateToTrueWhenFirstExpressionIsSmallerThanTheSecondExpression() {
		when(exp1.eval(state)).thenReturn(1L);
		when(exp2.eval(state)).thenReturn(2L);

		boolean result = new LessThanOrEqual(exp1, exp2).eval(state);

		verify(exp1, times(1)).eval(state);
		verify(exp2, times(1)).eval(state);
		verifyNoInteractions(state);

		assertTrue(result);
	}

	@Test
	public void shouldEvaluateToFalseWhenFirstExpressionIsLargerThanTheSecondExpression() {
		when(exp1.eval(state)).thenReturn(2L);
		when(exp2.eval(state)).thenReturn(1L);

		boolean result = new LessThanOrEqual(exp1, exp2).eval(state);

		verify(exp1, times(1)).eval(state);
		verify(exp2, times(1)).eval(state);
		verifyNoInteractions(state);

		assertFalse(result);
	}

}
