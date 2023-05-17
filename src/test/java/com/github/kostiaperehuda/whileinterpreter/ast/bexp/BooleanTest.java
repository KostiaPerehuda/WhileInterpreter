package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BooleanTest {

    @Test
    public void trueConstantShouldEvaluateToTrue() {
        assertTrue(Boolean.TRUE.eval(null));
    }

    @Test
    public void falseConstantShouldEvaluateToFalse() {
        assertFalse(Boolean.FALSE.eval(null));
    }

}
