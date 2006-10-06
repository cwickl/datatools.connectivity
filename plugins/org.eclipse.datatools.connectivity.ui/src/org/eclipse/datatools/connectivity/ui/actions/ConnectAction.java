/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.datatools.connectivity.internal.ProfileExtensionProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.SameShellProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

/**
 * @author shongxum, brianf
 */
public class ConnectAction implements IObjectActionDelegate, ISelectionProvider {

	private IStructuredSelection mSelection = null;
	private Shell mShell;
	
	/**
	 * This method blocks until the connection has been created.
	 * 
	 * @param profile
	 * @param viewer
	 * 
	 * @deprecated use IConnectionProfile.connect(). The caller is now
	 *             responsible for updating their view themselves. Note, if you
	 *             are using a servers viewer, you should not need to update the
	 *             view as the view listens for connect events and updates
	 *             itself.
	 */
	public static void connectAndRefresh(IConnectionProfile profile,
			TreeViewer viewer) {
		profile.connect();

		viewer.refresh(profile);
		viewer.setExpandedState(profile, true);
	}

	/**
	 * @param profile
	 * @param contentProvider
	 * @return
	 * @deprecated use IConnectionProfile.connect(). The caller is now
	 *             responsible for updating their view themselves. Note, if you
	 *             are using a servers viewer, you should not need to update the
	 *             view as the view listens for connect events and updates
	 *             itself.
	 */
	public static Job connect(IConnectionProfile profile,
			ITreeContentProvider contentProvider) {
		Job connectJob = ((ConnectionProfile) profile).new ConnectJob();
		connectJob.schedule();
		return connectJob;
	}

	public ConnectAction() {
		super();
	}

	public ConnectAction(Shell shell) {
		this();
		mShell = shell;
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		mShell = targetPart.getSite().getShell();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		if (mSelection != null && allProfilesInSelectionAreDisconnected(mSelection)) {
			List list = mSelection.toList();
			if (list == null || list.size() == 0) 
				return;
			
			for (int i = 0; i < list.size(); i++) {
				PropertyDialogAction propertyDialogAction = new PropertyDialogAction(
						new SameShellProvider(mShell), this);
				if (list.get(i) instanceof IConnectionProfile) {
					IConnectionProfile profile = (IConnectionProfile) list.get(i);
					StructuredSelection ssel = new StructuredSelection(profile);
					propertyDialogAction.selectionChanged(ssel);
					if (!profile.arePropertiesComplete()
							&& propertyDialogAction.isApplicableForSelection()) {
						PreferenceDialog dialog = propertyDialogAction.createDialog();
						String initialPage = getInitialPropertyPageID(profile);
						if (initialPage != null) {
							((IWorkbenchPreferenceContainer) dialog).openPage(
									initialPage, null);
						}
						if (dialog.open() == Dialog.CANCEL) {
							return;
						}
					}
					profile.connect(null);
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		IStructuredSelection sel = (IStructuredSelection) selection;
		mSelection = sel;
		boolean flag = checkSelectionForProfiles(mSelection);
		boolean allAreDisconnected = false;
		if (flag) {
			allAreDisconnected = allProfilesInSelectionAreDisconnected(mSelection);
		}
		action.setEnabled(flag && allAreDisconnected);
	}
	
	private boolean checkSelectionForProfiles ( IStructuredSelection selection ) {
		List list = selection.toList();
		if (list == null || list.size() == 0) 
			return false;
		
		for (int i = 0; i < list.size(); i++) {
			if (!(list.get(i) instanceof IConnectionProfile)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean allProfilesInSelectionAreDisconnected ( IStructuredSelection selection ) {
		List list = selection.toList();
		if (list == null || list.size() == 0) 
			return false;
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof IConnectionProfile) {
				IConnectionProfile profile = (IConnectionProfile) list.get(i);
				if (profile.isConnected())
					return false;
			}
		}
		return true;
	}

	public ISelection getSelection() {
		return mSelection;
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
	}

	public void setSelection(ISelection selection) {
	}
	
	protected String getInitialPropertyPageID(IConnectionProfile profile) {
		if (!profile.arePropertiesComplete(profile.getProviderId())) {
			return ((ConnectionProfileProvider)profile.getProvider()).getPropertiesPersistenceHook().getConnectionPropertiesPageID();
		}
		for (Iterator it = profile.getProfileExtensions().entrySet().iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry)it.next();
			String extID = (String)entry.getKey();
			if (!profile.arePropertiesComplete(extID)) {
				return ((ProfileExtensionProvider)entry.getValue()).getPropertiesPersistenceHook().getConnectionPropertiesPageID();
			}
		}
		return null;
	}

}