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

package org.eclipse.datatools.connectivity.oda.design.ui.designsession;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.ElementNullability;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ValueFormatHints;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSetUIElement;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIExtensionManifest;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIManifestExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;

/**
 *  An utility class to help an ODA host designer or
 *  an ODA driver's customized designer to manipulate
 *  ODA Design API objects during an ODA design session.
 */
public class DesignSessionUtil 
{
    // class has static methods only; no need to instantiate
    private DesignSessionUtil()
    {
    }
    
    /**
     * Creates an ODA design property collection for the public properties
     * defined in an ODA runtime extension manifest.  Their corresponding values 
     * are collected from the given profile property collection.
     * @param odaDataSourceId   the ODA extension data source element ID
     * @param utilProps      java.util.properties, such as those collected from a connection profile
     * @return  ODA design public property collection for inclusion
     *          in an OdaDesignSession's Data Source Design
     * @throws OdaException
     */
    public static Properties createDataSourcePublicProperties( 
                String odaDataSourceId,
                java.util.Properties utilProps )
            throws OdaException
    {
        // first get the public property definition in the ODA driver's runtime plugin manifest
        org.eclipse.datatools.connectivity.oda.util.manifest.Property[] publicPropDefns = 
            getPublicPropertiesDefn( odaDataSourceId );

        // create a new design Properties collection
        Properties designProps = 
            DesignFactory.eINSTANCE.createProperties();
        
        // for each defined public property name, get its corresponding
        // value in the profile, and create design property with name and value
        // in collection
        for( int i = 0; i < publicPropDefns.length; i++ )
        {
            String propName = publicPropDefns[i].getName();
            String propValue = utilProps.getProperty( propName );
            designProps.setProperty( propName, propValue );
        }
        return designProps;
    }
    
    /**
     * Creates an ODA design property collection for those given properties
     * that are not defined in an ODA runtime extension manifest. 
     * These are properties that are not publicly defined. 
     * Their corresponding values 
     * are collected from the given profile property collection.
     * @param odaDataSourceId   the ODA extension data source element ID
     * @param utilProps a java.util.Properties collection,
     *                  such as those collected from a connection profile
     * @return  ODA design non-public property collection for inclusion
     *          in an OdaDesignSession's Data Source Design
     * @throws OdaException
     */
    public static Properties createDataSourceNonPublicProperties( 
                String odaDataSourceId,
                java.util.Properties utilProps )
        throws OdaException
    {
        // first get the public property definition in the ODA driver's runtime plugin manifest
        org.eclipse.datatools.connectivity.oda.util.manifest.Property[] publicPropDefns = 
            getPublicPropertiesDefn( odaDataSourceId );
    
        // create a new design Properties collection
        Properties designProps = 
            DesignFactory.eINSTANCE.createProperties();
        
        // for each defined public property name, get its corresponding
        // value in the profile, and create design property with name and value
        // in collection
        Iterator iter = utilProps.keySet().iterator();
        while( iter.hasNext() )
        {
            String utilPropName = (String) iter.next();

            boolean isPublicProp = false;
            for( int i = 0; i < publicPropDefns.length; i++ )
            {
                if( utilPropName.equalsIgnoreCase( 
                        publicPropDefns[i].getName() ))
                {
                    isPublicProp = true;
                    break;
                }
            }
            if( isPublicProp )
                continue;   // skip public property

            String propValue = utilProps.getProperty( utilPropName );
            designProps.setProperty( utilPropName, propValue );
        }
        return designProps;
    }

    /**
     * Creates a new OdaDesignSession instance with a session
     * request that contains a new data set design of
     * the specified name and odaDataSetId,
     * associated with the specified data source design instance.
     * @param newDataSetName   a unique name that identifies a data set 
     *          design instance
     * @param odaDataSetId      an ODA data set element id;
     *              may be null if the associated data source extension 
     *              supports only one type of data set 
     * @param dataSourceDesign  the associated data source design instance
     * @return  a new OdaDesignSession instance with a session
     *          request of the specified design attributes
     * @throws OdaException if specified arguments are invalid
     */
    public static OdaDesignSession createNewRequestSession( 
                            String newDataSetName,
                            String odaDataSetId, 
                            DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        // validate input arguments
        if( newDataSetName == null || newDataSetName.length() == 0 ||
                dataSourceDesign == null )
            throw new OdaException( Messages.designSession_invalidArgument );
        
        OdaDesignSession newSession =
            DesignFactory.eINSTANCE
                .createRequestDesignSession( dataSourceDesign );

        DataSetDesign newDataSetDesign = 
            newSession.getRequestDataSetDesign();
        newDataSetDesign.setName( newDataSetName );
        newDataSetDesign.setOdaExtensionDataSetId( odaDataSetId );

        validateRequestSession( newSession.getRequest() );

        return newSession;
    }
    
    /**
     * Returns the connection profile instance externally referenced, 
     * i.e. linked, by the given data source design.
     * @param dataSourceDesign
     * @return  linked connection profile instance, or null
     *          if no external profile is referenced
     * @throws OdaException if referenced connection profile is not found
     */
    public static IConnectionProfile getLinkedProfile( DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        if( ! dataSourceDesign.hasLinkToProfile() )
            return null;

        String linkedProfileName = dataSourceDesign.getLinkedProfileName();
        IConnectionProfile profile = OdaProfileExplorer.getInstance()
            .getProfileByName( linkedProfileName,
                    dataSourceDesign.getLinkedProfileStoreFile() );
        if( profile == null )   // not found
            throw new OdaException( 
                    Messages.bind( Messages.designSession_invalidProfileName,
                                linkedProfileName ));
        return profile;
    }
        
    /**
     * Finds and returns the property definition specified in
     * an ODA extension that implements the 
     * oda.datasource run-time extension point.
     */
    private static org.eclipse.datatools.connectivity.oda.util.manifest.Property[] 
        getPublicPropertiesDefn( String odaDataSourceId )
        throws OdaException
    {
        try
        {
            ExtensionManifest runtimeManifest = 
                ManifestExplorer.getInstance().getExtensionManifest( 
                        odaDataSourceId );
            return runtimeManifest.getProperties();
        }
        catch( IllegalArgumentException ex )
        {
            throw new OdaException( ex );
        }        
    }
    
    /**
     * Indicates whether the given ODA data source type has
     * implemented a valid 
     * <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code>
     * extension point.
     * @param odaDataSourceId   an ODA data source extension type's element id 
     * @return
     */
    public static boolean hasValidOdaDesignUIExtension( 
                                    String odaDataSourceId )
    {
        Object manifest;
        try
        {
            manifest =
                UIManifestExplorer.getInstance()
                    .getExtensionManifest( odaDataSourceId );
        }
        catch( OdaException ex )
        {
            // has invalid manifest
            manifest = null;
        }

        return ( manifest != null );
    }
    
    /**
     * Returns a collection of identifiers of 
     * all connection profile instances for
     * the given ODA data source extension type.
     * The profile instances are searched in the given profile storage file.
     * It also caches the matching profiles for subsequent use.
     * @param odaDataSourceId   an ODA data source extension type's element id 
     * @param storageFile   a file that stores profile instances;
     *                      may be null, which means to use the
     *                      default DTP profiles storage file
     * @return  a <code>Map</code> containing the instance Id
     *          and display name of all existing profiles of the given odaDataSourceId.
     *          The connection profiles' instance Id and display name
     *          are stored as the key and value strings in the returned <code>Map</code> instance.
     *          Returns an empty collection if there are 
     *          no matching connection profiles found in given storageFile.
     * @throws OdaException if error in reading from given storageFile,
     *                      or in processing the found profiles
     */
    public static Map getProfileIdentifiers( String odaDataSourceId, 
                                            File storageFile ) 
        throws OdaException
    {
        if( storageFile == null )
            return OdaProfileExplorer.getInstance().getProfiles( 
                    odaDataSourceId );
        return OdaProfileExplorer.getInstance().getProfiles( 
                odaDataSourceId, storageFile );
    }
    
    /**
     * Returns the data set ui element manifest that
     * defines customized data set designer
     * that an ODA data provider extends to allow an user
     * to create or edit an ODA data set design instance.
     * This encapsulates the child elements for the data set wizard page(s) 
     * and editor page(s).  
     * @param odaDataSourceId
     * @param odaDataSetId
     * @return
     * @throws OdaException
     */
    static DataSetUIElement getDataSetUIElement( String odaDataSourceId,
                                        String odaDataSetId )
        throws OdaException
    {
        UIExtensionManifest manifest =
            UIManifestExplorer.getInstance().getExtensionManifest( odaDataSourceId );
        DataSetUIElement dataSetElement = null;
        if( manifest != null )
            dataSetElement = manifest.getDataSetUIElement( odaDataSetId );
        if( dataSetElement == null )
            throw new OdaException( 
                    Messages.bind( Messages.designSession_missingDataSetUIElement,
                            odaDataSourceId, odaDataSetId ));
        return dataSetElement;
    }

    /**
     * Validates the specified design session request.
     * If valid, returns the request's ODA data source element id.
     * @param requestSession
     * @return
     * @throws OdaException if specified session request is invalid
     */
    static String validateRequestSession( 
                    DesignSessionRequest requestSession )
        throws OdaException
    {
        String odaDataSourceId = null;
        try
        {
            odaDataSourceId =
                DesignUtil.validateRequestSession( requestSession );
        }
        catch( IllegalStateException ex )
        {
            throw new OdaException( ex );
        }

        // done validation
        return odaDataSourceId;
    }

    /**
     * Validates the specified data source design instance.
     * @param dataSourceDesign
     * @throws OdaException if specified design is invalid
     */
    static void validateDataSourceDesign( 
                        DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        try
        {
            DesignUtil.validateDataSourceDesign( dataSourceDesign );
        }
        catch( IllegalStateException ex )
        {
            throw new OdaException( ex );
        }
    }

    /**
     * Converts the contents of an ODA runtime result set meta data
     * to an ODA design-time ResultSetColumns instance.
	 * This may be used by an ODA custom page to
	 * populate a data set design instance with its
	 * result set meta data.
     * @param md    ODA runtime result set meta data instance
     * @return  the converted ResultSetColumns instance
     * @throws OdaException
     */
    public static ResultSetColumns toResultSetColumns( 
                            IResultSetMetaData md )
        throws OdaException
    {
        if( md == null || md.getColumnCount() == 0 )
            return null; // nothing to convert
        
        ResultSetColumns rsColumns =
            DesignFactory.eINSTANCE.createResultSetColumns();
        int columnCount = md.getColumnCount();
        for( int i = 1; i <= columnCount; i++ )
        {
            ColumnDefinition columnDef =
                DesignFactory.eINSTANCE.createColumnDefinition();

            DataElementAttributes columnAttrs =
                DesignFactory.eINSTANCE.createDataElementAttributes();
            columnAttrs.setPosition( i );
            columnAttrs.setNativeDataTypeCode( md.getColumnType(i) );

            ValueFormatHints formatHints = 
                    DesignFactory.eINSTANCE.createValueFormatHints();
            OutputElementAttributes outAttrs = 
                    DesignFactory.eINSTANCE.createOutputElementAttributes();
            try
            {
                columnAttrs.setName( md.getColumnName(i) );
                columnAttrs.setPrecision( md.getPrecision(i) );
                columnAttrs.setScale( md.getScale(i) );
                columnAttrs.setNullability( 
                        toElementNullability( md.isNullable(i) ));
                columnDef.setAttributes( columnAttrs );
                
                outAttrs.setLabel( md.getColumnLabel(i) );

                formatHints.setDisplaySize( md.getColumnDisplayLength(i) );                
            }
            catch( UnsupportedOperationException e )
            {
                // ignore; optional attributes
                e.printStackTrace();
            }

            outAttrs.setFormattingHints( formatHints );
            columnDef.setUsageHints( outAttrs );
            
            rsColumns.getResultColumnDefinitions().add( columnDef );
        }
        
        return rsColumns;
    }

    /**
     * Converts the value of the ODA runtime nullability
     * to that of the ODA design-time definition.
     * @param odaNullability an ODA runtime nullability value
     * @return
     */
    public static ElementNullability toElementNullability( int odaNullability )
    {
        ElementNullability toValue;
        switch( odaNullability )
        {
            case IResultSetMetaData.columnNoNulls:
                toValue = ElementNullability.NOT_NULLABLE_LITERAL; break;
            case IResultSetMetaData.columnNullableUnknown:
                toValue = ElementNullability.UNKNOWN_LITERAL; break;
            case IResultSetMetaData.columnNullable:
            default:
                toValue = ElementNullability.NULLABLE_LITERAL; break;
        }
        return toValue;
    }
}
