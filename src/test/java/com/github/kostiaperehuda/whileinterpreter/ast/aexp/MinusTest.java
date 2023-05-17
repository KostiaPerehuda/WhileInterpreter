package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MinusTest {

    @Mock
    private State state;

    @Mock
    private ArithmeticExpression exp1;

    @Mock
    private ArithmeticExpression exp2;

    @Test
    public void shouldEvaluateBothChildExpressionsAndReturnTheDifferenceOfTheirResults() {
        when(exp1.eval(state)).thenReturn(3L);
        when(exp2.eval(state)).thenReturn(5L);

        long result = new Minus(exp1, exp2).eval(state);

        verify(exp1, times(1)).eval(state);
        verify(exp2, times(1)).eval(state);
        verifyNoInteractions(state);

        assertEquals(-2L, result);
    }

}
