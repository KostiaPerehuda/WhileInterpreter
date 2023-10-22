package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.ast.Command;
import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;
import com.github.kostiaperehuda.whileinterpreter.interpreter.Interpreter;
import com.github.kostiaperehuda.whileinterpreter.parser.ProgramParser;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

public class ExecuteProgramAction implements Runnable {

    private final ViewModel view;

    public ExecuteProgramAction(ViewModel view) {
        this.view = view;
    }

    @Override
    public void run() {
        try {
            Optional<String> programOptional = Optional.of(view)
                    .map(ViewModel::editor)
                    .map(Editor::activeTabProperty)
                    .map(ObjectProperty::get)
                    .map(Editor.Tab::textProperty)
                    .map(StringProperty::get);

            programOptional.ifPresent(program -> {
                Command command = ProgramParser.parseString(program);
                Interpreter interpreter = new Interpreter();
                Map<String, BigInteger> result = interpreter.execute(command);

                view.runResults().add(result);
                view.statusBar().statusProperty().set("Executed current program");
            });

        } catch (RuntimeException e) {
            e.printStackTrace();
            view.statusBar().statusProperty().set("Failed to execute current program due to " + e.getMessage());
        }
    }

}
