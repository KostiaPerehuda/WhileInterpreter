package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.*;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Assign;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Command;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Sequence;
import com.github.kostiaperehuda.whileinterpreter.ast.cmd.Skip;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InterpreterTest {

    @Test
    void shouldNotAffectProgramStateWhenExecutingSkipInstruction() {
        Map<String, BigInteger> initialState = Collections.emptyMap(); // Immutable
        Command skip = new Skip();

        var finalState = new Interpreter(initialState).execute(skip);

        assertEquals(initialState, finalState);
    }

    @ParameterizedTest
    @MethodSource("arithmeticExpressionsWithExpectedResults")
    void shouldEvaluateArithmeticExpressionAndPutItsResultIntoTheProgramStateWhenExecutingAssignInstruction(
            ArithmeticExpression expression, BigInteger expectedResult
    ) {
        Command assignment = new Assign("result", expression);

        Map<String, BigInteger> initialState = new HashMap<>();

        Interpreter interpreter = new Interpreter(initialState);
        Map<String, BigInteger> finalState = interpreter.execute(assignment);

        assertEquals(Map.of("result", expectedResult), finalState);
    }

    static Stream<Arguments> arithmeticExpressionsWithExpectedResults() {
        return Stream.of(
                Arguments.of(new Const(BigInteger.TEN), BigInteger.TEN),
                Arguments.of(new Plus(new Const(BigInteger.ONE), new Const(BigInteger.TWO)), BigInteger.valueOf(3)),
                Arguments.of(new Minus(new Const(BigInteger.ONE), new Const(BigInteger.TWO)), BigInteger.valueOf(-1)),
                Arguments.of(new Multiply(new Const(BigInteger.TEN), new Const(BigInteger.TWO)), BigInteger.valueOf(20))
        );
    }

    @Test
    void shouldExtractTheValueFromTheVariableByQueryingTheState() {
        Command assignment = new Assign("result", new Variable("variable"));

        Map<String, BigInteger> initialState = new HashMap<>();
        initialState.put("variable", BigInteger.TWO);

        Interpreter interpreter = new Interpreter(initialState);
        Map<String, BigInteger> finalState = interpreter.execute(assignment);

        assertEquals(Map.of("variable", BigInteger.TWO, "result", BigInteger.TWO), finalState);
    }

    @Test
    void shouldThrowExceptionWhenExtractingUndefinedVariableFromTheState() {
        Command assignment = new Assign("result", new Variable("variable"));

        Map<String, BigInteger> initialState = Collections.emptyMap();

        Interpreter interpreter = new Interpreter(initialState);
        assertThrows(UndefinedVariableException.class, () -> interpreter.execute(assignment));
    }

    @Test
    void shouldExecuteBothChildCommandsOfSequence() {
        Command sequence = new Sequence(
                new Assign("one", new Const(BigInteger.ONE)),
                new Assign("two", new Const(BigInteger.TWO)));

        Map<String, BigInteger> initialState = new HashMap<>();

        Interpreter interpreter = new Interpreter(initialState);
        Map<String, BigInteger> finalState = interpreter.execute(sequence);

        assertEquals(Map.of("one", BigInteger.ONE, "two", BigInteger.TWO), finalState);
    }

    @Test
    void shouldExecuteChildCommandsOfSequenceInOrder() {
        Command sequence = new Sequence(
                new Assign("one", new Const(BigInteger.ONE)),
                new Assign("one", new Const(BigInteger.TWO)));

        Map<String, BigInteger> initialState = new HashMap<>();

        Interpreter interpreter = new Interpreter(initialState);
        Map<String, BigInteger> finalState = interpreter.execute(sequence);

        assertEquals(Map.of("one", BigInteger.TWO), finalState);
    }

}