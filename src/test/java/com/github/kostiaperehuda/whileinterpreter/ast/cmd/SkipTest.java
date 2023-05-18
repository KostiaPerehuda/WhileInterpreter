package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class SkipTest {

    @Mock
    private State state;

    @Test
    public void shouldNotAffectProgramStateWhenExecuted() {
        new Skip().execute(state);

        verifyNoInteractions(state);
    }

}