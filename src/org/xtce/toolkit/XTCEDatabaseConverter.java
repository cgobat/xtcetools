/* Copyright 2017 David Overeem (dovereem@cox.net)
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** Class to implement the capability to convert/upgrade an XTCE document from
 * the 1.1 version to the 1.2 version.
 *
 * This conversion is still under construction.  The XTCE 1.2 schema is only
 * a proposal at this stage and is expected to get some additional work before
 * ultimate release/voting in September of 2017.  As a result, this logic
 * should not be considered "ready".  It is only a first cut to get some of
 * the work out of the way.
 *
 * None of the intermediate conversion functions are yet documented because
 * they are all considered to still be in work and may change at any time.
 *
 * @author dovereem
 *
 */

public class XTCEDatabaseConverter extends XTCEDatabaseParser {

    public XTCEDatabaseConverter( final File file, final boolean useXInclude )
        throws XTCEDatabaseException {

        messages_ = new ArrayList<>();

        setFilename( file );

        loadDatabase( getFilename(),
                      false, // do not validate on load
                      useXInclude,
                      false ); // editable - loads the Document Object Model

    }

    /** Retrieve the messages that were generated, if any, during the file
     * conversion/upgrade activity.
     *
     * @return List of String objects containing the nessages.  This return
     * will never be null, but it can be an empty list.
     *
     */

    public List<String> getMessages() {
        return messages_;
    }

    /** Perform all of the conversion/upgrade steps except saving the result.
     * 
     * @return long containing the number of elements/attributes that were
     * modified during the conversion operation.  This is not particularly
     * useful to know, but might be of interest to user display messages.
     *
     */

    public long upgrade() {

        long numberConverted = 0;

        numberConverted += convertTwosComplement();
        numberConverted += convertEnumerationAlarmValue();
        numberConverted += convertMessageContainRef();
        numberConverted += convertEmptyUnitSets();
        numberConverted += convertValidRangeCalibratedAttribute();
        numberConverted += convertErrorDetectCRC();
        numberConverted += convertAlarmFormToRangeForm();
        numberConverted += convertBlockMetaCommandStepArguments();

        messages_.add( Long.toString( numberConverted ) +
                       " " + // NOI18N
                       XTCEFunctions.getText( "file_upgrade_count" ) ); // NOI18N

        return numberConverted;

    }

    /** Convert the twosComplement value change.
     *
     * Changing the value of an attribute
     *
     * @return long containing the number of encoding attributes that were
     * changed on IntegerDataEncoding elements.
     *
     */

    public long convertTwosComplement() {

        long numberConverted = 0;

        try {

            NodeList nodes =
                evaluateXPathQuery( "//xtce:IntegerDataEncoding[@encoding = 'twosCompliment']/@encoding" ); // NOI18N

            for ( int iii = 0; iii < nodes.getLength(); ++iii ) {
                nodes.item( iii ).setNodeValue( "twosComplement" ); // NOI18N
                ++numberConverted;
            }

        } catch ( Exception ex ) {
            messages_.add( XTCEFunctions.getText( "general_error_caps" ) + // NOI18N
                           " " + // NOI18N
                           ex.getLocalizedMessage() );
        }

        //System.out.println( "count = " + Long.toString( numberConverted ) );
        return numberConverted;

    }

    /** Convert the EnumerationAlarm attribute name.
     *
     * Changing the name of an attribute
     *
     * @return long containing the number of enumerationValue attributes that
     * were changed to enumerationLabel.
     *
     */

    public long convertEnumerationAlarmValue() {

        long numberConverted = 0;

        try {

            NodeList nodes =
                evaluateXPathQuery( "//xtce:EnumerationAlarmList/xtce:EnumerationAlarm/@enumerationValue" ); // NOI18N

            for ( int iii = 0; iii < nodes.getLength(); ++iii ) {
                getDocument().renameNode( nodes.item( iii ),
                                          nodes.item( iii ).getNamespaceURI(),
                                          "enumerationLabel" ); // NOI18N
                ++numberConverted;
            }

        } catch ( Exception ex ) {
            messages_.add( XTCEFunctions.getText( "general_error_caps" ) + // NOI18N
                           " " + // NOI18N
                           ex.getLocalizedMessage() );
        }

        //System.out.println( "count = " + Long.toString( numberConverted ) );
        return numberConverted;

    }

    /** Convert the MessageSet ContainerRef element to ContainerRef.
     *
     * Changing the name of an element
     *
     * @return long containing the number of ContainRef elements that were
     * renamed to ContainerRef in the MessageSet.
     *
     */

    public long convertMessageContainRef() {

        long numberConverted = 0;

        try {

            NodeList nodes =
                evaluateXPathQuery( "//xtce:TelemetryMetaData/xtce:MessageSet/xtce:Message/xtce:ContainRef" ); // NOI18N

            for ( int iii = 0; iii < nodes.getLength(); ++iii ) {
                Element element = (Element)nodes.item( iii );
                getDocument().renameNode( element,
                                          element.getNamespaceURI(),
                                          "xtce:ContainerRef" ); // NOI18N
                ++numberConverted;
            }

        } catch ( Exception ex ) {
            messages_.add( XTCEFunctions.getText( "general_error_caps" ) + // NOI18N
                           " " + // NOI18N
                           ex.getLocalizedMessage() );
        }

        //System.out.println( "count = " + Long.toString( numberConverted ) );
        return numberConverted;

    }

    /** Convert the empty UnitSet elements by removing them to reduce size.
     *
     * Removing an element
     *
     * @return long containing the number of ContainRef elements that were
     * renamed to ContainerRef in the MessageSet.
     *
     */

    public long convertEmptyUnitSets() {

        long numberConverted = 0;

        try {

            NodeList nodes =
                evaluateXPathQuery( "//xtce:UnitSet" ); // NOI18N

            for ( int iii = 0; iii < nodes.getLength(); ++iii ) {
                if ( nodes.item( iii ).hasChildNodes() == false ) {
                    Node parent = nodes.item( iii ).getParentNode();
                    parent.removeChild( nodes.item( iii ) );
                    NodeList children = parent.getChildNodes();
                    for ( int jjj = 0; jjj < children.getLength(); ++jjj ) {
                        if ( children.item( jjj ).getNodeType() == Node.TEXT_NODE ) {
                            parent.removeChild( children.item( jjj) );
                        }
                    }
                    ++numberConverted;
                }
            }

        } catch ( Exception ex ) {
            messages_.add( XTCEFunctions.getText( "general_error_caps" ) + // NOI18N
                           " " + // NOI18N
                           ex.getLocalizedMessage() );
        }

        //System.out.println( "count = " + Long.toString( numberConverted ) );
        return numberConverted;

    }

    /** Convert the validRangeAppliesToCalibrated attribute by relocating it.
     *
     * Move an attribute from one element to another
     *
     * @return long containing the number of ContainRef elements that were
     * renamed to ContainerRef in the MessageSet.
     *
     */

    public long convertValidRangeCalibratedAttribute() {

        long numberConverted = 0;

        try {

            String[] queries = {
                "//xtce:ParameterTypeSet/*/@validRangeAppliesToCalibrated", // NOI18N
                "//xtce:ArgumentTypeSet/*/@validRangeAppliesToCalibrated" // NOI18N
            };

            for ( String query : queries ) {

                NodeList nodes = evaluateXPathQuery( query );

                for ( int iii = 0; iii < nodes.getLength(); ++iii ) {

                    Element  parent   = ((Attr)nodes.item( iii )).getOwnerElement();
                    String   value    = nodes.item( iii ).getNodeValue();
                    NodeList children = parent.getChildNodes();

                    for ( int jjj = 0; jjj < children.getLength(); ++jjj ) {
                        Node node = children.item( jjj );
                        if ( ( node.getNodeType()                             == Node.ELEMENT_NODE ) &&
                             ( node.getNodeName().equals( "xtce:ValidRange" ) == true              ) ) { // NOI18N
                            ((Element)node).setAttribute( "validRangeAppliesToCalibrated", value ); // NOI18N
                        }
                    }

                    parent.removeAttribute( "validRangeAppliesToCalibrated" );

                    ++numberConverted;

                }

            }

        } catch ( Exception ex ) {
            messages_.add( XTCEFunctions.getText( "general_error_caps" ) + // NOI18N
                           " " + // NOI18N
                           ex.getLocalizedMessage() );
        }

        //System.out.println( "count = " + Long.toString( numberConverted ) );
        return numberConverted;

    }

    /** Convert the ErrorDetectCorrect CRC Polynomial element.
     *
     * Removing the contents of an element
     *
     * This conversion requires user interaction in that there is not enough
     * information in XTCE 1.1 to completely define the CRC algorithm
     * uniqueness to make the XTCE 1.2 representation complete.
     * 
     * I think I have enough information to perform an auto-convert entirely
     * if the polynomial resembles either CRC-CCITT, CRC-16-ANSI, and
     * CRC-32 POSIX/ISO 3309, but I need to make an assumption that someone is
     * not using an odd reflect/remainder/init/XOR setting(s).  Not sure if I
     * should do this or not.
     *
     * @return long containing the number of CRC Polynomial elements that
     * had their contents removed.
     *
     */

    public long convertErrorDetectCRC() {

        long numberConverted = 0;

        String query = "//xtce:ErrorDetectCorrect/xtce:CRC/xtce:Polynomial"; // NOI18N

        try {

            NodeList nodes = evaluateXPathQuery( query );

            for ( int iii = 0; iii < nodes.getLength(); ++iii ) {

                if ( nodes.item( iii ).hasChildNodes() == false ) {
                    continue;
                }

                NodeList children = nodes.item( iii ).getChildNodes();

                for ( int jjj = 0; jjj < children.getLength(); ++jjj ) {

                    if ( children.item( jjj ).getNodeType() != Node.ELEMENT_NODE ) {
                        continue;
                    }

                    Node typeNode = children.item( jjj )
                                            .getParentNode()
                                            .getParentNode()
                                            .getParentNode()
                                            .getParentNode()
                                            .getParentNode();

                    String typeName =
                        ((Element)typeNode).getAttribute( "name" ); // NOI18N

                    messages_.add( query +
                                   ": In Type named '" +
                                   typeName +
                                   "', Cannot auto-convert CRC.  " +
                                   "Term elements removed.  " +
                                   "Specify new attributes." );

                    nodes.item( iii ).setTextContent( "" );

                    ++numberConverted;

                    break;

                }

            }

        } catch ( Exception ex ) {
            messages_.add( XTCEFunctions.getText( "general_error_caps" ) + // NOI18N
                           " " + // NOI18N
                           ex.getLocalizedMessage() );
        }

        //System.out.println( "count = " + Long.toString( numberConverted ) );
        return numberConverted;

    }

    /** Convert the alarmForm attribute name.
     *
     * Changing the name of an attribute
     *
     * This only applies to a very small number of users who incorporated the
     * original XTCE 1.2 proposal with extensions provided by this toolkit.
     * Pure XTCE 1.1 users would not have any cases of this conversion.
     *
     * @return long containing the number of alarmForm attributes that
     * were changed to rangeForm.
     *
     */

    public long convertAlarmFormToRangeForm() {

        long numberConverted = 0;

        try {

            NodeList nodes =
                evaluateXPathQuery( "//xtce:StaticAlarmRanges/@alarmForm" ); // NOI18N

            for ( int iii = 0; iii < nodes.getLength(); ++iii ) {
                getDocument().renameNode( nodes.item( iii ),
                                          nodes.item( iii ).getNamespaceURI(),
                                          "rangeForm" ); // NOI18N
                ++numberConverted;
            }

        } catch ( Exception ex ) {
            messages_.add( XTCEFunctions.getText( "general_error_caps" ) + // NOI18N
                           " " + // NOI18N
                           ex.getLocalizedMessage() );
        }

        //System.out.println( "count = " + Long.toString( numberConverted ) );
        return numberConverted;

    }

    public long convertBlockMetaCommandStepArguments() {

        long numberConverted = 0;

        try {

            NodeList nodes =
                evaluateXPathQuery( "//xtce:CommandMetaData/xtce:MetaCommandSet/xtce:BlockMetaCommand/xtce:MetaCommandStepList/xtce:MetaCommandStep/xtce:ArgumentList" ); // NOI18N

            for ( int iii = 0; iii < nodes.getLength(); ++iii ) {
                getDocument().renameNode( nodes.item( iii ),
                                          nodes.item( iii ).getNamespaceURI(),
                                          "ArgumentAssignmentList" ); // NOI18N
                ++numberConverted;
                NodeList children = nodes.item( iii ).getChildNodes();
                for ( int jjj = 0; jjj < children.getLength(); ++jjj ) {
                    Node node = children.item( iii );
                    if ( ( node.getNodeType() == Node.ELEMENT_NODE ) &&
                         ( node.getNodeName().equals( "Argument" ) == true ) ) { // NOI18N
                        getDocument().renameNode( node,
                                                  node.getNamespaceURI(),
                                                  "ArgumentAssignment" ); // NOI18N
                        NamedNodeMap attrs = node.getAttributes();
                        Node nameAttr = attrs.getNamedItem( "name" ); // NOI18N
                        getDocument().renameNode( nameAttr,
                                                  nameAttr.getNamespaceURI(),
                                                  "argumentName" ); // NOI18N
                        Node valueAttr = attrs.getNamedItem( "value" ); // NOI18N
                        getDocument().renameNode( nameAttr,
                                                  nameAttr.getNamespaceURI(),
                                                  "argumentValue" ); // NOI18N
                    }
                }
            }

        } catch ( Exception ex ) {
            messages_.add( XTCEFunctions.getText( "general_error_caps" ) + // NOI18N
                           " " + // NOI18N
                           ex.getLocalizedMessage() );
        }

        //System.out.println( "count = " + Long.toString( numberConverted ) );
        return numberConverted;

    }

    /** Conclude the conversion/upgrade by saving the XML content to aa
     * automatically generated filename.
     *
     * The file will be overwritten if it already exists.  The old file name
     * will be used to create the new file name.  First it will try to replace
     * the ".xml" extension with "-1.2.xml".  If that does not succeed, then
     * the file name will receive "-1.2.xml" at the end to make the new file
     * name.
     *
     * @return boolean indicating if the save operation was successful.
     *
     */

    public boolean save() {

        String oldFile = getFilename().getAbsolutePath();
        String newFile = oldFile.replaceFirst( "\\.xml$", "-1.2.xml" ); // NOI18N

        if ( oldFile.equals( newFile ) == true ) {
            newFile = oldFile + "-1.2.xml"; // NOI18N
        }

        return save( new File( newFile ) );

    }

    /** Conclude the conversion/upgrade by saving the XML content to a provided
     * file.
     *
     * The file will be overwritten if it already exists.
     *
     * @param xmlFile File containing the path and name to the target filename
     * to save.
     *
     * @return boolean indicating if the save operation was successful.
     *
     */

    public boolean save( final File xmlFile ) {

        try {

            if ( messages_.isEmpty() == false ) {
                updateHistorySet();
            }

            saveDatabase( xmlFile );

        } catch ( Exception ex ) {

            messages_.add( XTCEFunctions.getText( "general_error_caps" ) + // NOI18N
                           " " + // NOI18N
                           ex.getLocalizedMessage() );

            return false;

        }

        setFilename( xmlFile );

        return true;

    }

    /** Update the History elements in the XTCE document before saving, only if
     * the document uses the Header element.
     *
     * @throws XTCEDatabaseException Unlikely to be thrown, but can occur if
     * the document is malformed according to the schema.
     *
     */

    private void updateHistorySet() throws XTCEDatabaseException {

        NodeList nodes = evaluateXPathQuery( "xtce:Header" );

        if ( nodes.getLength() == 0 ) {
            return;
        }

        // first find the HistorySet and if it does not exist under the Header
        // then make one.

        NodeList histNodes =
            ((Element)nodes.item( 0 )).getElementsByTagName( "xtce:HistorySet" );

        Element historySetElement;

        if ( histNodes.getLength() == 0 ) {
            historySetElement = getDocument().createElement( "xtce:HistorySet" );
            nodes.item( 0 ).appendChild( historySetElement );
        } else {
            historySetElement = ((Element)histNodes.item( 0 ));
        }

        // add each of the messages as a History element in the HistorySet

        for ( String message : messages_ ) {
            Element history = getDocument().createElement( "xtce:History" );
            history.setTextContent( message );
            historySetElement.appendChild( history );
        }

    }

    // Private Data Members

    private final List<String> messages_;

}