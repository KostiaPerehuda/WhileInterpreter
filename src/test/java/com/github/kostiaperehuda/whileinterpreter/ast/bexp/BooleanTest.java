package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
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
