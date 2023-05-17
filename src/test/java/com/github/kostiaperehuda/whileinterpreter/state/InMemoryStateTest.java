package com.github.kostiaperehuda.whileinterpreter.state;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InMemoryStateTest {

    private State state;

    @Before
    public void setUp() {
        state = new InMemoryState();
    }

    @Test
    public void shouldSuccessfullyPutAndRetrieveDataFromTheMap() {
        state.put("dummy", 1L);

        assertEquals(1L, state.get("dummy"));
    }

    @Test(expected = UndefinedVariableException.class)
    public void shouldThrowErrorWhenGettingUndefinedVariableFromTheMap() {
        state.get("dummy");
    }

    @Test
    public void shouldPrintEmptyMapIfMapIsEmpty() {
        assertEquals("{}", state.toString());
    }

    @Test
    public void shouldPrintAllEntriesInTheNonEmptyMap() {
        state.put("dummy1", 1L);
        state.put("dummy2", 2L);

        assertEquals("{\n\tdummy1 : 1;\n\tdummy2 : 2;\n}", state.toString());
    }

}
