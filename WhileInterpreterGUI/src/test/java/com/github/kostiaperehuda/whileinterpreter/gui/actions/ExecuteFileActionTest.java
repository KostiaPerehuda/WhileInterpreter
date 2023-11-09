package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.PathProvider;
import com.github.kostiaperehuda.whileinterpreter.gui.testdoubles.fileio.FakeInMemoryFileSystem;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.RunResults.Variable;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteFileActionTest {

    @Test
    void shouldExecuteTheProgramContainedByTheSpecifiedFileAndStoreResultsInNewRunResultsTab() {
        ViewModel viewModel = new ViewModel();

        FakeInMemoryFileSystem fileSystem = new FakeInMemoryFileSystem();
        fileSystem.writeString(Path.of("filepath"), "a = 1");

        PathProvider pathProvider = () -> Optional.of(Path.of("filepath"));


        new ExecuteFileAction(viewModel, fileSystem, pathProvider).run();


        assertEquals(List.of(new Variable("a", BigInteger.ONE)),
                     viewModel.runResults().getRunResultList().getLast().getProgramState());
    }

    @Test
    void shouldReportSuccessMessageToStatusBarAfterSuccessfulExecution() {
        ViewModel viewModel = new ViewModel();
        viewModel.statusBar().setStatus("Should change!");

        FakeInMemoryFileSystem fileSystem = new FakeInMemoryFileSystem();
        fileSystem.writeString(Path.of("filepath"), "a = 1");

        PathProvider pathProvider = () -> Optional.of(Path.of("filepath"));


        new ExecuteFileAction(viewModel, fileSystem, pathProvider).run();


        assertNotEquals("Should change!", viewModel.statusBar().getStatus());
    }

    @Test
    void shouldReportErrorToStatusBarWhenFailingToReadTheFile() {
        ViewModel viewModel = new ViewModel();
        viewModel.statusBar().setStatus("Should change!");

        FakeInMemoryFileSystem fileSystem = new FakeInMemoryFileSystem();

        PathProvider pathProvider = () -> Optional.of(Path.of("filepath"));


        new ExecuteFileAction(viewModel, fileSystem, pathProvider).run();


        assertNotEquals("Should change!", viewModel.statusBar().getStatus());
    }

    @Test
    void shouldDoNothingWhenNoFilepathIsProvided() {
        ViewModel viewModel = new ViewModel();
        viewModel.statusBar().setStatus("Should not change!");

        new ExecuteFileAction(viewModel, null, Optional::empty).run();

        assertTrue(viewModel.runResults().getRunResultList().isEmpty());
        assertEquals("Should not change!", viewModel.statusBar().getStatus());
    }

}