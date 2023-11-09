package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.RunResults.Variable;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteProgramActionTest {

    @Test
    void shouldExecuteProgramStringContainedByCurrentlyActiveEditorTabAndStoreResultsInNewRunResultsTab() {
        ViewModel viewModel = new ViewModel();

        Editor.Tab tab = viewModel.editor().openNewTab();
        tab.setText("a = 1");
        viewModel.editor().select(tab);


        new ExecuteProgramAction(viewModel).run();


        assertEquals(List.of(new Variable("a", BigInteger.ONE)),
                     viewModel.runResults().getRunResultList().getLast().getProgramState());
    }

    @Test
    void shouldReportSuccessMessageToStatusBarAfterSuccessfullyExecutingTheProgram() {
        ViewModel viewModel = new ViewModel();
        viewModel.statusBar().setStatus("Should change!");

        Editor.Tab tab = viewModel.editor().openNewTab();
        tab.setText("a = 1");
        viewModel.editor().select(tab);


        new ExecuteProgramAction(viewModel).run();


        assertNotEquals("Should change!", viewModel.statusBar().getStatus());
    }

    @Test
    void shouldReportErrorToStatusBarWhenFailingToExecuteProgramStringInCurrentlyActiveTab() {
        ViewModel viewModel = new ViewModel();
        viewModel.statusBar().setStatus("Should change!");

        Editor.Tab tab = viewModel.editor().openNewTab();
        tab.setText("program with invalid syntax");
        viewModel.editor().select(tab);


        new ExecuteProgramAction(viewModel).run();


        assertNotEquals("Should change!", viewModel.statusBar().getStatus());
    }

    @Test
    void shouldDoNothingWhenNoEditorTabIsSelected() {
        ViewModel viewModel = new ViewModel();
        viewModel.statusBar().setStatus("Should not change!");

        new ExecuteProgramAction(viewModel).run();

        assertTrue(viewModel.runResults().getRunResultList().isEmpty());
        assertEquals("Should not change!", viewModel.statusBar().getStatus());
    }

}