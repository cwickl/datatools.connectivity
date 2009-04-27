/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <p>
 * <strong>EXPERIMENTAL</strong>.
 * </p>
 * A representation of the model object '<em><b>Sort Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Specification of one or more sort keys.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.SortSpecification#getSortKeys <em>Sort Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortSpecification()
 * @since 3.2 (DTP 1.7)
 * @model extendedMetaData="name='SortSpecification' kind='elementOnly'"
 * @generated
 */
public interface SortSpecification extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Sort Keys</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.connectivity.oda.design.SortKey}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sort Keys</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sort Keys</em>' containment reference list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortSpecification_SortKeys()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='sortKeys' namespace='##targetNamespace'"
     * @generated
     */
    EList<SortKey> getSortKeys();

} // SortSpecification