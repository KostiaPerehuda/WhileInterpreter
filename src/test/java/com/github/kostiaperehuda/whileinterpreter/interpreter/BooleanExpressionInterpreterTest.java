package com.github.kostiaperehuda.whileinterpreter.interpreter;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.github.kostiaperehuda.whileinterpreter.ast.aexp.Const;
import com.github.kostiaperehuda.whileinterpreter.ast.bexp.Boolean;
import com.github.kostiaperehuda.whileinterpreter.ast.bexp.*;
import com.github.kostiaperehuda.whileinterpreter.state.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BooleanExpressionInterpreterTest {

    @Test
    void shouldEvaluateTrueConstantToTrue() {
        assertTrue(newBooleanExpressionInterpreterUnderTest().evaluate(Boolean.TRUE, null));
    }

    @Test
    void shouldEvaluateFalseConstantToFalse() {
        assertFalse(newBooleanExpressionInterpreterUnderTest().evaluate(Boolean.FALSE, null));
    }

    @Test
    void shouldEvaluateNotOperatorToTrueWhenItsOperandEvaluatesToFalse() {
        assertTrue(newBooleanExpressionInterpreterUnderTest().evaluate(new Not(Boolean.FALSE), null));
    }

    @Test
    void shouldEvaluateNotOperatorToFalseWhenItsOperandEvaluatesToTrue() {
        assertFalse(newBooleanExpressionInterpreterUnderTest().evaluate(new Not(Boolean.TRUE), null));
    }

    @Test
    void shouldEvaluateEqualsOperatorToTrueWhenItsOperandsEvaluateToTheSameValue() {
        var equals = new Equals(new Const(1L), new Const(1L));

        var result = newBooleanExpressionInterpreterUnderTest().evaluate(equals, null);

        assertTrue(result);
    }

    @Test
    void shouldEvaluateEqualsOperatorToFalseWhenItsOperandsEvaluateToDifferentValues() {
        var equals = new Equals(new Const(1L), new Const(2L));

        var result = newBooleanExpressionInterpreterUnderTest().evaluate(equals, null);

        assertFalse(result);
    }

    @Test
    void shouldEvaluateLessThanOrEqualToOperatorToTrueWhenItsOperandsEvaluateToTheSameValue() {
        var lessThanOrEqual = new LessThanOrEqual(new Const(1L), new Const(1L));

        var result = newBooleanExpressionInterpreterUnderTest().evaluate(lessThanOrEqual, null);

        assertTrue(result);
    }

    @Test
    void shouldEvaluateLessThanOrEqualToOperatorToTrueWhenItsLeftOperandIsSmallerThanItsRightOperand() {
        var lessThanOrEqual = new LessThanOrEqual(new Const(1L), new Const(2L));

        var result = newBooleanExpressionInterpreterUnderTest().evaluate(lessThanOrEqual, null);

        assertTrue(result);
    }

    @Test
    void shouldEvaluateLessThanOrEqualToOperatorToTrueWhenItsLeftOperandIsGreaterThanItsRightOperand() {
        var lessThanOrEqual = new LessThanOrEqual(new Const(2L), new Const(1L));

        var result = newBooleanExpressionInterpreterUnderTest().evaluate(lessThanOrEqual, null);

        assertFalse(result);
    }

    @Test
    void shouldEvaluateAndOperatorToTrueWhenItsOperandsEvaluateToTrue() {
        var trueAndTrue = new And(Boolean.TRUE, Boolean.TRUE);

        var result = newBooleanExpressionInterpreterUnderTest().evaluate(trueAndTrue, null);

        assertTrue(result);
    }

    @Test
    void shouldEvaluateAndOperatorToFalseWhenItsLeftOperandEvaluatesToTrueAndItsRightOperandEvaluatesToFalse() {
        var trueAndFalse = new And(Boolean.TRUE, Boolean.FALSE);

        var result = newBooleanExpressionInterpreterUnderTest().evaluate(trueAndFalse, null);

        assertFalse(result);
    }

    @Test
    void shouldEvaluateAndOperatorToFalseWhenItsLeftOperandEvaluatesToFalse() {
        var falseAndSomething = new And(Boolean.FALSE, Boolean.TRUE);

        var result = newBooleanExpressionInterpreterUnderTest().evaluate(falseAndSomething, null);

        assertFalse(result);
    }

    private BooleanExpressionInterpreter newBooleanExpressionInterpreterUnderTest() {
        var stubArithmeticExpressionInterpreter = new ArithmeticExpressionInterpreter() {
            @Override
            public long evaluate(ArithmeticExpression expression, State state) {
                if (expression instanceof Const constant) {
                    return constant.number();
                }
                throw new IllegalArgumentException("This stub can only handle constant arithmetic expressions");
            }
        };

        return new BooleanExpressionInterpreter(stubArithmeticExpressionInterpreter);
    }

}
