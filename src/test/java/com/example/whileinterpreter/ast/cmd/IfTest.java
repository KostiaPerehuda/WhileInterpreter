package com.example.whileinterpreter.ast.cmd;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.whileinterpreter.ast.bexp.BooleanExpression;
import com.example.whileinterpreter.state.State;

@RunWith(MockitoJUnitRunner.class)
public class IfTest {

	@Mock
	private State state;

	@Mock
	private BooleanExpression condition;

	@Mock
	private Command ifClause;

	@Mock
	private Command elseClause;

	@Test
	public void shouldOnlyExecuteIfClauseWhenConditionIsTrue() {
		when(condition.eval(state)).thenReturn(true);

		new If(condition, ifClause, elseClause).execute(state);

		verify(condition, times(1)).eval(state);
		verify(ifClause, times(1)).execute(state);

		verifyNoInteractions(elseClause);
		verifyNoInteractions(state);
	}

	@Test
	public void shouldOnlyExecuteElseClauseWhenConditionIsFalse() {
		when(condition.eval(state)).thenReturn(false);

		new If(condition, ifClause, elseClause).execute(state);

		verify(condition, times(1)).eval(state);
		verify(elseClause, times(1)).execute(state);

		verifyNoInteractions(ifClause);
		verifyNoInteractions(state);
	}

}
