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
 * $Id: DesignFactory.java,v 1.1 2005/12/29 04:17:55 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage
 * @generated
 */
public interface DesignFactory extends EFactory{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    DesignFactory eINSTANCE = new org.eclipse.datatools.connectivity.oda.design.impl.DesignFactoryImpl();

    /**
     * Returns a new object of class '<em>Axis Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Axis Attributes</em>'.
     * @generated
     */
    AxisAttributes createAxisAttributes();

    /**
     * Returns a new object of class '<em>Column Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Column Definition</em>'.
     * @generated
     */
    ColumnDefinition createColumnDefinition();

    /**
     * Returns a new object of class '<em>Data Access Design</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Access Design</em>'.
     * @generated
     */
    DataAccessDesign createDataAccessDesign();

    /**
     * Returns a new object of class '<em>Data Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Element Attributes</em>'.
     * @generated
     */
    DataElementAttributes createDataElementAttributes();

    /**
     * Returns a new object of class '<em>Data Element UI Hints</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Element UI Hints</em>'.
     * @generated
     */
    DataElementUIHints createDataElementUIHints();

    /**
     * Returns a new object of class '<em>Data Set Design</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Set Design</em>'.
     * @generated
     */
    DataSetDesign createDataSetDesign();

    /**
     * Returns a new object of class '<em>Data Set Parameters</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Set Parameters</em>'.
     * @generated
     */
    DataSetParameters createDataSetParameters();

    /**
     * Returns a new object of class '<em>Data Set Query</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Set Query</em>'.
     * @generated
     */
    DataSetQuery createDataSetQuery();

    /**
     * Returns a new object of class '<em>Data Source Design</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Source Design</em>'.
     * @generated
     */
    DataSourceDesign createDataSourceDesign();

    /**
     * Returns a new object of class '<em>er State</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>er State</em>'.
     * @generated
     */
    DesignerState createDesignerState();

    /**
     * Returns a new object of class '<em>er State Content</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>er State Content</em>'.
     * @generated
     */
    DesignerStateContent createDesignerStateContent();

    /**
     * Returns a new object of class '<em>Session Request</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Session Request</em>'.
     * @generated
     */
    DesignSessionRequest createDesignSessionRequest();

    /**
     * Returns a new object of class '<em>Session Response</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Session Response</em>'.
     * @generated
     */
    DesignSessionResponse createDesignSessionResponse();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>Dynamic Values Query</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Dynamic Values Query</em>'.
     * @generated
     */
    DynamicValuesQuery createDynamicValuesQuery();

    /**
     * Returns a new object of class '<em>Input Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Element Attributes</em>'.
     * @generated
     */
    InputElementAttributes createInputElementAttributes();

    /**
     * Returns a new object of class '<em>Input Element UI Hints</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Element UI Hints</em>'.
     * @generated
     */
    InputElementUIHints createInputElementUIHints();

    /**
     * Returns a new object of class '<em>Input Parameter Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Parameter Attributes</em>'.
     * @generated
     */
    InputParameterAttributes createInputParameterAttributes();

    /**
     * Returns a new object of class '<em>Input Parameter UI Hints</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Parameter UI Hints</em>'.
     * @generated
     */
    InputParameterUIHints createInputParameterUIHints();

    /**
     * Returns a new object of class '<em>Locale</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Locale</em>'.
     * @generated
     */
    Locale createLocale();

    /**
     * Returns a new object of class '<em>Name Value Pair</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Name Value Pair</em>'.
     * @generated
     */
    NameValuePair createNameValuePair();

    /**
     * Returns a new object of class '<em>Oda Design Session</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Oda Design Session</em>'.
     * @generated
     */
    OdaDesignSession createOdaDesignSession();

    /**
     * Returns a new object of class '<em>Output Element Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Output Element Attributes</em>'.
     * @generated
     */
    OutputElementAttributes createOutputElementAttributes();

    /**
     * Returns a new object of class '<em>Parameter Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter Definition</em>'.
     * @generated
     */
    ParameterDefinition createParameterDefinition();

    /**
     * Returns a new object of class '<em>Parameter Field Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter Field Definition</em>'.
     * @generated
     */
    ParameterFieldDefinition createParameterFieldDefinition();

    /**
     * Returns a new object of class '<em>Parameter Fields</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter Fields</em>'.
     * @generated
     */
    ParameterFields createParameterFields();

    /**
     * Returns a new object of class '<em>Properties</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Properties</em>'.
     * @generated
     */
    Properties createProperties();

    /**
     * Returns a new object of class '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Property</em>'.
     * @generated
     */
    Property createProperty();

    /**
     * Returns a new object of class '<em>Property Attributes</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Property Attributes</em>'.
     * @generated
     */
    PropertyAttributes createPropertyAttributes();

    /**
     * Returns a new object of class '<em>Result Set Columns</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Result Set Columns</em>'.
     * @generated
     */
    ResultSetColumns createResultSetColumns();

    /**
     * Returns a new object of class '<em>Result Set Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Result Set Definition</em>'.
     * @generated
     */
    ResultSetDefinition createResultSetDefinition();

    /**
     * Returns a new object of class '<em>Result Sets</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Result Sets</em>'.
     * @generated
     */
    ResultSets createResultSets();

    /**
     * Returns a new object of class '<em>Scalar Value Choices</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Scalar Value Choices</em>'.
     * @generated
     */
    ScalarValueChoices createScalarValueChoices();

    /**
     * Returns a new object of class '<em>Scalar Value Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Scalar Value Definition</em>'.
     * @generated
     */
    ScalarValueDefinition createScalarValueDefinition();

    /**
     * Returns a new object of class '<em>Value Format Hints</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Format Hints</em>'.
     * @generated
     */
    ValueFormatHints createValueFormatHints();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    DesignPackage getDesignPackage();
    
    /**
     * Creates and returns an OdaDesignSession instance with a 
     * DesignSessionRequest that contains the given DataSourceDesign.
     * <br>This may be used by an ODA host designer to create 
     * a design session instance, for use to create/edit a 
     * data source design definition,
     * or to create a new data set design with a given data source design.
     * @param   dataSourceDesign    may be null for a session to
     *                              create a new data source design
     * @generated NOT
     */
    OdaDesignSession createRequestDesignSession( DataSourceDesign dataSourceDesign );
    
    /**
     * Creates and returns a new OdaDesignSession instance with a
     * DesignSessionResponse that contains the given session status
     * and DataSourceDesign.
     * <br>This method may be used by an ODA driver's design UI extension
     * to create a new design session instance with a response that contains
     * a new or edited data source design definition.
     * <br>The returned session instance also includes an appropriate
     * DesignSessionRequest to meet the contract for API objects content.
     * @param isSessionOk
     * @param dataSourceDesign
     * @return
     * @generated NOT
     */
    OdaDesignSession createResponseDesignSession( 
            boolean isSessionOk, DataSourceDesign dataSourceDesign );

} //DesignFactory
