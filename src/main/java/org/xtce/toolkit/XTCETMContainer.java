/* Copyright 2015 David Overeem (dovereem@startmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.xtce.toolkit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.omg.space.xtce.ParameterRefEntryType;
import org.omg.space.xtce.SequenceContainerType;
import org.omg.space.xtce.SequenceEntryType;

/** The XTCETMContainer class models the XTCE SequenceContainer element by
 * simplifying the usage by pre-modeling the relationships. 
 *
 * @author David Overeem
 *
 */

public class XTCETMContainer extends XTCENamedObject {

    /** Constructor
     * 
     * @param path String containing the Space System path to this container
     * element, without the name of the container.
     *
     * @param iPath String containing the inheritance path for this container
     * element, including the name of this container.
     *
     * @param container SequenceContainerType element from the XTCE data model
     * as generated in the JAXB classes.
     *
     */

    XTCETMContainer( String path, String iPath, SequenceContainerType container ) {

        super( container.getName(),
               path,
               container.getAliasSet(),
               container.getAncillaryDataSet() );

        iPath_     = iPath;
        container_ = container;

    }

    /** Retrieve a reference to the SequenceContainer element in the JAXB
     * generated classes for the XTCE data model.
     *
     * @return SequenceContainerType element represented by this object.
     *
     */

    public final SequenceContainerType getSequenceContainerReference() {
        return container_;
    }

    /** Retrieve the abstract flag for this SequenceContainer.
     *
     * @return boolean indicating if this container should be represented as
     * an abstract container.
     *
     */

    public final boolean isAbstract() {

        // In XTCE 1.1, this method call can return null because there is no
        // default value.  The null is treated as "false".  For later versions
        // of XTCE this will be corrected and null will not be a possibility.
        // For XTCE newer versions, this will make a warning for the expression
        // in that it can never be null;

        Boolean result = container_.isAbstract();

        return ( result == null ? false : result );

    }

    /** Retrieve the inheritance path for this SequenceContainer.
     *
     * The "inheritance path" is different from the SpaceSystem locating path
     * in that it represents the parent SequenceContainer elements leading to
     * this element through the BaseContainer element in the XTCE data model.
     *
     * @return String containing the inheritance path to this container.
     *
     */

    public final String getInheritancePath() {
        return iPath_;
    }

    /** Retrieve the shortDescription attribute for this SequenceContainer.
     *
     * @return String containing the short description, or an empty string if
     * no short description is present.
     *
     */

    public final String getShortDescription() {
        return getPrimaryShortDescription( container_ );
    }

    /** Sets the shortDescription attribute for this SequenceContainer.
     *
     * In the event that the description is null or an empty string, then the
     * attribute will be removed from the element for compression.
     *
     * @param description String containing the new short description or an
     * empty string (null tolerated) to remove the attribute.
     *
     */

    public final void setShortDescription( String description ) {
        setPrimaryShortDescription( container_, description );
    }

    /** Retrieve the LongDescription element text for this SequenceContainer.
     *
     * @return String containing the long description, or an empty string if
     * no long description is present.
     *
     */

    public final String getLongDescription() {
        return getPrimaryLongDescription( container_ );
    }

    /** Sets the Long Description element text for this SequenceContainer.
     *
     * In the event that the description is null or an empty string, then the
     * element will be removed for compression.
     *
     * @param description String containing the new long description or an
     * empty string (null tolerated) to remove the element.
     *
     */

    public final void setLongDescription( String description ) {
        setPrimaryLongDescription( container_, description );
    }

    /** Retrieves the preferred effective description of this Container in the
     * XTCE data model.
     *
     * This method prefers the shortDescription attribute if it exists.  If it
     * does not, then the LongDescription element will be used if present.
     *
     * @return String containing a single description text item for this
     * Container, generally suitable for display tables.  The String will never
     * be null.
     *
     */

    public final String getDescription() {

        String parameterDescription = getShortDescription();
        if ( parameterDescription.isEmpty() == true ) {
            parameterDescription = getLongDescription();
        }

        return parameterDescription;

    }

    /** Function to test if this container includes an XTCEParameter.
     *
     * @param parameter XTCEParameter to search in the container entry list.
     *
     * @return Boolean indicating if this container entry list references the
     * requested parameter.
     *
     */

    public final boolean contains( XTCEParameter parameter ) {

        try {

            List<SequenceEntryType> entries =
                container_.getEntryList()
                          .getParameterRefEntryOrParameterSegmentRefEntryOrContainerRefEntry();

            String       name        = parameter.getFullPath();
            List<String> memberPaths = null;

            // handle the case where a parameter is a memeber element

            if ( parameter.isMember() == true ) {

                name        = name.replaceAll( "\\.", "/" ); // NOI18N
                memberPaths = new ArrayList<>();

                String path =
                    XTCEFunctions.getPathNameFromReferenceString( name );

                while ( path.isEmpty() == false ) {
                    memberPaths.add( path );
                    path = XTCEFunctions.getPathNameFromReferenceString( path );
                }

            }

            // search for a reference to the parameter

            for ( SequenceEntryType entry : entries ) {

                if ( entry instanceof ParameterRefEntryType ) {

                    String parameterRef =
                        ((ParameterRefEntryType)entry).getParameterRef();
                    String entryFullPath =
                        XTCEFunctions.resolvePathReference( getSpaceSystemPath(),
                                                            parameterRef );

                    if ( name.equals( entryFullPath ) == true ) {
                        return true;
                    } else if ( memberPaths != null ) {
                        for ( String path : memberPaths ) {
                            if ( path.equals( entryFullPath ) == true ) {
                                return true;
                            }
                        }
                    }

                }

            }

        } catch ( NullPointerException ex ) {
            // it is okay if the container has an empty entry list
        }
        
        return false;

    }

    /** Retrieve an XML string that represents this Container element.
     *
     * @return String containing the XML fragment.
     *
     * @throws XTCEDatabaseException in the event that the elements being
     * marshaled from the JAXB internal classes cannot make a valid document.
     * Check the exception message for causality information.
     *
     */

    public final String toXml() throws XTCEDatabaseException {

        try {

            JAXBElement<SequenceContainerType> xmlElement = new JAXBElement<>
                ( new QName( "SequenceContainer" ), // NOI18N
                  SequenceContainerType.class,
                  container_ );

            XTCEDocumentMarshaller mmm =
                new XTCEDocumentMarshaller( SequenceContainerType.class, true );

            return XTCEFunctions.xmlPrettyPrint( mmm.marshalToXml( xmlElement ) );

        } catch ( Exception ex ) {
            throw new XTCEDatabaseException(
                getName() +
                ": " + // NOI18N
                XTCEFunctions.getText( "xml_marshal_error_container" ) + // NOI18N
                " '" + // NOI18N
                ex.getCause() +
                "'" ); // NOI18N
        }

    }

    /** Comparison method used to order the XTCETMContainer objects when sorted
     * based upon their inheritance path rather than the Space System path.
     *
     * Sorting by inheritance path is used by the graphical interface for the
     * generation of tree nodes.
     *
     * @param that XTCETMContainer object to compare to.
     *
     * @return Integer value of -1, 0, or 1.
     *
     */

    @Override
    public int compareTo( Object that ) {

        if ( that instanceof String ) {
            return this.iPath_.compareTo( (String)that );
        }

        return this.iPath_
                   .compareTo( ( (XTCETMContainer)that ).iPath_ );

    }

    // Private Data Members

    private final String                iPath_;
    private final SequenceContainerType container_;

}
