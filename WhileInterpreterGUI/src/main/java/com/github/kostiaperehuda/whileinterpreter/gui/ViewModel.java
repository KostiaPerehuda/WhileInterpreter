package com.github.kostiaperehuda.whileinterpreter.gui;

import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;

public record ViewModel(
        Editor editor
) {

    public ViewModel() {
        this(new Editor());
    }

}
