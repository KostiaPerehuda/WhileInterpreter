package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssignTest {

    @Mock
    private State state;

    @Mock
    private ArithmeticExpression exp;

    @Test
    public void shouldEvaluateExpressionAndPutResultIntoMap() {
        when(exp.eval(state)).thenReturn(1L);

        new Assign("dummy", exp).execute(state);

        verify(exp, times(1)).eval(state);
        verify(state, times(1)).put("dummy", 1L);
    }

}
