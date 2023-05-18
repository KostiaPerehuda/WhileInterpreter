package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.*;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArithmeticExpressionInterpreterTest {

    @Test
    public void shouldExtractTheValueFromTheConstant() {
        var state = mock(State.class);
        var constant = new Const(1L);

        var result = new ArithmeticExpressionInterpreter().evaluate(constant, state);

        assertEquals(1L, result);
        verifyNoInteractions(state);
    }

    @Test
    public void shouldExtractTheValueFromTheVariableByQueryingTheState() {
        var state = mock(State.class);
        when(state.get("dummy")).thenReturn(1L);
        var variable = new Variable("dummy");

        var result = new ArithmeticExpressionInterpreter().evaluate(variable, state);

        assertEquals(1L, result);
        verify(state, times(1)).get("dummy");
        verify(state, never()).put(any(), any());
    }

    @Test
    public void shouldEvaluatePlusByComputingTheSumOfItsChildren() {
        var state = mock(State.class);
        var plus = new Plus(new Const(3L), new Const(5L));

        var result = new ArithmeticExpressionInterpreter().evaluate(plus, state);

        assertEquals(8L, result);
        verifyNoInteractions(state);
    }

    @Test
    public void shouldEvaluateMinusByComputingTheDifferenceOfItsChildren() {
        var state = mock(State.class);
        var minus = new Minus(new Const(3L), new Const(5L));

        var result = new ArithmeticExpressionInterpreter().evaluate(minus, state);

        assertEquals(-2L, result);
        verifyNoInteractions(state);
    }

    @Test
    public void shouldEvaluateTimesByComputingTheProductOfItsChildren() {
        var state = mock(State.class);
        var times = new Times(new Const(3L), new Const(5L));

        long result = new ArithmeticExpressionInterpreter().evaluate(times, state);

        assertEquals(15L, result);
        verifyNoInteractions(state);
    }

}
