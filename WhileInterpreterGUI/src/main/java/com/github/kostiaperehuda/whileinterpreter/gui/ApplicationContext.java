package com.github.kostiaperehuda.whileinterpreter.gui;

public class ApplicationContext {

    private final ViewModel viewModel;

    public ApplicationContext(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    public <T> T getNewInstanceOf(Class<T> type) {
        T newInstance;

        if ((newInstance = injectViewModelIntoNewInstanceOf(type)) != null) return newInstance;
        if ((newInstance = injectContextIntoNewInstanceOf(type)) != null) return newInstance;
        if ((newInstance = createNewInstanceOf(type)) != null) return newInstance;

        throw new ObjectCreationException(type);
    }

    private <T> T injectViewModelIntoNewInstanceOf(Class<T> type) {
        try {
            return type.getDeclaredConstructor(viewModel.getClass()).newInstance(viewModel);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }

    private <T> T injectContextIntoNewInstanceOf(Class<T> type) {
        try {
            return type.getDeclaredConstructor(getClass()).newInstance(this);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }


    private <T> T createNewInstanceOf(Class<T> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }

    public static class ObjectCreationException extends RuntimeException {

        private static final String MESSAGE_TEMPLATE =
                "%s must either have a no-args constructor or a constructor accepting " +
                        ApplicationContext.class.getName();

        public ObjectCreationException(Class<?> type) {
            super(MESSAGE_TEMPLATE.formatted(type.getName()));
        }

    }

}
