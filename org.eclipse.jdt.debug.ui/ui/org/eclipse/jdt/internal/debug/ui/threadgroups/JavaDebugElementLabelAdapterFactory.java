/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.debug.ui.threadgroups;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.internal.ui.viewers.IAsynchronousContentAdapter;
import org.eclipse.debug.internal.ui.viewers.IAsynchronousLabelAdapter;
import org.eclipse.debug.internal.ui.viewers.IModelProxyFactory;
import org.eclipse.jdt.debug.core.IJavaDebugTarget;
import org.eclipse.jdt.debug.core.IJavaThreadGroup;

/**
 * @since 3.2
 *
 */
public class JavaDebugElementLabelAdapterFactory implements IAdapterFactory{
	
	private static IAsynchronousLabelAdapter fgThreadGroupLabelAdapter = new AsyncThreadGroupLabelAdapter();
	private static IAsynchronousContentAdapter fgThreadGroupTreeAdapter = new AsyncThreadGroupTreeContentAdapter();
	private static IAsynchronousContentAdapter fgTargetTreeAdapter = new AsynchDebugTargetTreeContentAdapter();
	private static IModelProxyFactory fgJavaModelProxyFactory = new JavaModelProxyFactory();

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType.equals(IAsynchronousLabelAdapter.class)) {
			if (adaptableObject instanceof IJavaThreadGroup) {
				return fgThreadGroupLabelAdapter;
			}
		}
		if (adapterType.equals(IAsynchronousContentAdapter.class)) {
			if (adaptableObject instanceof IJavaThreadGroup) {
				return fgThreadGroupTreeAdapter;
			}
			if (adaptableObject instanceof IJavaDebugTarget) {
				return fgTargetTreeAdapter;
			}
		}
		if (adapterType.equals(IModelProxyFactory.class)) {
			if (adaptableObject instanceof IJavaDebugTarget) {
				return fgJavaModelProxyFactory;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		return new Class[]{IAsynchronousLabelAdapter.class, IAsynchronousContentAdapter.class, IModelProxyFactory.class};
	}

}