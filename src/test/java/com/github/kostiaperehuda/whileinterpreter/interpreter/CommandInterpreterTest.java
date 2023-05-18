package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.Const;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Assign;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Skip;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CommandInterpreterTest {

    @Test
    public void shouldNotAffectProgramStateWhenExecutingSkip() {
        var state = mock(State.class);
        var skip = new Skip();

        new CommandInterpreter().execute(skip, state);

        verifyNoInteractions(state);
    }

    @Test
    public void shouldEvaluateAssignExpressionAndPutItsResultIntoTheProgramStateWhenExecutingAssign() {
        var state = mock(State.class);
        var aExpInterpreter = mock(ArithmeticExpressionInterpreter.class);
        var expression = new Const(10L);
        var assign = new Assign("dummy", expression);

        new CommandInterpreter(aExpInterpreter).execute(assign, state);

        verify(state, times(1)).put("dummy", 10L);
        verify(aExpInterpreter, times(1)).evaluate(expression, state);
    }

}
