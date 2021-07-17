package com.example.whileinterpreter.ast.cmd;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.example.whileinterpreter.state.State;

@RunWith(MockitoJUnitRunner.class)
public class AssignTest {

	@Mock
	private State state;

	@Mock
	private ArithmeticExpression exp;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldEvaluateExpressionAndPutResultIntoMap() {
		String varName = "dummy";
		long result = 1;
		
		when(exp.eval(state)).thenReturn(result);
		
		new Assign(varName, exp).execute(state);
		
		verify(exp, times(1)).eval(state);
		verify(state, times(1)).put(varName, result);
	}

}
