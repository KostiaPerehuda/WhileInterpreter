package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Pair;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ExecuteProgramActionTest {

    @Test
    void shouldExecuteProgramStringContainedByCurrentlyActiveEditorTabAndStoreResultsInNewRunResultsTab() {
        ViewModel viewModel = new ViewModel();

        Editor.Tab tab = viewModel.editor().openNewTab();
        tab.setText("a = 1");
        viewModel.editor().select(tab);


        new ExecuteProgramAction(viewModel).run();


        assertEquals(List.of(new Pair<>("a", BigInteger.ONE)),
                     viewModel.runResults().getRunResults().getLast().getProgramState());
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

}