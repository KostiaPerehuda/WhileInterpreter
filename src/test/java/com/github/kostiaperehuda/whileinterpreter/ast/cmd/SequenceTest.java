package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
