package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;

@RunWith(MockitoJUnitRunner.class)
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
