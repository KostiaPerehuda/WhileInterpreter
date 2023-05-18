package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class ConstTest {

    @Mock
    private State state;

    @Test
    public void shouldReturnTheValueItWasGivenToHold() {
        long result = new Const(1L).eval(state);

        verifyNoInteractions(state);
        assertEquals(1L, result);
    }

}
