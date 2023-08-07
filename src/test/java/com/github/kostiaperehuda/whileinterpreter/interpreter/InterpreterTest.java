package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.Const;
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
        var command = new Skip();

        new Interpreter().execute(command, state);

        verifyNoInteractions(state);
    }

    @Test
    void shouldEvaluateConstantExpressionAndPutItsResultIntoTheProgramStateWhenExecutingAssignInstruction() {
        var state = mock(State.class);
        var command = new Assign("dummy", new Const(BigInteger.TEN));

        new Interpreter().execute(command, state);

        verify(state).put("dummy", BigInteger.TEN);
    }

}