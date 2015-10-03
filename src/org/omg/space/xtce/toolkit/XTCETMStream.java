/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.omg.space.xtce.toolkit;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.omg.space.xtce.database.AliasSetType;
import org.omg.space.xtce.database.DescriptionType.AncillaryDataSet;
import org.omg.space.xtce.database.FixedFrameStreamType;
import org.omg.space.xtce.database.PCMStreamType;
import org.omg.space.xtce.database.VariableFrameStreamType;

/** Class the represent a telemetry stream from the XTCE data model.
 *
 * @author dovereem
 *
 */

public class XTCETMStream extends XTCENamedObject {

    /** Constructor
     *
     * The constructor for this class is designed to be used from within the
     * toolkit package.
     *
     * @param stream PCMStreamType from the JAXB generated XTCE schema.
     *
     * @param spaceSystem XTCESpaceSystem object that represents the Space
     * System in XTCE that contains the definition of this stream.
     *
     * @param db XTCEDatabase object used to locate the base container of the
     * stream.
     *
     * @param aliasSet AliasSetType object, which can be null, that contains
     * the AliasSet element from the XTCE data model that is associated with
     * this stream.
     *
     * @throws XTCEDatabaseException in the event that the element defining
     * the stream is not supported or the stream cannot be fully decomposed.
     *
     */

    XTCETMStream( PCMStreamType    stream,
                  XTCESpaceSystem  spaceSystem,
                  XTCEDatabase     db,
                  AliasSetType     aliasSet,
                  AncillaryDataSet ancDataSet ) throws XTCEDatabaseException {

        super( stream.getName(),
               spaceSystem.getFullPath(),
               aliasSet,
               ancDataSet );

        stream_ = stream;
        db_     = db;

        String ref = null;

        if ( stream_ instanceof FixedFrameStreamType ) {
            FixedFrameStreamType fstream = (FixedFrameStreamType)stream_;
            ref = fstream.getContainerRef().getContainerRef();
        } else if ( stream_ instanceof VariableFrameStreamType ) {
            VariableFrameStreamType vstream = (VariableFrameStreamType)stream_;
            ref = vstream.getContainerRef().getContainerRef();
        } else {
            throw new XTCEDatabaseException( XTCEFunctions.getText( "error_stream_unsupported" ) +
                                             ": '" + getName() +
                                             "'" );
        }

        String sPath =
            XTCEFunctions.resolvePathReference( spaceSystem.getFullPath(),
                                                ref );

        rootContainer_ = db_.getContainer( sPath );

        streamContainers_ = new TreeMap<>();

        String iPath = getStreamRootContainer().getInheritancePath();

        for ( XTCESpaceSystem ss : db.getSpaceSystemTree() ) {
            List<XTCETMContainer> containers =
                ss.getInheritingContainers( iPath );
            for ( XTCETMContainer container : containers ) {
                streamContainers_.put( container, null );
                //System.out.println( "Adding Container " + container.getName() );
            }
        }

    }

    /** Retrieve the path to the XTCE SequenceContainer element that is the
     * base container for this stream definition.
     *
     * @return String containing the full path in the XTCE data model for the
     * container that is the base or root of this stream.
     *
     */

    public String getStreamRootContainerPath() {
        return rootContainer_.getFullPath();
    }

    /** Retrieve the XTCETMContainer that is the base container for this
     * stream.
     *
     * @return XTCETMContainer representing the container in the XTCE document.
     *
     */

    public XTCETMContainer getStreamRootContainer() {
        return rootContainer_;
    }

    /** Retrieve a reference to the native JAXB object that is generated from
     * the XTCE schema.
     *
     * @return PCMStreamType that underlies this stream object.
     *
     */

    public PCMStreamType getReference() {
        return stream_;
    }

    /** Function to retrieve all of the Telemetry Containers that are members
     * of this stream in the XTCE document.
     *
     * @return List of XTCETMContainers in the stream.
     *
     */

    public List<XTCETMContainer> getContainers() {

        Set<XTCETMContainer>  containers = streamContainers_.keySet();
        List<XTCETMContainer> sorted     = new ArrayList<>();

        //System.out.println( "Count: " + Long.toString( containers.size() ) );
        sorted.addAll( containers );
        Collections.sort( sorted );

        return sorted;

    }

    /** Function to decompose a binary data stream into a simple array of
     * entries that an application can iterate over without the need to
     * resolve XTCE data model references, included additional containers,
     * base containers, and conditional processing.
     *
     * @param binaryData BitSet containing the container binary encoded data
     * so that the output object contains entries with actual values from a
     * real binary image.
     *
     * @return XTCEContainerContentModel representing this XTCETMContainer.
     *
     * @throws XTCEDatabaseException thrown in the event that it is not
     * possible to decompose the container completely due to bad references in
     * the XTCE document.
     *
     */

    public XTCEContainerContentModel processStream( BitSet binaryData )
        throws XTCEDatabaseException {

        for ( XTCETMContainer container : streamContainers_.keySet() ) {

            if ( container.isAbstract() == true ) {
                continue;
            }

            XTCEContainerContentModel model;

            if ( ( model = streamContainers_.get( container ) ) == null ) {

                model = new XTCEContainerContentModel( container,
                                                       db_.getSpaceSystemTree(),
                                                       null,
                                                       false );

                streamContainers_.put( container, model );

            }

            if ( model.isProcessingCompatible( binaryData ) == true ) {
                return new XTCEContainerContentModel( container,
                                                      db_.getSpaceSystemTree(),
                                                      binaryData );
            }

        }

        throw new XTCEDatabaseException( "Unable to process binary to desired stream" );

    }

    /** Function to decompose a binary data stream into a simple array of
     * entries that an application can iterate over without the need to
     * resolve XTCE data model references, included additional containers,
     * base containers, and conditional processing.
     *
     * This function is intended to accept the byte array that is read from
     * a ByteArrayOutputStream.toByteArray() that is easily obtained when
     * reading a binary file using a Java FileInputStream.
     *
     * @param bytes byte[] containing the container binary encoded data
     * so that the output object contains entries with actual values from a
     * real binary image.
     *
     * @return XTCEContainerContentModel representing this XTCETMContainer.
     *
     * @throws XTCEDatabaseException thrown in the event that it is not
     * possible to decompose the container completely due to bad references in
     * the XTCE document.
     *
     */

    public XTCEContainerContentModel processStream( byte[] bytes )
        throws XTCEDatabaseException {

        BitSet bits = XTCEFunctions.getBitSetFromStreamByteArray( bytes );

        return processStream( bits );

    }

    /** Function to decompose a binary data stream into a simple array of
     * entries that an application can iterate over without the need to
     * resolve XTCE data model references, included additional containers,
     * base containers, and conditional processing.
     *
     * This function is intended to accept a Java InputStream containing the
     * bytes to use for the binary portion of the container.
     *
     * @param stream InputStream containing the container binary encoded data
     * so that the output object contains entries with actual values from a
     * real binary image.
     *
     * @return XTCEContainerContentModel representing this XTCETMContainer.
     *
     * @throws XTCEDatabaseException thrown in the event that it is not
     * possible to decompose the container completely due to bad references in
     * the XTCE document, or if the stream throws an IOException.
     *
     */

    public XTCEContainerContentModel processStream( InputStream stream )
        throws XTCEDatabaseException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int byteValue;

        try {
            while ( ( byteValue = stream.read() ) != -1 ) {
                buffer.write( byteValue );
            }
        } catch ( Exception ex ) {
            throw new XTCEDatabaseException( ex.getLocalizedMessage() );
        }

        return processStream( buffer.toByteArray() );

    }

    // Private Data Members

    private final PCMStreamType   stream_;
    private final XTCETMContainer rootContainer_;
    private final XTCEDatabase    db_;

    private Map<XTCETMContainer, XTCEContainerContentModel> streamContainers_;

}
