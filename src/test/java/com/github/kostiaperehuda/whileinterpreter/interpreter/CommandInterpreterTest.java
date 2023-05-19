package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.github.kostiaperehuda.whileinterpreter.ast.aexp.Const;
import com.github.kostiaperehuda.whileinterpreter.ast.bexp.Bool;
import com.github.kostiaperehuda.whileinterpreter.ast.bexp.BooleanExpression;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.*;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandInterpreterTest {

    @Test
    void shouldNotAffectProgramStateWhenExecutingSkip() {
        newCommandInterpreterUnderTest().execute(new Skip(), null);
    }

    @Test
    void shouldEvaluateAssignExpressionAndPutItsResultIntoTheProgramStateWhenExecutingAssign() {
        var state = mock(State.class);
        var command = new Assign("dummy", new Const(10L));

        newCommandInterpreterUnderTest().execute(command, state);

        verify(state).put("dummy", 10L);
    }

    @Test
    void shouldOnlyExecuteIfBranchWhenConditionIsTrue() {
        var state = mock(State.class);
        var command = new If(Bool.TRUE,
                new Assign("a", new Const(1L)),
                new Assign("b", new Const(2L)));

        newCommandInterpreterUnderTest().execute(command, state);

        verify(state).put("a", 1L);
        verifyNoMoreInteractions(state);
    }

    @Test
    void shouldOnlyExecuteElseBranchWhenConditionIsFalse() {
        var state = mock(State.class);
        var command = new If(Bool.FALSE,
                new Assign("a", new Const(1L)),
                new Assign("b", new Const(2L)));

        newCommandInterpreterUnderTest().execute(command, state);

        verify(state).put("b", 2L);
        verifyNoMoreInteractions(state);
    }

    @Test
    void shouldExecuteBothChildCommandsOfSequenceInOrder() {
        var state = mock(State.class);
        var inOrder = inOrder(state);

        var command = new Sequence(
                new Assign("a", new Const(1L)),
                new Assign("b", new Const(2L)));

        newCommandInterpreterUnderTest().execute(command, state);

        inOrder.verify(state).put("a", 1L);
        inOrder.verify(state).put("b", 2L);
    }

    @Test
    void shouldNotEnterLoopBodyWhenConditionIsInitiallyFalse() {
        var state = mock(State.class);
        var command = new While(Bool.FALSE,
                new Assign("a", new Const(1L)));

        newCommandInterpreterUnderTest().execute(command, state);

        verifyNoInteractions(state);
    }

    @Test
    void shouldOnlyEnterLoopBodyOnceWhenLoopConditionIsInitiallyTrueAndBecomesFalseAfterOneIteration() {
        var state = mock(State.class);
        var command = new While(Bool.FALSE,
                new Assign("a", new Const(1L)));

        var mockBooleanExpressionInterpreter = mock(BooleanExpressionInterpreter.class);
        when(mockBooleanExpressionInterpreter.evaluate(any(), any())).thenReturn(true).thenReturn(false);
        var commandInterpreterUnderTest =
                new CommandInterpreter(stubArithmeticExpressionInterpreter(), mockBooleanExpressionInterpreter);

        commandInterpreterUnderTest.execute(command, state);

        verify(mockBooleanExpressionInterpreter, times(2)).evaluate(Bool.FALSE, state);
        verify(state).put("a", 1L);
        verifyNoMoreInteractions(state);
    }

    private CommandInterpreter newCommandInterpreterUnderTest() {
        return new CommandInterpreter(stubArithmeticExpressionInterpreter(), stubBooleanExpressionInterpreter());
    }

    private ArithmeticExpressionInterpreter stubArithmeticExpressionInterpreter() {
        return new ArithmeticExpressionInterpreter() {
            @Override
            public long evaluate(ArithmeticExpression expression, State state) {
                if (expression instanceof Const constant) {
                    return constant.number();
                }
                throw new IllegalArgumentException("This stub can only handle constant arithmetic expressions");
            }
        };
    }

    private BooleanExpressionInterpreter stubBooleanExpressionInterpreter() {
        return new BooleanExpressionInterpreter() {
            @Override
            public boolean evaluate(BooleanExpression expression, State state) {
                if (expression instanceof Bool bool) {
                    return bool == Bool.TRUE;
                }
                throw new IllegalArgumentException("This stub can only handle true/false const boolean expressions");
            }
        };
    }

}
