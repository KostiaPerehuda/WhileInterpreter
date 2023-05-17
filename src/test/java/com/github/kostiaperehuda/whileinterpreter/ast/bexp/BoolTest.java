package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BoolTest {

    @Test
    public void trueConstantShouldEvaluateToTrue() {
        assertTrue(Bool.TRUE.eval(null));
    }

    @Test
    public void falseConstantShouldEvaluateToFalse() {
        assertFalse(Bool.FALSE.eval(null));
    }

}
