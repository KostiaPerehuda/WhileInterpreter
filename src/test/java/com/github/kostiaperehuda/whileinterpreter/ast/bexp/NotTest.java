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
