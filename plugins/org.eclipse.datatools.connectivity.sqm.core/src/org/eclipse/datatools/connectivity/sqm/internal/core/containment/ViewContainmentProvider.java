/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class ViewContainmentProvider  extends AbstractContainmentProvider {
	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		ViewTable table = (ViewTable) obj;
		children.addAll(table.getIndex());
		children.addAll(table.getTriggers());
		return children;
	}

	public EObject getContainer(EObject obj) 
	{
		return ((ViewTable) obj).getSchema();
	}

	public String getGroupId(EObject obj) {
		return GroupID.VIEW;
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLSchemaPackage.eINSTANCE.getSchema_Tables();
	}
}
