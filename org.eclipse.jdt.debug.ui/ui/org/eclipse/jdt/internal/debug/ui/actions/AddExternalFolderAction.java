/*******************************************************************************
 * Copyright (c) 2000, 2018 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.debug.ui.actions;


import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.internal.debug.ui.launcher.IClasspathViewer;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;

/**
 * Adds an external folder to the runtime class path.
 */
public class AddExternalFolderAction extends OpenDialogAction {

	public AddExternalFolderAction(IClasspathViewer viewer, String dialogSettingsPrefix) {
		super(ActionMessages.AddExternalFolderAction_Add_External_Folder_1, viewer, dialogSettingsPrefix);
	}

	/**
	 * Prompts for a folder to add.
	 *
	 * @see IAction#run()
	 */
	@Override
	public void run() {

		String lastUsedPath= getDialogSetting(LAST_PATH_SETTING);
		if (lastUsedPath == null) {
			lastUsedPath = Util.ZERO_LENGTH_STRING;
		}
		DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.MULTI | SWT.SHEET);
		dialog.setText(ActionMessages.AddExternalFolderAction_Folder_Selection_3);
		dialog.setFilterPath(lastUsedPath);
		String res= dialog.open();
		if (res == null) {
			return;
		}

		IPath filterPath= new Path(dialog.getFilterPath());
		IRuntimeClasspathEntry[] elems= new IRuntimeClasspathEntry[1];
		IPath path= new Path(res).makeAbsolute();
		elems[0]= JavaRuntime.newArchiveRuntimeClasspathEntry(path);

		setDialogSetting(LAST_PATH_SETTING, filterPath.toOSString());

		getViewer().addEntries(elems);
	}
}
