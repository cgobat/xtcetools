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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.omg.space.xtce.SpaceSystemType;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/** Helper class to do the XTCE document parsing to support the XTCEDatabase
 * class.
 *
 * @author dovereem
 *
 */

public abstract class XTCEDatabaseParser {

    /** Retrieve the current filename, in fully qualified path form, for the
     * XTCE database file that is loaded.
     *
     * @return String containing the name, or an empty string if this is a new
     * database that has not yet been saved to a filesystem.
     *
     */

    public final File getFilename( ) {
        return xtceFilename_;
    }

    /** Protected method to set a new filename when the database is saved.
     *
     * @param dbFile File object containing the file that was saved.
     *
     */

    protected final void setFilename( File dbFile ) {
        xtceFilename_ = dbFile.getAbsoluteFile();
    }

    /** Retrieve the changed flag indicating if some part of the XTCE document
     * has changed since it has last been saved.
     *
     * @return boolean indicating if a change has been made that has not been
     * yet saved.
     *
     */

    public final boolean getChanged( ) {
        return databaseChanged_;
    }

    /** Set the change flag to indicate whether or not this XTCE document has
     * been changed since the last time it was saved.
     *
     * @param changedFlag boolean to use to set the changed flag.
     *
     */

    public final void setChanged( boolean changedFlag ) {
        databaseChanged_ = changedFlag;
    }

    /** Retrieve the XTCE schema document that is applicable for this loaded
     * file.
     *
     * TODO: This only returns the default right now, need to work that.
     *
     * @return String containing the file and path to the schema document.
     *
     */

    public final String getSchemaFromDocument( ) {
        return XTCEConstants.DEFAULT_SCHEMA_FILE;
    }

    /** Retrieve the XTCE namespace that is applicable to the document that is
     * loaded.
     *
     * TODO: This only returns the default right now, need to work that.
     *
     * @return String containing the namespace.
     *
     */

    public final String getNamespaceFromDocument( ) {
        /// @todo work on this document namespace
        return XTCEConstants.XTCE_NAMESPACE;
    }

    /** Retrieve the list of warnings and errors that were collected during
     * parsing of the XTCE document by the SAX parser.
     *
     * @return List of strings that contain the warnings/errors when processing
     * a document, or an empty list if there are none.  The return will never
     * be null.
     *
     */

    public final List<String> getDocumentWarnings( ) {
        return warnings_;
    }

    /** Retrieve the count of error messages from the parsing.
     *
     * @return long count of the number of messages logged as errors when
     * parsing a document.
     *
     */

    public final long getErrorCount( ) {
        return errorCount_;
    }

    /** Retrieve the read only flag that was provided when the XTCE document
     * was loaded and parsed.
     *
     * When this flag is true, XPath queries and document saves cannot be used.
     *
     * @return boolean indicating if the document was opened read-only.
     *
     */

    public final boolean isReadOnly( ) {
        return ( domLoaded_ == false );
    }

    /** Method to perform XML Schema Description (XSD) verification on an
     * arbitrary XTCE document.
     *
     * @param dbFile File object containing the fully qualified filesystem path
     * to the XTCE document to be verified.
     *
     * @param applyXIncludes boolean indicating if the XInclude processing for
     * the loaded file should be applied or ignored.
     *
     * @return List of strings that contain the validation warning and error
     * messages from the validator.
     *
     */

    public static List<String> validateDocument( File    dbFile,
                                                 boolean applyXIncludes ) {

        String currentDir = System.getProperty( "user.dir" ); // NOI18N

        if ( dbFile.getParent() != null ) {
            File absPath = new File( dbFile.getParent() );
            System.setProperty( "user.dir", absPath.getAbsolutePath() ); // NOI18N
        }

        XTCESchemaErrorHandler handler = new XTCESchemaErrorHandler();

        try {

            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware( true );
            spf.setXIncludeAware( true );
            spf.setValidating( true );
            
            SAXParser parser = spf.newSAXParser();
            parser.setProperty( sunSchema_,
                                XMLConstants.W3C_XML_SCHEMA_NS_URI );

            XMLReader reader = parser.getXMLReader();
            reader.setErrorHandler( handler );
            reader.parse( new InputSource( dbFile.getName() ) );

        } catch ( ParserConfigurationException ex ) {
            List<String> messages = new ArrayList<>();
            messages.add( XTCEFunctions.getText( "xml_bad_configuration" ) + // NOI18N
                          ": " + ex.getLocalizedMessage() ); // NOI18N
            return messages;
        } catch ( SAXParseException ex ) {
            handler.fatalError( ex );
        } catch ( SAXException ex ) {
            handler.fatalError( ex );
        } catch ( IOException ex ) {
            handler.fatalError( ex );
        } finally {
            System.setProperty( "user.dir", currentDir ); // NOI18N
        }

        return handler.getMessages();

    }

    /** Method to create a new XTCE Document in memory, which is always created
     * in an editable state instead of read-only.
     *
     * @param spaceSystemName Name attribute of the root SpaceSystem element
     * to begin the document.
     *
     * @return SpaceSystemType object from the JAXB binding that represents
     * the newly created document.
     *
     * @throws XTCEDatabaseException in the event that the DocumentBuilder
     * cannot make a new document or JAXB cannot bind to the new document from
     * the DOM.  This is unlikely.
     *
     */

    protected SpaceSystemType newDatabase( String spaceSystemName )
        throws XTCEDatabaseException {

        // TODO check on cleanup within the catch of this function.

        try {

            jaxbContext_ =
                JAXBContext.newInstance( XTCEConstants.XTCE_PACKAGE );

            domBinder_ = jaxbContext_.createBinder();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            dbf.setXIncludeAware( true );
            dbf.setNamespaceAware( true );
            dbf.setValidating( false );

            DocumentBuilder db = dbf.newDocumentBuilder();

            domDocumentRoot_ = db.newDocument();
            domDocumentRoot_.setDocumentURI( XTCEConstants.XTCE_NAMESPACE );
            Element root =
                domDocumentRoot_.createElementNS( XTCEConstants.XTCE_NAMESPACE,
                                                  "SpaceSystem" ); // NOI18N
            root.setAttribute( "name", spaceSystemName ); // NOI18N
            domDocumentRoot_.appendChild( root );

            jaxbElementRoot_ =
                (JAXBElement)domBinder_.unmarshal( domDocumentRoot_ );

            Object candidate = jaxbElementRoot_.getValue();

            if ( candidate.getClass().equals( SpaceSystemType.class ) == false ) {
                String exceptionMessage =
                    XTCEFunctions.getText( "error_invalidxmlfile" ); // NOI18N
                throw new XTCEDatabaseException( exceptionMessage );
            }

            errorCount_ = 0;
            warnings_   = new ArrayList<>();
            domLoaded_  = true;

            return (SpaceSystemType)candidate;

        } catch ( Exception ex ) {
            throw new XTCEDatabaseException( ex );
        }

    }

    /** Protected method to read/parse an XTCE XML document and populate the
     * internal data members for this object.
     *
     * @param dbFile File object containing the XTCE XML document to load.
     *
     * @param validateOnLoad boolean to indicate if the XML document should be
     * validated through the XML Schema Description (XSD) document that is
     * configured for the XTCEDatabase object.  In the future this will be more
     * flexible.
     *
     * @param applyXIncludes boolean indicating if the XInclude processing for
     * the loaded file should be applied or ignored.
     *
     * @param readOnly boolean indicating if the Document Object Model should
     * be constructed.  This is needed for round trip editing, but it is slower
     * than just the pure JAXB implementation.
     *
     * @return SpaceSystemType XTCE document element that was loaded.
     *
     * @throws XTCEDatabaseException thrown in the event that the document
     * cannot be read or is not sufficiently valid to complete construction of
     * the internal data model.
     *
     */

    protected SpaceSystemType loadDatabase( File    dbFile,
                                            boolean validateOnLoad,
                                            boolean applyXIncludes,
                                            boolean readOnly )
        throws XTCEDatabaseException {

        String path = null;

        if ( dbFile.getParent() != null ) {
            File absPath = new File( dbFile.getParent() );
            path = absPath.getAbsolutePath();
        }

        try {

            InputStream istream = new FileInputStream( dbFile );

            return loadDatabase( istream,
                                 path,
                                 validateOnLoad,
                                 applyXIncludes,
                                 readOnly );
            
        } catch ( FileNotFoundException ex ) {

            String exceptionMessage =
                XTCEFunctions.getText( "file_chooser_noload_text" ) + // NOI18N
                " " + // NOI18N
                dbFile.toString();
            throw new XTCEDatabaseException( exceptionMessage );

        }

    }

    /** Protected method to read/parse an XTCE XML document and populate the
     * internal data members for this object.
     *
     * @param dbLocation URL containing a location to load the document from.
     *
     * @param validateOnLoad boolean to indicate if the XML document should be
     * validated through the XML Schema Description (XSD) document that is
     * configured for the XTCEDatabase object.  In the future this will be more
     * flexible.
     *
     * @param applyXIncludes boolean indicating if the XInclude processing for
     * the loaded file should be applied or ignored.
     *
     * @param readOnly boolean indicating if the Document Object Model should
     * be constructed.  This is needed for round trip editing, but it is slower
     * than just the pure JAXB implementation.
     *
     * @return SpaceSystemType XTCE document element that was loaded.
     *
     * @throws XTCEDatabaseException thrown in the event that the document
     * cannot be read or is not sufficiently valid to complete construction of
     * the internal data model.
     *
     */

    protected SpaceSystemType loadDatabase( URL     dbLocation,
                                            boolean validateOnLoad,
                                            boolean applyXIncludes,
                                            boolean readOnly )
        throws XTCEDatabaseException {

        try {

            InputStream istream = dbLocation.openStream();

            return loadDatabase( istream,
                                 null,
                                 validateOnLoad,
                                 applyXIncludes,
                                 readOnly );
            
        } catch ( Exception ex ) {

            String msg = XTCEFunctions.getText( "error_urlnotfound" ) + // NOI18N
                         ": " + // NOI18N
                         ex.getLocalizedMessage();
            throw new XTCEDatabaseException( msg );

        }

    }

    /** Protected method to read/parse an XTCE XML document and populate the
     * internal data members for this object.
     *
     * @param dbStream InputStream object containing the XTCE XML document to
     * load.
     *
     * @param path String containing the current working directory for the
     * stream, which is needed for XInclude, or null if not possible to get.
     *
     * @param validateOnLoad boolean to indicate if the XML document should be
     * validated through the XML Schema Description (XSD) document that is
     * configured for the XTCEDatabase object.  In the future this will be more
     * flexible.
     *
     * @param applyXIncludes boolean indicating if the XInclude processing for
     * the loaded file should be applied or ignored.
     *
     * @param readOnly boolean indicating if the Document Object Model should
     * be constructed.  This is needed for round trip editing, but it is slower
     * than just the pure JAXB implementation.
     *
     * @return SpaceSystemType XTCE document element that was loaded.
     *
     * @throws XTCEDatabaseException thrown in the event that the document
     * cannot be read or is not sufficiently valid to complete construction of
     * the internal data model.
     *
     */

    protected SpaceSystemType loadDatabase( InputStream dbStream,
                                            String      path,
                                            boolean     validateOnLoad,
                                            boolean     applyXIncludes,
                                            boolean     readOnly )
        throws XTCEDatabaseException {

        try {
            jaxbContext_ =
                JAXBContext.newInstance( XTCEConstants.XTCE_PACKAGE );
        } catch ( Exception ex ) {
            throw new XTCEDatabaseException( ex );
        }

        if ( readOnly == true ) {
            return loadReadOnlyDatabase( dbStream,
                                         path,
                                         validateOnLoad,
                                         applyXIncludes );
        } else {
            return loadEditableDatabase( dbStream,
                                         path,
                                         validateOnLoad,
                                         applyXIncludes );
        }

    }

    /** Private method to implement a document load operation for read-only
     * mode.
     *
     * Read only mode only initializes the JAXB binding.  It skips the DOM
     * binding in order to save time and memory.
     *
     * @param dbStream InputStream containing the XML to retrieve.
     *
     * @param path The path to the XML document.  This get set so that XInclude
     * and schema validation can find their artifacts from the xml base.
     *
     * @param validateOnLoad boolean indicating if XSD validation should be
     * performed on load.
     *
     * @param applyXIncludes boolean indicating if XInclude elements should
     * be resolved.
     *
     * @return SpaceSystemType element from the JAXB model of the root Space
     * System of the XTCE document data.
     *
     * @throws XTCEDatabaseException in the event that the file could not be
     * loaded.
     *
     */

    private SpaceSystemType loadReadOnlyDatabase( InputStream dbStream,
                                                  String      path,
                                                  boolean     validateOnLoad,
                                                  boolean     applyXIncludes )
        throws XTCEDatabaseException {

        String currentDir = System.getProperty( "user.dir" ); // NOI18N

        if ( path != null ) {
            System.setProperty( "user.dir", path ); // NOI18N
        }

        XTCESchemaErrorHandler handler = new XTCESchemaErrorHandler();

        try {

            SchemaFactory factory =
                SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            Schema schema = factory.newSchema();

            Unmarshaller     um  = jaxbContext_.createUnmarshaller();
            SAXParserFactory spf = SAXParserFactory.newInstance();

            spf.setXIncludeAware( applyXIncludes );
	    spf.setNamespaceAware( true );
            if ( validateOnLoad == true ) {
                spf.setSchema( schema );
            }

            SAXParser parser = spf.newSAXParser();
            //parser.setProperty( sunSchema_,
            //                    XMLConstants.W3C_XML_SCHEMA_NS_URI );

            XMLReader reader = parser.getXMLReader();
            reader.setEntityResolver(new XTCEEntityResolver());
            reader.setErrorHandler( handler );
            um.setEventHandler( handler );

            SAXSource source =
                new SAXSource( reader, new InputSource( dbStream ) );

            jaxbElementRoot_ = (JAXBElement)um.unmarshal( source );

            errorCount_ = handler.getErrorCount();
            warnings_   = handler.getMessages();

            Object candidate = jaxbElementRoot_.getValue();

            if ( candidate.getClass().equals( SpaceSystemType.class ) == false ) {
                String exceptionMessage =
                    XTCEFunctions.getText( "error_invalidxmlfile" ); // NOI18N
                throw new XTCEDatabaseException( exceptionMessage );
            }

            domLoaded_       = false;
            domDocumentRoot_ = null;
            domBinder_       = null;

            return (SpaceSystemType)candidate;

        } catch ( UnmarshalException ex ) {
            final List<String> msgs = handler.getMessages();
            msgs.add( XTCEFunctions.getText( "xml_io_fatal" ) ); // NOI18N
            throw new XTCEDatabaseException( msgs );
        } catch ( NumberFormatException ex ) {
            String msg = XTCEFunctions.generalErrorPrefix() +
                         XTCEFunctions.getText( "general_numberexception" ) + // NOI18N
                         " '" + // NOI18N
                         ex.getLocalizedMessage() +
                         "'"; // NOI18N
            List<String> msgs = handler.getMessages();
            msgs.add( msg );
            throw new XTCEDatabaseException( msgs );
        } catch ( Exception ex ) {
            throw new XTCEDatabaseException( ex ); 
        } finally {
            System.setProperty( "user.dir", currentDir ); // NOI18N
        }

    }

    /** Private method to implement a document load operation for edit mode.
     *
     * Editable mode initializes both the JAXB binding as well as the DOM
     * binding.  This is needed for XPath and round trip output.  It has the
     * disadvantage of needing more memory and is slower to startup.
     *
     * @param dbStream InputStream containing the XML to retrieve.
     *
     * @param path The path to the XML document.  This get set so that XInclude
     * and schema validation can find their artifacts from the xml base.
     *
     * @param validateOnLoad boolean indicating if XSD validation should be
     * performed on load.
     *
     * @param applyXIncludes boolean indicating if XInclude elements should
     * be resolved.
     *
     * @return SpaceSystemType element from the JAXB model of the root Space
     * System of the XTCE document data.
     *
     * @throws XTCEDatabaseException in the event that the file could not be
     * loaded.
     *
     */

    private SpaceSystemType loadEditableDatabase( InputStream dbStream,
                                                  String      path,
                                                  boolean     validateOnLoad,
                                                  boolean     applyXIncludes )
        throws XTCEDatabaseException {

        String currentDir = System.getProperty( "user.dir" ); // NOI18N

        if ( path != null ) {
            System.setProperty( "user.dir", path ); // NOI18N
        }

        XTCESchemaErrorHandler handler = new XTCESchemaErrorHandler();

        try {

            domBinder_ = jaxbContext_.createBinder();
            domBinder_.setEventHandler( handler );

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            dbf.setXIncludeAware( applyXIncludes );
            dbf.setNamespaceAware( true );
            dbf.setValidating( validateOnLoad );

            if ( validateOnLoad == true ) {
                dbf.setAttribute( sunSchema_,
                                  XMLConstants.W3C_XML_SCHEMA_NS_URI );
            }

            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler( handler );

            domDocumentRoot_ = db.parse( dbStream );

            jaxbElementRoot_ =
                (JAXBElement)domBinder_.unmarshal( domDocumentRoot_ );

            errorCount_ = handler.getErrorCount();
            warnings_   = handler.getMessages();

            Object candidate = jaxbElementRoot_.getValue();

            if ( candidate.getClass().equals( SpaceSystemType.class ) == false ) {
                String exceptionMessage =
                    XTCEFunctions.getText( "error_invalidxmlfile" ); // NOI18N
                throw new XTCEDatabaseException( exceptionMessage );
            }
            
            domLoaded_ = true;

            return (SpaceSystemType)candidate;

        } catch ( UnmarshalException ex ) {
            final List<String> msgs = handler.getMessages();
            msgs.add( XTCEFunctions.getText( "xml_io_fatal" ) ); // NOI18N
            throw new XTCEDatabaseException( msgs );
        } catch ( NumberFormatException ex ) {
            String msg = XTCEFunctions.generalErrorPrefix() +
                         XTCEFunctions.getText( "general_numberexception" ) + // NOI18N
                         " '" + // NOI18N
                         ex.getLocalizedMessage() +
                         "'"; // NOI18N
            List<String> msgs = handler.getMessages();
            msgs.add( msg );
            throw new XTCEDatabaseException( msgs );
        } catch ( Exception ex ) {
            throw new XTCEDatabaseException( ex );    
        } finally {
            System.setProperty( "user.dir", currentDir ); // NOI18N
        }

    }

    /** Method to write the current XTCE Document in memory to a File object.
     *
     * @param dbFile File containing the location to write the database XML.
     *
     * @throws XTCEDatabaseException in the event that the document is opened
     * read-only, in which case the DOM is not loaded or initialized.
     *
     */

    protected void saveDatabase( File dbFile ) throws XTCEDatabaseException {

        if ( isReadOnly() == true ) {
            throw new XTCEDatabaseException(
                XTCEFunctions.getText( "error_isreadonly" ) ); // NOI18N
        }

        TransformerFactory tfactory = TransformerFactory.newInstance();

        try {

            // thinking about this update, maybe it should be done when nodes
            // are edited to keep in sync for xpath

            if ( databaseChanged_ == true ) {
                domBinder_.updateXML( jaxbElementRoot_ );
            }

            Transformer xformer = tfactory.newTransformer();

            xformer.setOutputProperty( OutputKeys.INDENT, "yes" );
            xformer.setOutputProperty( "{http://xml.apache.org/xslt}indent-amount", "4" );

            xformer.transform( new DOMSource( domDocumentRoot_ ),
                               new StreamResult( dbFile ) );

        } catch ( Exception ex ) {
            throw new XTCEDatabaseException( ex );
        }
        
    }

    /** Method to normalize the whitespace in the XML Document Object Model to
     * make saved documents look clean.
     *
     * @throws XTCEDatabaseException in the event that the document is not well
     * formed or it is loaded read-only, in which case the Document Object
     * Model is not in use.
     *
     */

    public void normalizeDocument() throws XTCEDatabaseException {

        if ( isReadOnly() == true ) {
            throw new XTCEDatabaseException(
                XTCEFunctions.getText( "error_isreadonly" ) ); // NOI18N
        }

        String xpath = "//text()[normalize-space(.) = '']";

        NodeList nodes = evaluateXPathQuery( xpath );

        for( int iii = 0; iii < nodes.getLength(); ++iii ) {
            Node node = nodes.item( iii );
            node.getParentNode().removeChild( node );
        }

    }

    /** Method to return the root document element from the Document Object
     * Model (DOM) of this XTCE document.
     *
     * @return Element containing the root SpaceSystem.
     *
     * @throws XTCEDatabaseException in the event that the document is opened
     * read-only, in which case the DOM is not loaded or initialized.
     *
     */

    public final Element getDocumentElement() throws XTCEDatabaseException {

        if ( isReadOnly() == true ) {
            throw new XTCEDatabaseException(
                XTCEFunctions.getText( "error_isreadonly" ) ); // NOI18N
        }

        return domDocumentRoot_.getDocumentElement();

    }

    /** Method to return the document object from the Document Object Model
     * (DOM) of this XTCE document.
     *
     * @return Element containing the root SpaceSystem.
     *
     * @throws XTCEDatabaseException in the event that the document is opened
     * read-only, in which case the DOM is not loaded or initialized.
     *
     */

    public final Document getDocument() throws XTCEDatabaseException {

        if ( isReadOnly() == true ) {
            throw new XTCEDatabaseException(
                XTCEFunctions.getText( "error_isreadonly" ) ); // NOI18N
        }

        return domDocumentRoot_;

    }

    /** Evaluate an arbitrary XPath Query Expression and return a generic
     * NodeList object back or an exception with an error message.
     *
     * @param query String containing the XPath expression.
     *
     * @return NodeList containing 0 or more nodes that were located by the
     * query.
     *
     * @throws XTCEDatabaseException in the event that the query contains an
     * error or cannot otherwise be evaluated against the DOM tree.
     *
     */

    public NodeList evaluateXPathQuery( String query ) throws XTCEDatabaseException {

        if ( isReadOnly() == true ) {
            throw new XTCEDatabaseException(
                XTCEFunctions.getText( "error_xpath_readonly" ) ); // NOI18N
        }

        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext( new XTCENamespaceContext() );

        try {

            XPathExpression expr = xpath.compile( query );
            NodeList nnn = (NodeList)expr.evaluate( getDocumentElement(),
                                                    XPathConstants.NODESET );
            return nnn;

        } catch ( XPathException ex ) {
            throw new XTCEDatabaseException( ex );
        }

    }

    /** Rename an Element node in the Document Object Model assuming that it is
     * the XTCE namespace but consider that the XTCE URI might be the default
     * namespace, in which case there is no prefix, or it could have the
     * xtce: prefix.
     *
     * @param element Element to rename
     *
     * @param newName String containing the new element name
     *
     * @throws XTCEDatabaseException in the event that the document object
     * does not exist because the document is in read-only mode.
     *
     */

    protected void renameElement( final Element element,
                                  final String  newName )
        throws XTCEDatabaseException {

        String prefix = element.getPrefix();

        if ( prefix == null ) {
            prefix = ""; // NOI18N
        } else {
            prefix += ":"; // NOI18N
        }

        getDocument().renameNode( element,
                                  element.getNamespaceURI(),
                                  prefix + newName );

    }

    /** Remove an attribute node from an element in the Document Object Model
     * when the attribute contains the default value.
     *
     * @param element Element object from the DOM to search for the attribute.
     *
     * @param name String containing the name of the attribute to check.
     *
     * @param value String containing the default value of the attribute.
     *
     * @return boolean indicating if the attribute was removed.
     *
     */

    protected boolean removeDefaultAttribute( final Element element,
                                              final String  name,
                                              final String  value ) {

        Attr attrNode = element.getAttributeNode( name );

        if ( ( attrNode                            != null ) &&
             ( attrNode.getValue().equals( value ) == true ) ) {

            element.removeAttributeNode( attrNode );
            return true;

        }

        return false;

    }

    /** Update the History elements in the XTCE document before saving, only if
     * the document uses the Header element.
     *
     * @param messages List of strings to add to the HistorySet element with
     * each message being 1 new History element.
     *
     * @throws XTCEDatabaseException Unlikely to be thrown, but can occur if
     * the document is malformed according to the schema.
     *
     */

    protected void updateHistorySet( final List<String> messages )
        throws XTCEDatabaseException {

        NodeList nodes = evaluateXPathQuery( "xtce:Header" ); // NOI18N

        if ( nodes.getLength() == 0 ) {
            return;
        }

        // first find the HistorySet and if it does not exist under the Header
        // then make one.

        NodeList histNodes =
            ((Element)nodes.item( 0 )).getElementsByTagName( "xtce:HistorySet" ); // NOI18N

        Element historySetElement;

        if ( histNodes.getLength() == 0 ) {
            historySetElement = getDocument().createElement( "xtce:HistorySet" ); // NOI18N
            nodes.item( 0 ).appendChild( historySetElement );
        } else {
            historySetElement = ((Element)histNodes.item( 0 ));
        }

        // add each of the messages as a History element in the HistorySet

        for ( String message : messages ) {
            Element history = getDocument().createElement( "xtce:History" ); // NOI18N
            history.setTextContent( message );
            historySetElement.appendChild( history );
        }

        databaseChanged_ = true;

    }

    // Private Data Members

    private boolean      databaseChanged_ = false;
    private File         xtceFilename_    = null;
    private List<String> warnings_        = new ArrayList<>();
    private long         errorCount_      = 0;
    private boolean      domLoaded_       = true;
    private JAXBContext  jaxbContext_     = null;
    private JAXBElement  jaxbElementRoot_ = null;
    private Document     domDocumentRoot_ = null;
    private Binder<Node> domBinder_       = null;

    private static final String sunSchema_ =
        "http://java.sun.com/xml/jaxp/properties/schemaLanguage"; // NOI18N

}
