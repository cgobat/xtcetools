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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.omg.space.xtce.AbsoluteTimeDataType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** This class is a container to capture some common static functions that are
 * applicable throughout the toolkit.
 *
 * This class is not meant to be constructed, since the functions are static.
 *
 * @author David Overeem
 *
 */

public class XTCEFunctions {

    /** Private Constructor
     *
     * This is to prevent a class of this type from being instantiated.  It has
     * no non-static functions or data members.
     *
     */

    private XTCEFunctions() { }

    /** Method to register a custom time handler for XTCE AbsoluteTimeDataType
     * elements.
     *
     * @param instance Constructed object of a type that implements the
     * interface XTCEAbsoluteTimeType in toolkit.
     *
     */

    public static void registerAbsoluteTimeHandler( final XTCEAbsoluteTimeType instance ) {

        ensureDefaultRegistration();

        absTimeHandlers_.add( instance );

    }

    /** Method to locate a time handler for an XTCE AbsoluteTimeDataType
     * element.
     *
     * Handlers are searched in reverse order of addition, so the newest is
     * always checked first.
     *
     * @param element AbsoluteTimeDataType XML element from an XTCE document.
     *
     * @return Handler that implements the XTCEAbsoluteTimeType class in this
     * toolkit that matches the XML element required contents.  If not handler
     * exists, then the function throws.
     *
     * @throws XTCEDatabaseException thrown in the event that a handler is not
     * available or not usable for this element.
     *
     */

    public static XTCEAbsoluteTimeType getAbsoluteTimeHandler( final AbsoluteTimeDataType element ) throws XTCEDatabaseException {

        ensureDefaultRegistration();

        for ( int iii = absTimeHandlers_.size() - 1; iii >= 0; --iii ) {
            try {
                if ( absTimeHandlers_.get( iii ).isApplicable( element ) == true ) {
                    return absTimeHandlers_.get( iii );
                }
            } catch ( Exception ex ) {
                throw new XTCEDatabaseException(
                    XTCEFunctions.getText( "error_bad_abstime_handler" ) + // NOI18N
                    " '" +
                    absTimeHandlers_.get( iii ).getClass().getName() + "': " + // NOI18N
                    ex.getLocalizedMessage() );
            }
        }

        throw new XTCEDatabaseException(
            XTCEFunctions.getText( "error_noabstime_handler" ) ); // NOI18N

    }

    /** This function converts a byte array to a BitSet suitable for use in the
     * container content processing when applying a binary data set.
     *
     * The BitSet is sequenced where bit 0 is the first bit in the data bytes,
     * progressing forward in count until the last bit in the data bytes.
     *
     * @param bytes byte[] array of raw binary data.
     *
     * @return BitSet containing the binary data exactly sized to the number
     * of bytes, so it will always be a multiple of 8 bits in size.
     *
     */

    public static BitSet getBitSetFromByteArray( final byte[] bytes ) {

        //BitSet bits = new BitSet( bytes.length * 8 );

        //for ( int iii = 0; iii < bytes.length; ++iii ) {
        //    for ( int jjj = 0; jjj < 8; ++jjj ) {
        //        if ( ( bytes[iii] & ( 1 << ( 7 - jjj ) ) ) != 0 ) {
        //            bits.set( ( iii * 8 ) + jjj );
        //        }
        //    }
        //}

        //return bits;
        return BitSet.valueOf( bytes );

    }

    /** This function converts a BitSet from the encode functions to a byte
     * array in the order it would exist on the wire in that the zeroth index
     * is the first byte read/written.
     *
     * @param rawBits BitSet containing the raw binary data.
     *
     * @param minBytes int containing the number of bytes expected.  Sometimes
     * the BitSet can be short or long due to allocations.
     *
     * @return byte[] containing the data for streaming or printing.
     *
     */

    public static byte[] getStreamByteArrayFromBitSet( final BitSet rawBits,
                                                       final int    minBytes ) {

        byte[] bytes = new byte[minBytes];

        int byteCounter = -1;
        int bitSize     = rawBits.length();
        int remainder;

        for ( int iii = 0; iii < ( minBytes * 8 ); ++iii ) {

            remainder = iii % 8;

            if ( remainder == 0 ) {
                ++byteCounter;
            }

            if ( ( iii < bitSize ) && ( rawBits.get( iii ) == true ) ) {
                bytes[byteCounter] += 1 << ( 7 - remainder );
            }
            
        }

        return bytes;

    }

    /** This function converts a byte array to a BitSet suitable for use in the
     * container content processing when applying a binary data set.
     *
     * The BitSet is sequenced where bit 0 is the first bit in the data bytes,
     * progressing forward in count until the last bit in the data bytes.
     *
     * @param bytes byte[] array of raw binary data.
     *
     * @return BitSet containing the binary data exactly sized to the number
     * of bytes, so it will always be a multiple of 8 bits in size.
     *
     */

    public static BitSet getBitSetFromStreamByteArray( final byte[] bytes ) {

        BitSet bits = new BitSet( bytes.length * 8 );

        for ( int iii = 0; iii < bytes.length; ++iii ) {
            //System.out.println( "Byte: 0x" + String.format( "%02x", bytes[iii] ) );
            for ( int jjj = 0; jjj < 8; ++jjj ) {
                if ( ( bytes[iii] & ( 1 << ( 7 - jjj ) ) ) != 0 ) {
                    bits.set( ( iii * 8 ) + jjj );
                    //System.out.println( "Setting bit " + Integer.toString( ( iii * 8 ) + jjj ) + " 1" );
                } else {
                    //System.out.println( "Setting bit " + Integer.toString( ( iii * 8 ) + jjj ) + " 0" );
                }
            }
        }

        return bits;

    }

    /** Convert a BitSet object to a hex byte string, ordered from the most
     * significant byte to the least significant byte.
     *
     * The most significant bits are padded with zero in this case when the
     * raw size is not on an even 8 bit boundary.  This results in the function
     * never returning a hex string that is less than 2 characters for each
     * byte, with a minimum of 1 byte.  A "0x" is prepended.
     *
     * @param bits BitSet containing an arbitrarily long binary field.
     *
     * @param minBytes integer containing the minimum number of bytes to output
     * on the hex string.  This results in 0 padding if the BitSet is too
     * small.
     *
     * @return String containing the hex of the raw value, subject to the
     * explanation above associated with this function.
     *
     */

    public static String bitSetToHex( final BitSet bits, final int minBytes ) {

        StringBuilder sb = new StringBuilder( "0x" ); // NOI18N

        byte[] bytes = bits.toByteArray();
        for ( int iii = minBytes - 1; iii >= 0; --iii ) {
            if ( iii < bytes.length ) {
                sb.append( String.format( "%02x", bytes[iii] ) ); // NOI18N
            } else {
                sb.append( "00" ); // NOI18N
            }
        }

        return sb.toString();

    }

    /** This function mimics the behavior of the ubiquitous filesystem function
     * called realpath().
     *
     * The purpose is to take a filesystem path reference, which may contain
     * relative or absolute path, and may also contain the . or .. or multiple
     * slashes, and convert that to an absolute path reference.  In order to do
     * this, the candidate path provided by a user (in this case, an XTCE
     * document) and also the current working Space System path are needed.
     *
     * @param currentPath The current working Space System path from which the
     * candidate path reference is to be resolved from.
     *
     * @param pathReference The candidate path reference, generally from an
     * XTCE containerRef, parameterRef, or argumentRef.
     *
     * @return String that will always contain the absolute path fully resolved
     * for the candidate path reference.
     *
     */

    public static String resolvePathReference( final String currentPath,
                                               final String pathReference ) {

        // TODO: This function takes a big performance hit.

        StringBuilder fullPath = new StringBuilder();
        if ( pathReference.charAt( 0 ) != '/' ) { // NOI18N
            fullPath.append( currentPath );
        }
        
        String[] fields = pathReference.split( "/" ); // NOI18N
        for ( String field : fields ) {
            if ( field.equals( ".." ) == true ) {
                int index = fullPath.lastIndexOf( "/" ); // NOI18N
                if ( index >= 0 ) {
                   fullPath = fullPath.delete( index, fullPath.length() );
                }
            } else if ( field.equals( "." ) == true ) { // NOI18N
                // do nothing
            } else if ( field.isEmpty() == false ) {
                fullPath.append( '/' ); // NOI18N
                fullPath.append( field );
            }
        }

        String candidate = fullPath.toString();

        // this takes half the time called in this function, there should be
        // a better way to avoid this

        return candidate.replaceAll( "/+", "/" ); // NOI18N

    }

    /** This function is for stripping the path off an path reference in the
     * XTCE data model for containerRef, parameterRef, and argumentRef, amongst
     * others.
     *
     * @param filepath An XTCE style path reference string that contains the
     * Space System path leading up to the object of interest, such as a
     * Container, Parameter, or Argument, amongst others.
     * 
     * @return String containing just the path to the object name.
     *
     */

    public static String getPathNameFromReferenceString( final String filepath ) {

        int idx  = filepath.lastIndexOf( '/' ); // NOI18N
        if ( idx == -1 ) {
            return ""; // NOI18N
        }
        return filepath.substring( 0, idx );

    }

    /** This function is for stripping off object name from an XTCE path
     * reference from the data model for containerRef, parameterRef, and
     * argumentRef, amongst others.
     *
     * @param filepath An XTCE style path reference string that contains the
     * Space System path leading up to the object of interest, such as a
     * Container, Parameter, or Argument, amongst others.
     * 
     * @return String containing just the name of the object.
     *
     */

    public static String getNameFromPathReferenceString( final String filepath ) {

        int idx  = filepath.lastIndexOf( '/' ); // NOI18N
        if ( idx == -1 ) {
            return filepath;
        }
        return filepath.substring( idx + 1 );

    }

    /** This function formats the aliases on a named and typed object based on
     * some preferences.
     *
     * The format of the returned string will have each alias space delimited,
     * unless there is only one.  The "showAllNamespaces" takes precedence
     * over the "preferredNamespace".  The string will be in the form
     * NS1:ALIAS1 NS2:ALIAS2 if the "showAliasNamespaces" option is true.
     * Otherwise, it will just be ALIAS1 ALIAS2.
     * 
     * @param parameter XTCENamedObject containing any of the objects that can
     * be aliased in the XTCE data model and are supported by this toolkit.
     *
     * @param showAllNamespaces boolean indicating if all of the namespaces
     * should appear in the string.
     *
     * @param showAliasNamespaces boolean indicating if the alias names should
     * be included in the alias string.
     *
     * @param preferredNamespace String containing the preferred namespace if
     * there is more than one defined.
     *
     * @return String for display purposes.
     *
     */

    public static String makeAliasDisplayString( final XTCENamedObject parameter,
                                                 final boolean         showAllNamespaces,
                                                 final boolean         showAliasNamespaces,
                                                 final String          preferredNamespace ) {

        String myPreferredNamespace = preferredNamespace;

        if ( myPreferredNamespace == null ) {
            myPreferredNamespace = ""; // NOI18N
        }

        List<XTCEAlias> aliasList    = parameter.getAliasSet();
        StringBuilder   aliasDisplay = new StringBuilder();

        for ( XTCEAlias entry : aliasList ) {
            if ( ( showAllNamespaces                                   == true ) ||
                 ( myPreferredNamespace.equals( entry.getNameSpace() ) == true ) ) {
                if ( aliasDisplay.length() > 0 ) {
                    aliasDisplay.append( ' ' ); // NOI18N
                }
                if ( showAliasNamespaces == true ) {
                    aliasDisplay.append( entry.getFullAliasName() );
                } else {
                    aliasDisplay.append( entry.getAliasName() );
                }
            }
        }

        return aliasDisplay.toString();

    }

    /** Retrieve a "cleaned up" hex string from a user input hexadecimal field.
     *
     * The output will have any leading "0x" removed, will be in lower case,
     * will contain no whitespace, no commas, no periods, or semicolons.  At
     * present this function does not guarantee viability of the string from a
     * "NumberFormatException" when parsing.
     *
     * @param inputText String containing user input text.
     *
     * @return String suitable for parsing the text in base 16.
     *
     */

    public static String getCleanHexString( final String inputText ) {

        String text = inputText.replaceAll( "[ ,;\r\f\n\t.]", "" ); // NOI18N
        text = text.toLowerCase();
        if ( text.startsWith( "0x" ) == true ) { // NOI18N
            text = text.replaceFirst( "0x", "" ); // NOI18N
        }
        return text;

    }

    /** Retrieves a byte array from a hexadecimal stream represented by a
     * string.
     *
     * @param hex String containing the user provided hexadecimal string.  It
     * is recommended to call the "getCleanHexString()" on this text first.
     *
     * @return byte[] byte array of sufficient size to capture the hexadecimal
     * string provided.
     *
     * @throws NumberFormatException if the string cannot be interpreted as a
     * hex string by the Java Integer class.
     *
     */

    public static byte[] getBytesFromHexString( final String hex ) {

        int byteCount = hex.length() / 2;
        if ( hex.length() % 2 != 0 ) {
            byteCount += 1;
        }

        byte[] bytes = new byte[byteCount];

        for ( int iii = 0; iii < hex.length(); iii += 2 ) {

            int startIdx = iii;
            int endIdx   = iii + 2;
            if ( endIdx > hex.length() ) {
                endIdx = hex.length();
            }

            String value = hex.substring( startIdx, endIdx );
            if ( value.length() == 1 ) {
                value += "0"; // NOI18N
            }

            bytes[iii / 2] = (byte)Integer.parseInt( value, 16 );

        }

        return bytes;

    }

    /** A matcher function that uses the glob style algorithm to determine if
     * a candidate string matches a glob style pattern.
     *
     * This function is to the credit of a suggested algorithm found on many
     * stackoverflow.com searches.  It is not the authors original work.  The
     * posting user name is "Mihi" who links to a website at:
     * http://schierlm.users.sourceforge.net  The stackoverflow thread that
     * contains this code is at:
     * http://stackoverflow.com/questions/1247772/is-there-an-equivalent-of-java-util-regex-for-glob-type-patterns
     *
     * @param text String containing the candidate text to match.
     *
     * @param glob String containing the glob pattern, probably provided by a
     * user to match something from a list.
     *
     * @return boolean indicating if the seatch text matches the glob by the
     * glob algorithm rules.
     *
     */

    public static boolean matchesUsingGlob( final String text, String glob ) {

        String rest = null;
        int pos = glob.indexOf( '*' ); // NOI18N
        if ( pos != -1 ) {
            rest = glob.substring( pos + 1 );
            glob = glob.substring( 0, pos );
        }

        if ( glob.length() > text.length() ) {
            return false;
        }

        // handle the part up to the first *
        for ( int iii = 0; iii < glob.length(); ++iii ) {
            if ( ( glob.charAt( iii ) != '?' ) && // NOI18N
                 ( ! glob.substring( iii, iii + 1 ).equals(text.substring( iii, iii + 1 ) ) ) ) {
                return false;
            }
        }

        // recurse for the part after the first *, if any
        if ( rest == null ) {
            return glob.length() == text.length();
        } else {
            for ( int iii = glob.length(); iii <= text.length(); ++iii ) {
                if ( matchesUsingGlob( text.substring( iii ), rest ) == true ) {
                    return true;
                }
            }
            return false;
        }

    }

    /** This function supports I18N and L10N for the XTCE toolkit by permitting
     * this class to maintain the statics for internationalization and making
     * it easy for calling code to get internationalized strings from anywhere.
     *
     * @param key String containing the key to lookup the desired text message
     * from the locale appropriate properties file.
     *
     * @return String to display in the local language and country preference.
     *
     */

    public static String getText( final String key ) {

        if ( messages_ == null ) {
            setLocalePreference( Locale.getDefault() );
        }

        return messages_.getString( key );

    }

    /** Sets the user language and country preference so that a ResourceBundle
     * can be loaded to assist the getText() function.
     *
     * @param userLocale Locale object containing the desired language and
     * country.  A properties file is needed to support the choice.
     *
     * @return boolean indicating if the preference was valid and could be set.
     *
     */

    public static boolean setLocalePreference( final Locale userLocale ) {

        try {
            messages_ = ResourceBundle.getBundle( propLocation_, userLocale );
            Locale.setDefault( userLocale );
            return true;
        } catch ( NullPointerException ex ) {
            System.out.println( "Invalid Locale, continuing in US English" ); // NOI18N
        } catch ( MissingResourceException ex ) {
            System.out.println( "Missing Language Resource Bundle for " + // NOI18N
                userLocale.getDisplayName() + ", continuing in US English" ); // NOI18N
        }

        return false;

    }

    /** Checks if a Locale preference is supported by this toolkit.
     *
     * @param userLocale Locale to check to see if a bundle exists.
     *
     * @return boolean indicating if this Locale is available.
     *
     */

    public static boolean checkLocaleAvailable( final Locale userLocale ) {

        try {
            ResourceBundle.getBundle( propLocation_, userLocale );
            return true;
        } catch ( Exception ex ) {
            return false;
        }

    }

    /** Retrieves the internationalized version of the ERROR prefix used for
     * exception and log messages.
     *
     * @return String containing the error text with a colon and space after.
     *
     */

    public static String generalErrorPrefix() {
        return getText( "general_error_caps" ) + ": "; // NOI18N
    }

    /** Retrieves the internationalized version of the Warning prefix used for
     * exception and log messages.
     *
     * @return String containing the error text with a colon and space after.
     *
     */

    public static String generalWarningPrefix() {
        return getText( "general_warning" ) + ": "; // NOI18N
    }

    /** Method to format and "beautify" the XML text from a provided DOM
     * NodeList object.
     *
     * This function passes the nodes through the DOM Transformer using a
     * stylesheet embedded in this package to format the output.
     *
     * @param nodes NodeList containing XML nodes to format into ASCII text.
     *
     * @return String containing the text.
     *
     * @throws XTCEDatabaseException thrown in the event that the Transformer
     * cannot convert the NodeList to XML text.
     *
     */

    public static String xmlPrettyPrint( final NodeList nodes ) throws XTCEDatabaseException {

        InputStream stream = ClassLoader.getSystemResourceAsStream(
            "org/xtce/toolkit/prettyprint.xsl" ); // NOI18N

        try {

            StringBuilder resultsText = new StringBuilder();

            Transformer transformer =
                TransformerFactory.newInstance()
                                  .newTransformer( new StreamSource( stream ) );
            transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION,
                                           "yes" ); // NOI18N

            for ( int iii = 0; iii < nodes.getLength(); ++iii ) {
                Node item = nodes.item( iii );
                switch ( item.getNodeType() ) {
                    case Node.TEXT_NODE:
                        resultsText.append( item.getTextContent() );
                        break;
                    case Node.ATTRIBUTE_NODE:
                        resultsText.append( item.getNodeValue() );
                        break;
                    default:
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        Source source = new DOMSource( nodes.item( iii ) );
                        Result target = new StreamResult( out );
                        transformer.transform( source, target );
                        resultsText.append( out.toString() );
                }
                resultsText.append( System.getProperty( "line.separator" ) ); // NOI18N
            }

            stream.close();

            return resultsText.toString();

        } catch ( Exception ex ) {
            throw new XTCEDatabaseException( ex );
        }

    }

    /** Method to format and "beautify" the XML text from a provided String
     * object containing XML ASCII text.
     *
     * This function passes the nodes through the DOM Transformer using a
     * stylesheet embedded in this package to format the output.
     *
     * @param xmlText String containing XML text.
     *
     * @return String containing the text passed through the transformer.
     *
     * @throws XTCEDatabaseException thrown in the event that the Transformer
     * cannot convert the convert the ASCII text.
     *
     */

    public static String xmlPrettyPrint( final String xmlText ) throws XTCEDatabaseException {

        InputStream stream = ClassLoader.getSystemResourceAsStream(
            "org/xtce/toolkit/prettyprint.xsl" ); // NOI18N

        try {

            Transformer transformer =
                TransformerFactory.newInstance()
                                  .newTransformer( new StreamSource( stream ) );
            transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION,
                                           "yes" ); // NOI18N

            ByteArrayInputStream  inStream  = new ByteArrayInputStream( xmlText.getBytes() );
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Source source = new StreamSource( inStream );
            Result target = new StreamResult( outStream );
            transformer.transform( source, target );

            stream.close();

            return outStream.toString();

        } catch ( Exception ex ) {
            throw new XTCEDatabaseException( ex );
        }

    }

    /** Creates a string with the current memory usage statistics from the
     * Java Virtual Machine.
     *
     * @return String containing a memory usage description suitable for a log
     * line.
     *
     */

    public static String getMemoryUsageStatistics() {

        // this function is basically the contents from a thread on
        // stackoverflow.com with the output tweaked.

        Runtime       runtime         = Runtime.getRuntime();
        StringBuilder sb              = new StringBuilder();
        long          maxMemory       = runtime.maxMemory();
        long          allocatedMemory = runtime.totalMemory();
        long          freeMemory      = runtime.freeMemory();
        long          freeAlloc       = maxMemory - allocatedMemory;

        sb.append( getText( "memstats_title" ) ); // NOI18N
        sb.append( ": " ); // NOI18N
        sb.append( getText( "memstats_freemem" ) ); // NOI18N
        sb.append( " " ); // NOI18N
        sb.append( formatMemoryQuantity( freeMemory ) );
        sb.append( " " ); // NOI18N
        sb.append( getText( "memstats_allocmem" ) ); // NOI18N
        sb.append( " " ); // NOI18N
        sb.append( formatMemoryQuantity( allocatedMemory ) );
        sb.append( " " ); // NOI18N
        sb.append( getText( "memstats_maxmem" ) ); // NOI18N
        sb.append( " " ); // NOI18N
        sb.append( formatMemoryQuantity( maxMemory ) );
        sb.append( " " ); // NOI18N
        sb.append( getText( "memstats_totalfreemem" ) ); // NOI18N
        sb.append( " " ); // NOI18N
        sb.append( formatMemoryQuantity( freeMemory + freeAlloc ) );

        return sb.toString();

    }

    /** This function makes the repeat parameter/argument string, taking into
     * account the internationalization.
     *
     * @param count long containing the current item count.
     *
     * @param total long total number of items.
     *
     * @return String containing the internationalized text.
     *
     */

    public static String makeRepeatString( final long count,
                                           final long total ) {

        if ( repeatText_.isEmpty() == true ) {
            repeatText_ = XTCEFunctions.getText( "general_repeat" ); // NOI18N
            ofText_     = XTCEFunctions.getText( "general_of" ); // NOI18N
        }

        StringBuilder sb = new StringBuilder();
        sb.append( repeatText_ );
        sb.append( " " ); // NOI18N
        sb.append( Long.toString( count ) );
        sb.append( " " ); // NOI18N
        sb.append( ofText_ );
        sb.append( " " ); // NOI18N
        sb.append( Long.toString( total ) );

        return sb.toString();

    }

    /** Private method to format the amount of memory provided in bytes to an
     * output that used K, M, or G depending on the size of the amount.
     *
     * This function considers 1024 of each amount to be the cutoff to jump to
     * a higher unit.  In other words, 1024K will be 1M, 1024M will be 1G.
     *
     * @param amount long containing the number of bytes.
     *
     * @return String in the format of a decimal number followed by the letter
     * K, M, or G depending on the size of the number of bytes provided.
     *
     */

    private static String formatMemoryQuantity( final long amount ) {

        String unit = "K"; // NOI18N
        double quantity = amount / 1024.0;

        if ( quantity > ( 1024 * 1024 ) ) {
            unit = "G"; // NOI18N
            quantity /= ( 1024.0 * 1024.0 );
        } else if ( quantity > 1024 ) {
            unit = "M"; // NOI18N
            quantity /= 1024.0;
        }

        NumberFormat format = NumberFormat.getInstance();

        return format.format( quantity ) + unit;

    }

    /** Private method to ensure that the default XTCEAbsoluteTimeType handlers
     * are registered before searching for one.
     *
     */

    private static void ensureDefaultRegistration() {

        // do not add the TAI variant of the CCSDS CUC yet because it is not
        // finished or tested.

        if ( absTimeHandlersInitialized_ == false ) {
            absTimeHandlers_.add( new XTCEPosixTimeHandler() );
            absTimeHandlers_.add( new XTCECcsdsCucTimeHandler( "yyyy-MM-dd HH:mm:ss.SSS", // NOI18N
                                                               "GMT", // NOI18N
                                                               "1980-01-06", // NOI18N
                                                               4,
                                                               3 ) );
            absTimeHandlersInitialized_ = true;
        }

    }

    private static ResourceBundle messages_ = null;

    private static final String propLocation_ =
        "org.xtce.toolkit.MessagesBundle"; // NOI18N

    private static boolean absTimeHandlersInitialized_ = false;

    private static final List<XTCEAbsoluteTimeType> absTimeHandlers_ =
        new ArrayList<>();

    private static String repeatText_ = "";
    private static String ofText_     = "";

}
