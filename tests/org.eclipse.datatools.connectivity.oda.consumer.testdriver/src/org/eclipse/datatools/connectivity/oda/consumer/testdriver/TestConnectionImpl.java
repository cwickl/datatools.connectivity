/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.testdriver;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * A tester ODA driver to test the behavior of odaconsumer, calling
 * on an ODA driver's IConnection implementation. 
 * Behavior being tested include:
 * 	setAppContext
 */
public class TestConnectionImpl implements IConnection
{
    private Object m_appContext;
    private boolean m_isOpen = false;
    
    public TestConnectionImpl()
    {
    }

    public TestConnectionImpl( boolean isConnOpen )
    {
        m_isOpen = isConnOpen;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#close()
     */
    public void close() throws OdaException
    {
        m_isOpen = false;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#commit()
     */
    public void commit() throws OdaException
    {
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#getMaxQueries()
     */
    public int getMaxQueries() throws OdaException
    {
        return 0;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#getMetaData(java.lang.String)
     */
    public IDataSetMetaData getMetaData( String dataSetType )
            throws OdaException
    {
        return new TestDataSetMetaDataImpl( this );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#isOpen()
     */
    public boolean isOpen() throws OdaException
    {
        return m_isOpen;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#newQuery(java.lang.String)
     */
    public IQuery newQuery( String dataSetType ) throws OdaException
    {
       return new TestAdvQueryImpl();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#open(java.util.Properties)
     */
    public void open( Properties connProperties ) throws OdaException
    {
        m_isOpen = true;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#rollback()
     */
    public void rollback() throws OdaException
    {
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#setAppContext(java.lang.Object)
     */
    public void setAppContext( Object context ) throws OdaException
    {
        m_appContext = context;
    }
    
    public Object getAppContext()
    {
        return m_appContext;
    }
}
