package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Skip;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

class InterpreterTest {

    @Test
    void shouldNotAffectProgramStateWhenExecutingSkipInstruction() {
        var state = mock(State.class);
        var command = new Skip();

        new Interpreter().execute(command, state);

        verifyNoInteractions(state);
    }

}