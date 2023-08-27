package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {

    @Test
    void shouldNotModifyProgramStateWhenExecutingSkipCommand() {
        Command skip = new Skip();
        Map<String, BigInteger> initialState = Collections.emptyMap(); // Immutable

        Interpreter interpreter = new Interpreter(initialState);
        Map<String, BigInteger> finalState = interpreter.execute(skip);

        assertEquals(initialState, finalState);
    }

    @ParameterizedTest
    @MethodSource("arithmeticExpressionsWithExpectedResults")
    void shouldPutTheResultOfArithmeticExpressionIntoTheProgramStateWhenExecutingAssignCommand(
            ArithmeticExpression expression, BigInteger expectedResult
    ) {
        Command assignment = new Assign(new Variable("result"), expression);

        Interpreter interpreter = new Interpreter();
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
    void shouldExtractTheValueFromTheVariableByQueryingTheProgramState() {
        Command assignment = new Assign(new Variable("result"), new Variable("variable"));

        Map<String, BigInteger> initialState = new HashMap<>();
        initialState.put("variable", BigInteger.TWO);

        Interpreter interpreter = new Interpreter(initialState);
        Map<String, BigInteger> finalState = interpreter.execute(assignment);

        assertEquals(Map.of("variable", BigInteger.TWO, "result", BigInteger.TWO), finalState);
    }

    @Test
    void shouldThrowExceptionWhenExtractingUndefinedVariableFromTheProgramState() {
        Command assignment = new Assign(new Variable("result"), new Variable("variable"));

        Interpreter interpreter = new Interpreter();
        assertThrows(UndefinedVariableException.class, () -> interpreter.execute(assignment));
    }

    @Test
    void shouldExecuteBothChildCommandsOfSequenceCommand() {
        Command sequence = new Sequence(
                new Assign(new Variable("one"), new Const(BigInteger.ONE)),
                new Assign(new Variable("two"), new Const(BigInteger.TWO)));

        Interpreter interpreter = new Interpreter();
        Map<String, BigInteger> finalState = interpreter.execute(sequence);

        assertEquals(Map.of("one", BigInteger.ONE, "two", BigInteger.TWO), finalState);
    }

    @Test
    void shouldExecuteChildCommandsOfSequenceCommandInOrder() {
        Command sequence = new Sequence(
                new Assign(new Variable("one"), new Const(BigInteger.ONE)),
                new Assign(new Variable("one"), new Const(BigInteger.TWO)));

        Interpreter interpreter = new Interpreter();
        Map<String, BigInteger> finalState = interpreter.execute(sequence);

        assertEquals(Map.of("one", BigInteger.TWO), finalState);
    }

    @Test
    void shouldOnlyExecuteIfBranchWhenConditionIsTrue() {
        Command ifStatement = new If(Bool.TRUE,
                new Assign(new Variable("ifBranchTaken"), new Const(BigInteger.ONE)),
                new Assign(new Variable("elseBranchTaken"), new Const(BigInteger.ONE)));

        Interpreter interpreter = new Interpreter();
        Map<String, BigInteger> finalState = interpreter.execute(ifStatement);

        assertEquals(Map.of("ifBranchTaken", BigInteger.ONE), finalState);
    }

    @Test
    void shouldOnlyExecuteElseBranchWhenConditionIsFalse() {
        Command ifStatement = new If(Bool.FALSE,
                new Assign(new Variable("ifBranchTaken"), new Const(BigInteger.ONE)),
                new Assign(new Variable("elseBranchTaken"), new Const(BigInteger.ONE)));

        Interpreter interpreter = new Interpreter();
        Map<String, BigInteger> finalState = interpreter.execute(ifStatement);

        assertEquals(Map.of("elseBranchTaken", BigInteger.ONE), finalState);
    }

    @ParameterizedTest
    @MethodSource("compoundBooleanExpressionsWithExpectedResults")
    void shouldCorrectlyEvaluateCompoundBooleanExpressionWhenExecutingIfCommand(
            BooleanExpression expression, boolean expectedResult
    ) {
        Command ifStatement = new If(expression,
                new Assign(new Variable("result"), new Const(BigInteger.ONE)),
                new Assign(new Variable("result"), new Const(BigInteger.ZERO)));

        Interpreter interpreter = new Interpreter();
        Map<String, BigInteger> finalState = interpreter.execute(ifStatement);

        BigInteger expectedResultValue = expectedResult ? BigInteger.ONE : BigInteger.ZERO;
        assertEquals(Map.of("result", expectedResultValue), finalState);
    }

    static Stream<Arguments> compoundBooleanExpressionsWithExpectedResults() {
        return Stream.of(
                Arguments.of(new Not(Bool.TRUE), false),
                Arguments.of(new Not(Bool.FALSE), true),
                Arguments.of(new And(Bool.TRUE, Bool.TRUE), true),
                Arguments.of(new And(Bool.TRUE, Bool.FALSE), false),
                Arguments.of(new And(Bool.FALSE, Bool.TRUE), false),
                Arguments.of(new And(Bool.FALSE, Bool.FALSE), false),
                Arguments.of(new Equals(new Const(BigInteger.ONE), new Const(BigInteger.ONE)), true),
                Arguments.of(new Equals(new Const(BigInteger.ONE), new Const(BigInteger.TWO)), false),
                Arguments.of(new LessThanOrEqual(new Const(BigInteger.ONE), new Const(BigInteger.TWO)), true),
                Arguments.of(new LessThanOrEqual(new Const(BigInteger.ONE), new Const(BigInteger.ONE)), true),
                Arguments.of(new LessThanOrEqual(new Const(BigInteger.TWO), new Const(BigInteger.ONE)), false)
        );
    }

    @Test
    void shouldNeverEnterWhileLoopBodyWhenLoopConditionIsInitiallyFalse() {
        Command whileCommand = new While(Bool.FALSE,
                new Assign(new Variable("variable"), new Const(BigInteger.ONE)));

        Interpreter interpreter = new Interpreter();
        Map<String, BigInteger> finalState = interpreter.execute(whileCommand);

        assertTrue(finalState.isEmpty());
    }

    @Test
    void shouldContinueToEnterWhileLoopBodyUntilLoopConditionBecomesFalseWhenLoopConditionIsInitiallyTrue() {
        Command whileCommand = new While(
                new LessThanOrEqual(new Variable("counter"), new Const(BigInteger.ONE)),
                new Assign(new Variable("counter"), new Plus(new Variable("counter"), new Const(BigInteger.ONE)))
        );

        Map<String, BigInteger> initialState = new HashMap<>();
        initialState.put("counter", BigInteger.ZERO);

        Interpreter interpreter = new Interpreter(initialState);
        Map<String, BigInteger> finalState = interpreter.execute(whileCommand);

        assertEquals(Map.of("counter", BigInteger.TWO), finalState);
    }

}