/*
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
 */

package org.eclipse.datatools.connectivity.oda.consumer.testdriver.spec.impl;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.IValidator;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.ValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ExtensionContributor;
import org.eclipse.datatools.connectivity.oda.spec.manifest.SupportedDataSetType;
import org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.ColumnIdentifier;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CustomExpression;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;

/**
 *  Sample custom validator of extension-defined filter expressions.
 */
@SuppressWarnings("restriction")
public class ExpressionTester implements IValidator, IExecutableExtension
{
    private ExtensionContributor m_contributor;

    public ExpressionTester()
    {}

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
     */
    public void setInitializationData( IConfigurationElement contributorElement,
            String propertyName, Object data ) throws CoreException
    {
        try
        {
            m_contributor = new ExtensionContributor( contributorElement );
        }
        catch( OdaException ex )
        {
            // TODO ignore, log warning
        }
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.filter.IValidator#validate(org.eclipse.datatools.connectivity.oda.filter.FilterExpression, org.eclipse.datatools.connectivity.oda.filter.ValidationContext)
     */
    public void validate( FilterExpression expr, ValidationContext context )
            throws OdaException
    {
        // TODO
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validateSyntax( FilterExpression expr, ValidationContext context ) throws OdaException
    {
        if( !(expr instanceof CustomExpression) )
        {
            return;     // done
        }
        
        CustomExpression customExpr = (CustomExpression) expr;
        
        // validate if the data set types specified by the contributor of this validator matches 
        // the type declared as supported in the custom expression definition
        SupportedDataSetType[] dataSetTypes = m_contributor.getSupportedDataSetTypes();
        if( dataSetTypes.length > 0 )
        {
            boolean supportsExprDataSetType = false;
            for( int i=0; i < dataSetTypes.length; i++ )
            {
                SupportedDataSetType validatorDataSetType = dataSetTypes[i];
                if( customExpr.supportsDataSetType( validatorDataSetType ))
                {
                    supportsExprDataSetType = true;
                    break;
                }
            }
            if( ! supportsExprDataSetType )
                throw new OdaException( "The filter expression (" + customExpr.getQualifiedId() 
                        + ") is not valid for the ODA data set type." );
        }
        
        // TODO 
        // validate a custom expression is supported 
        // vaildate ExpressionVariable is of type RESULT_COLUMN
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.filter.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression, org.eclipse.datatools.connectivity.oda.filter.ValidationContext)
     */
    public void validate( AggregateExpression expr, ValidationContext context )
            throws OdaException
    {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validateSyntax( AggregateExpression aggrExpr,
            ValidationContext context ) throws OdaException
    {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.ValueExpression, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validate( ValueExpression valueExpr, ValidationContext context )
            throws OdaException
    {
        // TODO Auto-generated method stub        
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValueExpression, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validateSyntax( ValueExpression valueExpr,
            ValidationContext context ) throws OdaException
    {
        // TODO Auto-generated method stub      
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validate( SortSpecification sortSpec, ValidationContext context )
            throws OdaException
    {
        // TODO Auto-generated method stub  
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validate( QuerySpecification querySpec, ValidationContext context )
            throws OdaException
    {
        SortSpecification sortSpec = QuerySpecificationHelper.getSortSpecification( querySpec );
        if( sortSpec != null )
        {
            for( int i=1; i <= sortSpec.getSortKeyCount(); i++ )
            {
                ColumnIdentifier column = sortSpec.getSortColumn( i );
                
                // test driver expects sort column identifier are defined as querySpec property names
                String columnName = column.isIdentifiedByNumber() ? column.getNumber().toString() : column.getNameExpression();
                if( ! querySpec.getProperties().containsKey( columnName ) )
                    throw new OdaException( "Unexpected sort column: " + column ); //$NON-NLS-1$
                
                // validate that no null ordering is specified
                int nullOrdering = sortSpec.getNullOrdering( i );
                if( nullOrdering != SortSpecification.NULL_ORDERING_NONE )
                    throw new OdaException( "Cannot support null ordering: " + nullOrdering ); //$NON-NLS-1$
            }
        }
        
        if( context != null )
        {
            String propValue = (String) context.getData( ValidationContext.DATA_PROPERTY_QUERY_TEXT );
            String expectedPropValue = (String) querySpec.getProperties().get( ValidationContext.DATA_PROPERTY_QUERY_TEXT );
            if(  propValue != expectedPropValue )
                throw new OdaException( "Unexpected query text: " + propValue ); //$NON-NLS-1$
        }
    }

}
