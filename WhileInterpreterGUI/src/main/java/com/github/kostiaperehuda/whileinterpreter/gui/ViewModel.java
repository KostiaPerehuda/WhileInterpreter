package com.github.kostiaperehuda.whileinterpreter.gui;

import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.RunResults;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.StatusBar;

public record ViewModel(
        Editor editor,
        StatusBar statusBar,
        RunResults runResults
) {

    public ViewModel() {
        this(new Editor(), new StatusBar(), new RunResults());
    }

}
