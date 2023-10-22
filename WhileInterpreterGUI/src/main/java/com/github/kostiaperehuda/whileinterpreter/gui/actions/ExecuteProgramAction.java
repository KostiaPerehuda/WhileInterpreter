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
        Optional<String> programOptional = Optional.ofNullable(view)
                .map(ViewModel::editor)
                .map(Editor::activeTab)
                .map(ObjectProperty::get)
                .map(Editor.Tab::text)
                .map(StringProperty::get);

        programOptional.ifPresent(program -> {
            Command command = ProgramParser.parseString(program);
            Interpreter interpreter = new Interpreter();
            Map<String, BigInteger> result = interpreter.execute(command);
            view.editor().tabs().add(new Editor.Tab(result.toString()));
        });
    }

}
