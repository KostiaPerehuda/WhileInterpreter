package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.ast.bexp.BooleanExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IfTest {

    @Mock
    private State state;

    @Mock
    private BooleanExpression condition;

    @Mock
    private Command ifBranch;

    @Mock
    private Command elseBranch;

    @Test
    public void shouldOnlyExecuteIfBranchWhenConditionIsTrue() {
        when(condition.eval(state)).thenReturn(true);

        new If(condition, ifBranch, elseBranch).execute(state);

        verify(condition, times(1)).eval(state);
        verify(ifBranch, times(1)).execute(state);

        verifyNoInteractions(elseBranch);
        verifyNoInteractions(state);
    }

    @Test
    public void shouldOnlyExecuteElseBranchWhenConditionIsFalse() {
        when(condition.eval(state)).thenReturn(false);

        new If(condition, ifBranch, elseBranch).execute(state);

        verify(condition, times(1)).eval(state);
        verify(elseBranch, times(1)).execute(state);

        verifyNoInteractions(ifBranch);
        verifyNoInteractions(state);
    }

}
