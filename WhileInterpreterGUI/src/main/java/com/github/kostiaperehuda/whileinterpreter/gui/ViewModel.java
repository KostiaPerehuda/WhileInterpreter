package com.github.kostiaperehuda.whileinterpreter.gui;

import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.StatusBar;

public record ViewModel(
        Editor editor,
        StatusBar statusBar
) {

    public ViewModel() {
        this(new Editor(), new StatusBar());
    }

}
