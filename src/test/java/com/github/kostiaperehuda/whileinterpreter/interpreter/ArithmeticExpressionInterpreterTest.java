package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.*;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArithmeticExpressionInterpreterTest {

    @Test
    void shouldExtractTheValueFromTheConstant() {
        var constant = new Const(1L);

        var result = new ArithmeticExpressionInterpreter().evaluate(constant, null);

        assertEquals(1L, result);
    }

    @Test
    void shouldExtractTheValueFromTheVariableByQueryingTheState() {
        var state = mock(State.class);
        when(state.get("dummy")).thenReturn(1L);
        var variable = new Variable("dummy");

        var result = new ArithmeticExpressionInterpreter().evaluate(variable, state);

        assertEquals(1L, result);
        verify(state).get("dummy");
        verifyNoMoreInteractions(state);
    }

    @Test
    void shouldEvaluatePlusOperatorByComputingTheSumOfItsOperands() {
        var plus = new Plus(new Const(3L), new Const(5L));

        var result = new ArithmeticExpressionInterpreter().evaluate(plus, null);

        assertEquals(8L, result);
    }

    @Test
    void shouldEvaluateMinusOperatorByComputingTheDifferenceOfItsOperands() {
        var minus = new Minus(new Const(3L), new Const(5L));

        var result = new ArithmeticExpressionInterpreter().evaluate(minus, null);

        assertEquals(-2L, result);
    }

    @Test
    void shouldEvaluateTimesOperatorByComputingTheProductOfItsOperands() {
        var times = new Times(new Const(3L), new Const(5L));

        long result = new ArithmeticExpressionInterpreter().evaluate(times, null);

        assertEquals(15L, result);
    }

}
