package com.happyprog.pairhero.views;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.eclipse.swt.widgets.Composite;
import org.junit.Before;
import org.junit.Test;

public class ProgrammerTest {

	private Composite parent;
	private StubbedProgrammer programmer;

	@Before
	public void before() {
		parent = mock(Composite.class);
		programmer = new StubbedProgrammer(parent);
	}

	@Test
	public void whenDriving() throws Exception {
		programmer.drive();
		assertEquals(Programmer.Role.Driving, programmer.currentRole);
	}

	@Test
	public void whenObserving() throws Exception {
		programmer.observe();
		assertEquals(Programmer.Role.Observing, programmer.currentRole);
	}

	@Test
	public void switchingRole() throws Exception {

		programmer.drive();
		assertEquals(Programmer.Role.Driving, programmer.currentRole);

		programmer.switchRole();
		assertEquals(Programmer.Role.Observing, programmer.currentRole);

		programmer.switchRole();
		assertEquals(Programmer.Role.Driving, programmer.currentRole);
	}

	@Test
	public void whenDriving_updateTimeAtKeyboard() throws Exception {
		programmer.drive();

		programmer.onTimeChange();

		assertTrue(programmer.timeAtKeyboardWasUpdated);
	}

	@Test
	public void whenObserving_timeAtKeyboardDoesNotUpdate() throws Exception {
		programmer.observe();

		programmer.onTimeChange();

		assertFalse(programmer.timeAtKeyboardWasUpdated);
	}

	class StubbedProgrammer extends Programmer {

		Role currentRole;
		boolean timeAtKeyboardWasUpdated;

		public StubbedProgrammer(Composite parent) {
			super(parent);
		}

		@Override
		void updateRole(Role role) {
			this.currentRole = role;
		}

		@Override
		void updateTimeAtKeyboard(int seconds) {
			timeAtKeyboardWasUpdated = true;
		}

		@Override
		void initializeUIControls(Composite parent) {
			// do nothing, please
		}

	}

}
