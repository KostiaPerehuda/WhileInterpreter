package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SequenceTest {

    @Mock
    private State state;

    @Mock
    private Command command;

    @Test
    public void shouldExecuteBothChildCommands() {
        new Sequence(command, command).execute(state);

        verify(command, times(2)).execute(state);
        verifyNoInteractions(state);
    }

}