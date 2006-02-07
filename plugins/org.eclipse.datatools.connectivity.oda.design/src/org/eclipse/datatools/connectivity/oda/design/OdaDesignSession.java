/**
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: OdaDesignSession.java,v 1.2 2006/02/03 04:16:15 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition of an ODA design session on a Data Access Design.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getRequest <em>Request</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getResponse <em>Response</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOdaDesignSession()
 * @model 
 * @generated
 */
public interface OdaDesignSession extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Request</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Request</em>' containment reference.
     * @see #setRequest(DesignSessionRequest)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOdaDesignSession_Request()
     * @model containment="true" resolveProxies="false" required="true"
     * @generated
     */
    DesignSessionRequest getRequest();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getRequest <em>Request</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Request</em>' containment reference.
     * @see #getRequest()
     * @generated
     */
    void setRequest( DesignSessionRequest value );

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getRequest <em>Request</em>}' containment reference
     * with a new request associated with the given data source design. 
     * @param dataSourceDesign
     * @see #setRequest(DesignSessionRequest)
     * @generated NOT
     */
    void setNewRequest( DataSourceDesign dataSourceDesign );
    
    /**
     * Returns the value of the '<em><b>Response</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Response</em>' containment reference.
     * @see #setResponse(DesignSessionResponse)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getOdaDesignSession_Response()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    DesignSessionResponse getResponse();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getResponse <em>Response</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Response</em>' containment reference.
     * @see #getResponse()
     * @generated
     */
    void setResponse( DesignSessionResponse value );

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.OdaDesignSession#getResponse <em>Response</em>}' containment reference
     * with a new response associated with the given data source design. 
     * @param dataSourceDesign
     * @see #setResponse(DesignSessionResponse)
     * @generated NOT
     */
    void setNewResponse( boolean isSessionOk, DataSourceDesign dataSourceDesign );

    /**
     * Gets the Data Source Design associated with the
     * top level Data Set in the Request session.
     * @return the value of the '<em>Data Source Design</em>' containment reference.
     * @see #getRequest()
     * @generated NOT
     */
    DataSourceDesign getRequestDataSourceDesign();

    /**
     * Gets the Data Source Design associated with the
     * top level Data Set in the Response session.
     * @return the value of the '<em>Data Source Design</em>' containment reference.
     * @see #getResponse()
     * @generated NOT
     */
   DataSourceDesign getResponseDataSourceDesign();
    
} // OdaDesignSession
