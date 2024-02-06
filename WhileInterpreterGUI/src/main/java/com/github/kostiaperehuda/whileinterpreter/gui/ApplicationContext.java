package com.github.kostiaperehuda.whileinterpreter.gui;

public class ApplicationContext {

    private final ViewModel viewModel;

    public ApplicationContext(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

}
