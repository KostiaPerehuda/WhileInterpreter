package com.github.kostiaperehuda.whileinterpreter.gui.actions;

import com.github.kostiaperehuda.whileinterpreter.ast.Command;
import com.github.kostiaperehuda.whileinterpreter.gui.ViewModel;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.Editor;
import com.github.kostiaperehuda.whileinterpreter.gui.viewmodels.RunResults.RunResult;
import com.github.kostiaperehuda.whileinterpreter.interpreter.Interpreter;
import com.github.kostiaperehuda.whileinterpreter.parser.ProgramParser;

import java.math.BigInteger;
import java.util.Map;

public class ExecuteProgramAction implements Runnable {

    private final ViewModel view;

    public ExecuteProgramAction(ViewModel view) {
        this.view = view;
    }

    @Override
    public void run() {
        try {
            Editor.Tab activeTab = view.editor().getSelectedTab();
            if (activeTab == null) return;

            Command command = ProgramParser.parseString(activeTab.getText());
            Interpreter interpreter = new Interpreter();
            Map<String, BigInteger> result = interpreter.execute(command);

            view.runResults().getRunResults().add(RunResult.of(result));
            view.statusBar().setStatus("Executed current program");
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            view.statusBar().setStatus("Failed to execute current program due to " + e.getMessage());
        }
    }

}
