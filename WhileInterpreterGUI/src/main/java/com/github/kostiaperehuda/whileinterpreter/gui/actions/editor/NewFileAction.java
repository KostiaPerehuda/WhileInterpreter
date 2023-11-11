package com.github.kostiaperehuda.whileinterpreter.gui.actions.editor;

import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;

public class NewFileAction implements Runnable {

    private final ViewModel view;

    public NewFileAction(ViewModel view) {this.view = view;}

    @Override
    public void run() {
        Editor.Tab tab = view.editor().openNewTab();
        view.editor().select(tab);
    }

}
