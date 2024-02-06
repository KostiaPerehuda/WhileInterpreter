package com.github.kostiaperehuda.whileinterpreter.gui.experimental;

import com.github.kostiaperehuda.whileinterpreter.gui.config.FxControllerFactory;
import javafx.fxml.FXMLLoader;
import javafx.util.Builder;
import javafx.util.BuilderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public final class CustomJavaFXBuilderFactory implements BuilderFactory {

    private final FxControllerFactory fxControllerFactory;

    public CustomJavaFXBuilderFactory(FxControllerFactory fxControllerFactory) {
        this.fxControllerFactory = fxControllerFactory;
    }

    @Override
    public Builder<?> getBuilder(Class<?> type) {
        if (type.isAnnotationPresent(FxComponent.class)) {
            var customizer = new FxComponentCustomizer<>(fxControllerFactory.getNewInstanceOf(type));
            return (Builder.class.isAssignableFrom(type)) ? (Builder<?>) customizer.build() : customizer;
        }

        return null;
    }

    private class FxComponentCustomizer<T> implements Builder<T> {

        private final T component;
        private final FxComponent annotation;

        public FxComponentCustomizer(T component) {
            this.component = component;
            this.annotation = component.getClass().getAnnotation(FxComponent.class);
        }

        @Override
        public final T build() {
            loadFXMLIfRequired();

            return component;
        }

        private void loadFXMLIfRequired() {

            URL fxmlURL;

            if ("".equals(annotation.fxmlUrl())) {
                if ((fxmlURL = component.getClass()
                        .getResource(component.getClass().getSimpleName() + ".fxml")) == null)
                    return;
            } else if ((fxmlURL = component.getClass().getResource(annotation.fxmlUrl())) == null) {
                throw new RuntimeException(new FileNotFoundException(annotation.fxmlUrl()));
            }

            doLoadFXML(fxmlURL);
        }

        private void doLoadFXML(URL fxmlURL) {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

            fxmlLoader.setBuilderFactory(CustomJavaFXBuilderFactory.this);
            fxmlLoader.setController(component);
            fxmlLoader.setRoot(component);

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
