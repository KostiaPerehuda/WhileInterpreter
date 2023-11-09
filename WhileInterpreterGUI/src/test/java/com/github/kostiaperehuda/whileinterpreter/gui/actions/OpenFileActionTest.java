package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.fileio.PathProvider;
import com.github.kostiaperehuda.whileinterpreter.gui.testdoubles.fileio.FakeInMemoryFileSystem;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OpenFileActionTest {

    @Test
    void shouldReadContentsOfTheFileIntoANewEditorTab() {
        ViewModel viewModel = new ViewModel();

        FakeInMemoryFileSystem fileSystem = new FakeInMemoryFileSystem();
        fileSystem.writeString(Path.of("filepath"), "Dummy File");

        PathProvider pathProvider = () -> Optional.of(Path.of("filepath"));


        new OpenFileAction(viewModel, fileSystem, pathProvider).run();


        assertEquals(1, viewModel.editor().getTabs().size());
        assertEquals("Dummy File", viewModel.editor().getTabs().getFirst().getText());
    }

    @Test
    void shouldReportSuccessMessageToStatusBarAfterSuccessfullyOpeningAFileInANewANewEditorTab() {
        ViewModel viewModel = new ViewModel();
        viewModel.statusBar().setStatus("Should change!");

        FakeInMemoryFileSystem fileSystem = new FakeInMemoryFileSystem();
        fileSystem.writeString(Path.of("filepath"), "Dummy File");

        PathProvider pathProvider = () -> Optional.of(Path.of("filepath"));


        new OpenFileAction(viewModel, fileSystem, pathProvider).run();


        assertNotEquals("Should change!", viewModel.statusBar().getStatus());
    }

    @Test
    void shouldReportErrorToStatusBarWhenFailingToReadContentsOfTheFileIntoANewEditorTab() {
        ViewModel viewModel = new ViewModel();
        viewModel.statusBar().setStatus("Should change!");

        FakeInMemoryFileSystem fileSystem = new FakeInMemoryFileSystem();

        PathProvider pathProvider = () -> Optional.of(Path.of("filepath"));


        new OpenFileAction(viewModel, fileSystem, pathProvider).run();


        assertNotEquals("Should change!", viewModel.statusBar().getStatus());
    }

    @Test
    void shouldDoNothingWhenNoFilepathIsProvided() {
        ViewModel viewModel = new ViewModel();
        viewModel.statusBar().setStatus("Should not change!");

        new OpenFileAction(viewModel, null, Optional::empty).run();

        assertTrue(viewModel.runResults().getRunResultList().isEmpty());
        assertEquals("Should not change!", viewModel.statusBar().getStatus());
    }

}