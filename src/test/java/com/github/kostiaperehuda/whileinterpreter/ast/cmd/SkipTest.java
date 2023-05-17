package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verifyNoInteractions;

@RunWith(MockitoJUnitRunner.class)
public class SkipTest {

    @Mock
    private State state;

    @Test
    public void shouldNotAffectProgramStateWhenExecuted() {
        new Skip().execute(state);

        verifyNoInteractions(state);
    }

}