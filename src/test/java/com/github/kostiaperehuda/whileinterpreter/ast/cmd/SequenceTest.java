package com.github.kostiaperehuda.whileinterpreter.ast.cmd;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.kostiaperehuda.whileinterpreter.state.State;

@RunWith(MockitoJUnitRunner.class)
public class SequenceTest {

	@Mock
	private State state;

	@Mock
	private Command command;

	@Test
	public void sequenceOfZeroInstructionsMustNotAffectProgramState() {
		new Sequence().execute(state);

		verifyNoInteractions(state);
	}

	@Test
	public void sequenceOfOneInstructionMustOnlyExecuteTheInstructionOnce() {
		new Sequence(command).execute(state);

		verify(command, times(1)).execute(state);
		verifyNoInteractions(state);
	}

	@Test
	public void sequenceOfThreeInstructionsMustExecuteTheInstructionThreeTimes() {
		new Sequence(command, command, command).execute(state);

		verify(command, times(3)).execute(state);
		verifyNoInteractions(state);
	}

}
