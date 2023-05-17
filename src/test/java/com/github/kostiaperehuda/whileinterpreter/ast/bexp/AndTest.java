package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

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

import com.github.kostiaperehuda.whileinterpreter.state.State;

@RunWith(MockitoJUnitRunner.class)
public class AndTest {

	@Mock
	private State state;

	@Mock
	private BooleanExpression exp1;

	@Mock
	private BooleanExpression exp2;

	@Test
	public void shouldEvaluateToTrueWhenBothChildExpressionsEvaluateToTrue() {
		when(exp1.eval(state)).thenReturn(true);
		when(exp2.eval(state)).thenReturn(true);

		boolean result = new And(exp1, exp2).eval(state);

		verify(exp1, times(1)).eval(state);
		verify(exp2, times(1)).eval(state);
		verifyNoInteractions(state);

		assertTrue(result);
	}

	@Test
	public void shouldEvaluateToFalseWhenFirstExpressionIsTrueAndSecondExpressionIsFalse() {
		when(exp1.eval(state)).thenReturn(true);
		when(exp2.eval(state)).thenReturn(false);

		boolean result = new And(exp1, exp2).eval(state);

		verify(exp1, times(1)).eval(state);
		verify(exp2, times(1)).eval(state);
		verifyNoInteractions(state);

		assertFalse(result);
	}

	@Test
	public void shouldEvaluateToFalseWhenFirstExpressionIsFalse() {
		when(exp1.eval(state)).thenReturn(false);

		boolean result = new And(exp1, exp2).eval(state);

		verify(exp1, times(1)).eval(state);
		verifyNoInteractions(exp2);
		verifyNoInteractions(state);

		assertFalse(result);
	}

}
