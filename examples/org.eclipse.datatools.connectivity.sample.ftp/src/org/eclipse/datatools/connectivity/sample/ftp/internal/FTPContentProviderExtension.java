/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf & mdow - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.ftp.internal;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ui.CommonContentProviderBase;
import org.eclipse.datatools.connectivity.ui.IContentExtension;

/**
 * This class implements the navigatorContent extension and supplies workspace
 * resources.
 * 
 */
public class FTPContentProviderExtension extends
		CommonContentProviderBase {

	public FTPContentProviderExtension() {
		super(new FtpContentProvider());
	}

	protected IContentExtension createContentExtension(
			IConnectionProfile profile) {
		return new FTPContentExtension(profile);
	}
}