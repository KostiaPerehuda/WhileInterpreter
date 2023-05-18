package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
