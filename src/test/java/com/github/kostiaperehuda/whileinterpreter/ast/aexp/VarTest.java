package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VarTest {

    @Mock
    private State state;

    @Test
    public void shouldGetVariableValueFromTheProgramStateByVariableNameAndReturnTheValue() {
        when(state.get("dummy")).thenReturn(1L);

        long result = new Var("dummy").eval(state);

        verify(state, times(1)).get("dummy");
        assertEquals(1L, result);
    }

}
