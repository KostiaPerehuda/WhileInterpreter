package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VariableTest {

    @Mock
    private State state;

    @Test
    public void shouldGetVariableValueFromTheProgramStateByVariableNameAndReturnTheValue() {
        when(state.get("dummy")).thenReturn(1L);

        long result = new Variable("dummy").eval(state);

        verify(state, times(1)).get("dummy");
        assertEquals(1L, result);
    }

}
