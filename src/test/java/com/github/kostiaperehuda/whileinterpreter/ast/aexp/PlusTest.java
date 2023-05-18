package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlusTest {

    @Mock
    private State state;

    @Mock
    private ArithmeticExpression exp1;

    @Mock
    private ArithmeticExpression exp2;

    @Test
    public void shouldEvaluateBothChildExpressionsAndReturnTheSumOfTheirResults() {
        when(exp1.eval(state)).thenReturn(3L);
        when(exp2.eval(state)).thenReturn(5L);

        long result = new Plus(exp1, exp2).eval(state);

        verify(exp1, times(1)).eval(state);
        verify(exp2, times(1)).eval(state);
        verifyNoInteractions(state);

        assertEquals(8L, result);
    }

}
