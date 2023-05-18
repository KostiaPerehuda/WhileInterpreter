package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
