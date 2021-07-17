package com.example.whileinterpreter.ast.cmd;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
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

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldOnlyExecuteIfClauseWhenConditionIsTrue() {
		when(condition.eval()).thenReturn(true);

		new If(condition, ifClause, elseClause).execute(state);

		verify(condition, times(1)).eval();
		verify(ifClause, times(1)).execute(state);

		verifyNoInteractions(elseClause);
		verifyNoInteractions(state);
	}

	@Test
	public void shouldOnlyExecuteElseClauseWhenConditionIsFalse() {
		when(condition.eval()).thenReturn(false);

		new If(condition, ifClause, elseClause).execute(state);

		verify(condition, times(1)).eval();
		verify(elseClause, times(1)).execute(state);

		verifyNoInteractions(ifClause);
		verifyNoInteractions(state);
	}

}
