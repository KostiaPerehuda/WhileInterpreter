package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotTest {

    @Mock
    private State state;

    @Mock
    private BooleanExpression exp;

    @Test
    public void shouldEvaluateToTrueWhenChildExpressionEvaluatesToFalse() {
        when(exp.eval(state)).thenReturn(false);

        boolean result = new Not(exp).eval(state);

        verify(exp, times(1)).eval(state);
        verifyNoInteractions(state);

        assertTrue(result);
    }

    @Test
    public void shouldEvaluateToFalseWhenChildExpressionEvaluatesToTrue() {
        when(exp.eval(state)).thenReturn(true);

        boolean result = new Not(exp).eval(state);

        verify(exp, times(1)).eval(state);
        verifyNoInteractions(state);

        assertFalse(result);
    }

}
