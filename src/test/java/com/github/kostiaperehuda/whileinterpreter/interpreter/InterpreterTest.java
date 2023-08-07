package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.*;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Assign;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Skip;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

class InterpreterTest {

    @Test
    void shouldNotAffectProgramStateWhenExecutingSkipInstruction() {
        var state = mock(State.class);
        var skip = new Skip();

        new Interpreter().execute(skip, null);

        verifyNoInteractions(state);
    }

    @ParameterizedTest
    @MethodSource("arithmeticExpressionsWithExpectedResults")
    void shouldEvaluateArithmeticExpressionAndPutItsResultIntoTheProgramStateWhenExecutingAssignInstruction(
            ArithmeticExpression expression, BigInteger expectedResult
    ) {
        var state = mock(State.class);
        var assignment = new Assign("result", expression);

        new Interpreter().execute(assignment, state);

        verify(state).put("result", expectedResult);
        verifyNoMoreInteractions(state);
    }

    static Stream<Arguments> arithmeticExpressionsWithExpectedResults() {
        return Stream.of(
                Arguments.of(new Const(BigInteger.TEN), BigInteger.TEN),
                Arguments.of(new Plus(new Const(BigInteger.ONE), new Const(BigInteger.TWO)), BigInteger.valueOf(3)),
                Arguments.of(new Minus(new Const(BigInteger.ONE), new Const(BigInteger.TWO)), BigInteger.valueOf(-1)),
                Arguments.of(new Multiply(new Const(BigInteger.TEN), new Const(BigInteger.TWO)), BigInteger.valueOf(20))
        );
    }

}