package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.Const;
import com.github.kostiaperehuda.whileinterpreter.ast.aexp.Plus;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Assign;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Skip;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.mockito.Mockito.*;

class InterpreterTest {

    @Test
    void shouldNotAffectProgramStateWhenExecutingSkipInstruction() {
        var state = mock(State.class);
        var skip = new Skip();

        new Interpreter().execute(skip, null);

        verifyNoInteractions(state);
    }

    @Test
    void shouldEvaluateConstantExpressionAndPutItsResultIntoTheProgramStateWhenExecutingAssignInstruction() {
        var state = mock(State.class);
        var assignment = new Assign("result", new Const(BigInteger.TEN));

        new Interpreter().execute(assignment, state);

        verify(state).put("result", BigInteger.TEN);
        verifyNoMoreInteractions(state);
    }

    @Test
    void shouldEvaluatePlusOperatorByComputingTheSumOfItsOperands() {
        var state = mock(State.class);
        var onePlusTwo = new Plus(new Const(BigInteger.ONE), new Const(BigInteger.TWO));
        var assignment = new Assign("result", onePlusTwo);

        new Interpreter().execute(assignment, state);

        verify(state).put("result", BigInteger.valueOf(3));
        verifyNoMoreInteractions(state);
    }

}