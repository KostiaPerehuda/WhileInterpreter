package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.ast.bexp.BooleanExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WhileTest {

    @Mock
    private State state;

    @Mock
    private BooleanExpression condition;

    @Mock
    private Command loopBody;

    @Test
    public void shouldNotEnterLoopBodyWhenConditionIsInitiallyFalse() {
        when(condition.eval(state)).thenReturn(false);

        new While(condition, loopBody).execute(state);

        verify(condition, times(1)).eval(state);

        verifyNoInteractions(loopBody);
        verifyNoInteractions(state);
    }

    @Test
    public void shouldOnlyEnterLoopBodyOnceWhenConditionIsInitiallyTrueAndFalseAfterOneIteration() {
        when(condition.eval(state)).thenReturn(true).thenReturn(false);

        new While(condition, loopBody).execute(state);

        verify(condition, times(2)).eval(state);
        verify(loopBody, times(1)).execute(state);

        verifyNoInteractions(state);
    }

}
