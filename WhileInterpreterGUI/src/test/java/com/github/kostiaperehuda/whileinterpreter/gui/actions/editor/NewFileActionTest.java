package com.github.kostiaperehuda.whileinterpreter.gui.actions.editor;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewFileActionTest {

    @Test
    void shouldAddANewTabToTheEditor() {
        ViewModel view = new ViewModel();
        int numberOfTabsBeforeAction = view.editor().getTabs().size();

        new NewFileAction(view).run();

        int numberOfTabsAfterAction = view.editor().getTabs().size();
        assertEquals(numberOfTabsBeforeAction + 1, numberOfTabsAfterAction);
    }

    @Test
    void shouldSelectTheNewlyAddedTab() {
        ViewModel view = new ViewModel();
        List<Editor.Tab> tabsBeforeAction = List.copyOf(view.editor().getTabs());

        new NewFileAction(view).run();

        Editor.Tab selectedTabAfterAction = view.editor().getSelectedTab();
        ObservableList<Editor.Tab> tabsAfterAction = view.editor().getTabs();

        assertNotNull(selectedTabAfterAction);
        assertFalse(tabsBeforeAction.contains(selectedTabAfterAction));
        assertTrue(tabsAfterAction.contains(selectedTabAfterAction));
    }
}
