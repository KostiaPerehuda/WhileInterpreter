package com.github.kostiaperehuda.whileinterpreter.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InMemoryStateTest {

    @Test
    public void shouldSuccessfullyPutAndRetrieveDataFromTheMap() {
        var state = new InMemoryState();

        state.put("dummy", 1L);

        assertEquals(1L, state.get("dummy"));
    }

    @Test
    public void shouldThrowErrorWhenGettingUndefinedVariableFromTheMap() {
        var state = new InMemoryState();

        assertThrows(UndefinedVariableException.class, () -> state.get("dummy"));
    }

    @Test
    public void shouldPrintEmptyMapIfMapIsEmpty() {
        var state = new InMemoryState();

        assertEquals("{}", state.toString());
    }

    @Test
    public void shouldPrintAllEntriesInTheNonEmptyMap() {
        var state = new InMemoryState();

        state.put("dummy1", 1L);
        state.put("dummy2", 2L);

        assertEquals("{\n\tdummy1 : 1;\n\tdummy2 : 2;\n}", state.toString());
    }

}
