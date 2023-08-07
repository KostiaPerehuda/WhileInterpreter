package com.github.kostiaperehuda.whileinterpreter.state;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InMemoryStateTest {

    @Test
    void shouldSuccessfullyPutAndRetrieveDataFromTheMap() {
        var state = new InMemoryState();

        state.put("dummy", BigInteger.ONE);

        assertEquals(BigInteger.ONE, state.get("dummy"));
    }

    @Test
    void shouldThrowErrorWhenGettingUndefinedVariableFromTheMap() {
        var state = new InMemoryState();

        assertThrows(UndefinedVariableException.class, () -> state.get("dummy"));
    }

    @Test
    void shouldPrintEmptyMapIfMapIsEmpty() {
        var state = new InMemoryState();

        assertEquals("{}", state.toString());
    }

    @Test
    void shouldPrintAllEntriesInTheNonEmptyMap() {
        var state = new InMemoryState();

        state.put("dummy1", BigInteger.ONE);
        state.put("dummy2", BigInteger.TWO);

        assertEquals("{\n\tdummy1 : 1;\n\tdummy2 : 2;\n}", state.toString());
    }

}
