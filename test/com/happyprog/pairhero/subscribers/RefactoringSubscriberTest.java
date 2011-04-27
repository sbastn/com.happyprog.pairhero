package com.happyprog.pairhero.subscribers;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptorProxy;
import org.eclipse.ltk.core.refactoring.history.IRefactoringHistoryService;
import org.eclipse.ltk.core.refactoring.history.RefactoringHistory;
import org.junit.Before;
import org.junit.Test;

import com.happyprog.pairhero.game.Game;
import com.happyprog.pairhero.subscribers.RefactoringSubscriber;

public class RefactoringSubscriberTest {

	private Game game;
	private IRefactoringHistoryService historyService;
	private RefactoringHistory refactoringHistory;
	private StubbedRefactorSubscriber refactorSubscriber;

	@Before
	public void before() {
		game = mock(Game.class);
		historyService = mock(IRefactoringHistoryService.class);
		refactoringHistory = mock(RefactoringHistory.class);
		refactorSubscriber = new StubbedRefactorSubscriber(historyService);
	}

	@Test
	public void connectsAndDisconnectToRefactoringService() throws Exception {
		when(historyService.getWorkspaceHistory(anyLong(), anyLong(), isA(IProgressMonitor.class))).thenReturn(
				refactoringHistory);

		RefactoringDescriptorProxy[] proxy = {};
		when(refactoringHistory.getDescriptors()).thenReturn(proxy);

		refactorSubscriber.extractRefactoringHistory();

		verify(historyService).connect();
		verify(historyService).disconnect();
	}

	@Test
	public void doesNotNotifyObserverIfNoRefactoringHasHappened() throws Exception {
		when(historyService.getWorkspaceHistory(anyLong(), anyLong(), isA(IProgressMonitor.class))).thenReturn(
				refactoringHistory);

		RefactoringDescriptorProxy[] proxy = {};
		when(refactoringHistory.getDescriptors()).thenReturn(proxy);

		refactorSubscriber.subscribe(game);
		refactorSubscriber.extractRefactoringHistory();

		verify(game, never()).onRefactoring();
	}

	@Test
	public void notifiesObserverIfARefactorHappened() throws Exception {
		when(historyService.getWorkspaceHistory(anyLong(), anyLong(), isA(IProgressMonitor.class))).thenReturn(
				refactoringHistory);

		RefactoringDescriptorProxy[] proxy = { mock(RefactoringDescriptorProxy.class) };
		when(refactoringHistory.getDescriptors()).thenReturn(proxy);

		refactorSubscriber.subscribe(game);
		refactorSubscriber.extractRefactoringHistory();

		verify(game).onRefactoring();
	}

	class StubbedRefactorSubscriber extends RefactoringSubscriber {
		public StubbedRefactorSubscriber(IRefactoringHistoryService historyService) {
			super(historyService);
		}

		@Override
		void startListener() {
			// Do nothing on purpose to avoid eclipse async to kick in
		}
	}
}
