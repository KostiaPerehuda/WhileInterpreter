package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EqualsTest {

    @Mock
    private State state;

    @Mock
    private ArithmeticExpression exp1;

    @Mock
    private ArithmeticExpression exp2;

    @Test
    public void shouldEvaluateToTrueWhenChildExpressionsEvaluateToTheSameValue() {
        when(exp1.eval(state)).thenReturn(1L);
        when(exp2.eval(state)).thenReturn(1L);

        boolean result = new Equals(exp1, exp2).eval(state);

        verify(exp1, times(1)).eval(state);
        verify(exp2, times(1)).eval(state);
        verifyNoInteractions(state);

        assertTrue(result);
    }

    @Test
    public void shouldEvaluateToFalseWhenChildExpressionsEvaluateToDifferentValues() {
        when(exp1.eval(state)).thenReturn(1L);
        when(exp2.eval(state)).thenReturn(2L);

        boolean result = new Equals(exp1, exp2).eval(state);

        verify(exp1, times(1)).eval(state);
        verify(exp2, times(1)).eval(state);
        verifyNoInteractions(state);

        assertFalse(result);
    }

}
