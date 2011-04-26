package com.happyprog.pairhero.views;

import java.util.Calendar;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ltk.core.refactoring.RefactoringCore;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptorProxy;
import org.eclipse.ltk.core.refactoring.history.IRefactoringHistoryService;
import org.eclipse.ltk.core.refactoring.history.RefactoringHistory;
import org.eclipse.ui.PlatformUI;

public class RefactoringSubscriber {

	private static final NullProgressMonitor MONITOR = new NullProgressMonitor();
	private static final int SINCE_INTERVAL = -1;
	private static final int SECOND_INTERVAL = 1000;
	private Game game;
	private final IRefactoringHistoryService refactoringHistoryService;
	private boolean isListenning;

	public RefactoringSubscriber() {
		this(RefactoringCore.getHistoryService());
	}

	public RefactoringSubscriber(IRefactoringHistoryService refactoringHistoryService) {
		this.refactoringHistoryService = refactoringHistoryService;
	}

	public void subscribe(Game game) {
		this.game = game;
		startListener();
	}

	void extractRefactoringHistory() {
		refactoringHistoryService.connect();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, SINCE_INTERVAL);
		long timeInterval = calendar.getTimeInMillis();
		long now = Calendar.getInstance().getTimeInMillis();

		RefactoringHistory history = refactoringHistoryService.getWorkspaceHistory(timeInterval, now, MONITOR);

		RefactoringDescriptorProxy[] descriptors = history.getDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			game.onRefactoring();
		}

		refactoringHistoryService.disconnect();
	}

	void startListener() {
		isListenning = true;
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getDisplay().asyncExec(new RefactoringRunnable());
	}

	class RefactoringRunnable implements Runnable {
		@Override
		public void run() {
			extractRefactoringHistory();
			if (isListenning) {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getDisplay().timerExec(SECOND_INTERVAL, this);
			}
		}
	}

	public void unregister() {
		isListenning = false;
	}
}
