/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.omg.space.xtce.toolkit;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import org.omg.space.xtce.database.ArgumentTypeSetType.FloatArgumentType;
import org.omg.space.xtce.database.ArgumentTypeSetType.IntegerArgumentType;
import org.omg.space.xtce.database.BooleanDataType;
import org.omg.space.xtce.database.CalibratorType;
import org.omg.space.xtce.database.CalibratorType.MathOperationCalibrator;
import org.omg.space.xtce.database.CalibratorType.SplineCalibrator;
import org.omg.space.xtce.database.EnumeratedDataType;
import org.omg.space.xtce.database.FloatDataType;
import org.omg.space.xtce.database.IntegerDataType;
import org.omg.space.xtce.database.NameDescriptionType;
import org.omg.space.xtce.database.ParameterTypeSetType.BooleanParameterType;
import org.omg.space.xtce.database.ParameterTypeSetType.EnumeratedParameterType;
import org.omg.space.xtce.database.ParameterTypeSetType.FloatParameterType;
import org.omg.space.xtce.database.ParameterTypeSetType.IntegerParameterType;
import org.omg.space.xtce.database.PolynomialType;
import org.omg.space.xtce.database.ValueEnumerationType;

/** This class captures the attributes needed to encode or decode a raw value
 * to or from an engineering value and provides public methods to do these
 * tasks from the caller's application.
 *
 * The encode method is for converting an EU value provided to a raw value.
 * The decode method does the opposite, which converts a raw value back to an
 * EU value.  Telemetry typically uses decode on the ground and Telecommanding
 * typically uses encode on the ground, with the reverse of both happening on
 * the satellite side.
 *
 * The implementation of this class might seem a little strange or less than
 * efficient because it is extended by platform specific classes for other use
 * cases that overload the population of the attributes such that it can be
 * used for specific cots products.  Those extended implementations are not
 * included in this toolkit.
 *
 * @author b1053583
 *
 */

public class XTCEItemValue {

    /** Constructor
     *
     * @param item XTCETypedObject containing the Parameter or Argument
     * that is being encoded/decoded.
     *
     */

    public XTCEItemValue( XTCETypedObject item ) {

        // first gather the general attributes that are common
        itemName_    = item.getName();
        euTypeName_  = item.getEngineeringType();
        rawTypeName_ = item.getRawType();
        rawBitOrder_ = item.getRawBitOrder();

        // gather the raw encoding size, which may not be appropriate
        try {
            rawSizeInBits_ = Integer.parseInt( item.getRawSizeInBits() );
            validObject_   = true;
        } catch ( NumberFormatException ex ) {
            warnings_.add( "Cannot encode/decode item " +
                           itemName_ +
                           " without a numeric raw size in bits.  Valud provided: " +
                           rawSizeInBits_ );
            validObject_ = false;
        }

        // gather the Type Reference, for which we cannot proceed further
        // unless exists
        NameDescriptionType typeObj = item.getTypeReference();
        if ( typeObj == null ) {
            warnings_.add( "No type defined for " + itemName_ );
            validObject_ = false;
            return;
        }

        // gather the extended attributes that are needed for specific types
        setValidRangeAttributes( typeObj );
        setEnumerationList( item );
        setDefaultCalibrator( item );

    }

    /** Retrieves the item name of the Parameter or Argument that is the basis
     * for this item value encode()/decode() object.
     *
     * @return String containing the name used when this object was
     * constructed.
     *
     */

    public String getItemName() {
        return itemName_;
    }

    /** Retrieves the validity flag from this item value encode/decode.
     *
     * @return boolean indicating if the encode/decode can be performed.  If
     * this is false, the encode/decode functions will throw an exception.
     *
     */

    public boolean isValid() {
        return validObject_;
    }

    /** Retrieve the list of warnings that have accumulated since this object
     * was created or since the caller last cleared the warnings list.
     *
     * @see #clearWarnings()
     *
     * @return ArrayList of String containing the warning messages.
     *
     */

    public ArrayList<String> getWarnings() {
        return warnings_;
    }

    /** Clears the list of warning messages for this object, which can be
     * useful if multiple raw or engineering values need to be generated, so
     * that the list does not include previous issues.
     *
     */

    public void clearWarnings() {
        warnings_ = new ArrayList<String>();
    }

    public String decode( BitSet rawValue ) {
        return "";
    }

    public BitSet encode( String euValue ) {

        BigInteger calNumericValue   = null;
        BigInteger uncalNumericValue = null;
        BitSet     rawValue          = new BitSet( rawSizeInBits_ );

        if ( euTypeName_.equals( "BOOLEAN" ) == true ) {
            calNumericValue   = numberFromBoolean( euValue );
            uncalNumericValue = encodeNumber( calNumericValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "ENUMERATED" ) == true ) {
            calNumericValue   = numberFromEnumeration( euValue );
            uncalNumericValue = encodeNumber( calNumericValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "STRING" ) == true ) {
            uncalNumericValue = encodeString( euValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "BINARY" ) == true ) {
            uncalNumericValue = encodeString( euValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.startsWith( "FLOAT" ) == true ) {
            uncalNumericValue = encodeString( euValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "UNSIGNED" ) == true ) {
            uncalNumericValue = encodeString( euValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "SIGNED" ) == true ) {
            // TODO Add SIGNED Type
        } else if ( euTypeName_.equals( "TIME" ) == true ) {
            // TODO Add TIME Type
            warnings_.add( "Absolute Time Type Not Yet Supported for " +
                           itemName_ );
        } else if ( euTypeName_.equals( "DURATION" ) == true ) {
            // TODO Add DURATION Type
            warnings_.add( "Relative Time Type Not Yet Supported for " +
                           itemName_ );
        } else {
            warnings_.add( "AGGREGATE and ARRAY types for item " +
                           itemName_ +
                           " cannot directly be encoded." + 
                           "  Use their children instead" );
        }

        return rawValue;

    }

    public BitSet encode( long euValue ) {

        BigInteger calNumericValue   = null;
        BigInteger uncalNumericValue = null;
        BitSet     rawValue          = new BitSet( rawSizeInBits_ );
        if ( euTypeName_.equals( "BOOLEAN" ) == true ) {
            calNumericValue   = numberFromBoolean( Long.toString( euValue ) );
            uncalNumericValue = encodeNumber( calNumericValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "ENUMERATED" ) == true ) {
            calNumericValue   = numberFromEnumeration( Long.toString( euValue ) );
            uncalNumericValue = encodeNumber( calNumericValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "STRING" ) == true ) {
            uncalNumericValue = encodeNumber( BigInteger.valueOf( euValue ) );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "BINARY" ) == true ) {
            uncalNumericValue = encodeNumber( BigInteger.valueOf( euValue ) );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.startsWith( "FLOAT" ) == true ) {
            uncalNumericValue = encodeNumber( BigInteger.valueOf( euValue ) );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "UNSIGNED" ) == true ) {
            uncalNumericValue = encodeNumber( BigInteger.valueOf( euValue ) );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "SIGNED" ) == true ) {
            // TODO Add SIGNED Type
        } else if ( euTypeName_.equals( "TIME" ) == true ) {
            // TODO Add TIME Type
            warnings_.add( "Absolute Time Type Not Yet Supported for " +
                           itemName_ );
        } else if ( euTypeName_.equals( "DURATION" ) == true ) {
            // TODO Add DURATION Type
            warnings_.add( "Relative Time Type Not Yet Supported for " +
                           itemName_ );
        } else {
            warnings_.add( "AGGREGATE and ARRAY types for item " +
                           itemName_ +
                           " cannot directly be encoded." + 
                           "  Use their children instead" );
        }

        return rawValue;

    }

    public BitSet encode( double euValue ) {

        BigInteger calNumericValue   = null;
        BigInteger uncalNumericValue = null;
        BitSet     rawValue          = new BitSet( rawSizeInBits_ );
        if ( euTypeName_.equals( "BOOLEAN" ) == true ) {
            long numericValue = Double.valueOf( euValue ).longValue();
            calNumericValue   = numberFromBoolean( Long.toString( numericValue ) );
            uncalNumericValue = encodeNumber( calNumericValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "ENUMERATED" ) == true ) {
            calNumericValue   = numberFromEnumeration( Double.toString( euValue ) );
            uncalNumericValue = encodeNumber( calNumericValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "STRING" ) == true ) {
            uncalNumericValue = encodeDecimal( BigDecimal.valueOf( euValue ) );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "BINARY" ) == true ) {
            warnings_.add( itemName_ +
                           " Invalid EU Binary value of '" +
                           Double.toString( euValue ) +
                           "'" );
            rawValue = new BitSet( rawSizeInBits_ );
        } else if ( euTypeName_.startsWith( "FLOAT" ) == true ) {
            uncalNumericValue = encodeDecimal( BigDecimal.valueOf( euValue ) );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "UNSIGNED" ) == true ) {
            if ( ( euValue % 1 ) != 0 ) {
                warnings_.add( itemName_ +
                               " Invalid EU unsigned integer value of '" +
                               Double.toString( euValue ) +
                               "'" );
                rawValue = new BitSet( rawSizeInBits_ );
                return rawValue;
            }
            long number = Double.valueOf( euValue ).longValue();
            uncalNumericValue = encodeNumber( BigInteger.valueOf( number ) );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "SIGNED" ) == true ) {
            // TODO Add SIGNED Type
        } else if ( euTypeName_.equals( "TIME" ) == true ) {
            // TODO Add TIME Type
            warnings_.add( "Absolute Time Type Not Yet Supported for " +
                           itemName_ );
        } else if ( euTypeName_.equals( "DURATION" ) == true ) {
            // TODO Add DURATION Type
            warnings_.add( "Relative Time Type Not Yet Supported for " +
                           itemName_ );
        } else {
            warnings_.add( "AGGREGATE and ARRAY types for item " +
                           itemName_ +
                           " cannot directly be encoded." + 
                           "  Use their children instead" );
        }

        return rawValue;

    }

    public BitSet encode( float euValue ) {

        BigInteger calNumericValue   = null;
        BigInteger uncalNumericValue = null;
        BitSet     rawValue          = new BitSet( rawSizeInBits_ );
        if ( euTypeName_.equals( "BOOLEAN" ) == true ) {
            long numericValue = Float.valueOf( euValue ).longValue();
            calNumericValue   = numberFromBoolean( Long.toString( numericValue ) );
            uncalNumericValue = encodeNumber( calNumericValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "ENUMERATED" ) == true ) {
            calNumericValue   = numberFromEnumeration( Float.toString( euValue ) );
            uncalNumericValue = encodeNumber( calNumericValue );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "STRING" ) == true ) {
            uncalNumericValue = encodeDecimal( BigDecimal.valueOf( euValue ) );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "BINARY" ) == true ) {
            warnings_.add( itemName_ +
                           " Invalid EU Binary value of '" +
                           Double.toString( euValue ) +
                           "'" );
            rawValue = new BitSet( rawSizeInBits_ );
        } else if ( euTypeName_.startsWith( "FLOAT" ) == true ) {
            uncalNumericValue = encodeDecimal( BigDecimal.valueOf( euValue ) );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "UNSIGNED" ) == true ) {
            if ( ( euValue % 1 ) != 0 ) {
                warnings_.add( itemName_ +
                               " Invalid EU unsigned integer value of '" +
                               Float.toString( euValue ) +
                               "'" );
                rawValue = new BitSet( rawSizeInBits_ );
                return rawValue;
            }
            long number = Float.valueOf( euValue ).longValue();
            uncalNumericValue = encodeNumber( BigInteger.valueOf( number ) );
            rawValue          = encodeRawBits( uncalNumericValue );
        } else if ( euTypeName_.equals( "SIGNED" ) == true ) {
            // TODO Add SIGNED Type
        } else if ( euTypeName_.equals( "TIME" ) == true ) {
            // TODO Add TIME Type
            warnings_.add( "Absolute Time Type Not Yet Supported for " +
                           itemName_ );
        } else if ( euTypeName_.equals( "DURATION" ) == true ) {
            // TODO Add DURATION Type
            warnings_.add( "Relative Time Type Not Yet Supported for " +
                           itemName_ );
        } else {
            warnings_.add( "AGGREGATE and ARRAY types for item " +
                           itemName_ +
                           " cannot directly be encoded." + 
                           "  Use their children instead" );
        }

        return rawValue;

    }

    /** Function to resolve a Boolean Engineering Type, which has a flexible
     * set of EU values, into the numeric calibrated value.
     *
     * This function could have been implemented by creating a bogus set of
     * enumerations, where just 0 and 1 are implememnted, using the attributes
     * of @zeroStringValue and @oneStringValue, but it was just as simple to
     * create this and it seemed like less overhead.  A warning message is
     * added to the warnings list in the event that the engineering value does
     * not match and the return value will be BigInteger.ZERO.
     *
     * @param euValue String containing the user provided engineering value for
     * this Boolean type Parameter/Argument.
     *
     * @return BigInteger containing either ZERO or ONE, which are constants
     * for the BigInteger class.
     *
     */

    private BigInteger numberFromBoolean( String euValue ) {

        if ( euValue.equals( booleanZeroString_ ) == true ) {
            return BigInteger.ZERO;
        } else if ( euValue.equals( booleanOneString_ ) == true ) {
            return BigInteger.ONE;
        } else {
            warnings_.add( itemName_ +
                           " Invalid EU Boolean value of " +
                           "'" +
                           euValue +
                           "'" );
        }

        return BigInteger.ZERO;

    }

    /** Function to resolve an Enumerated Engineering Type into the numeric
     * calibrated value.
     *
     * This function searches the labels of the EnumerationList in the XTCE
     * data model to find a match.  It takes into account that some
     * enumerations have ranges, and if there is an overlap, it will end up
     * returning the first match.  Since this function supports the encode()
     * method, enumeration labels that have ranges cannot be exactly
     * determined, so the raw value returned will always be the first valid
     * value for the label, if it is in a range.  A warning will be posted to
     * the warnings list, but it should be considered harmless in this case.
     *
     * @param euValue String containing the enumeration label provided by the
     * user that wishes to encode() this Parameter/Argument into a raw value.
     *
     * @return BigInteger containing the raw value associated with the provided
     * enumeration label, or BigInteger.ZERO if the label was not located.  A
     * warning will be posted to the warnings list if this occurs.
     *
     */

    private BigInteger numberFromEnumeration( String euValue ) {

        for ( ValueEnumerationType enumItem : enums_ ) {

            if ( enumItem.getLabel().equals( euValue ) == false ) {
                continue;
            }

            if ( enumItem.getMaxValue() != null ) {
                warnings_.add( "Enumeration label '" +
                               enumItem.getLabel() +
                               "' for " +
                               itemName_ +
                               " has values '" +
                               enumItem.getValue().toString() +
                               "' through '" +
                               enumItem.getMaxValue().toString() +
                               "', using lowest possible value" );
            }

            return enumItem.getValue();

        }

        warnings_.add( itemName_ +
                       " Invalid EU Enumeration value of '" +
                       euValue +
                       "'" );

        return BigInteger.ZERO;

    }

    private String enumerationFromNumber( BigInteger euValue ) {

        for ( ValueEnumerationType enumItem : enums_ ) {
            BigInteger value    = enumItem.getValue();
            BigInteger maxValue = value;
            if ( enumItem.getMaxValue() != null ) {
                maxValue = enumItem.getMaxValue();
            }
            if ( ( euValue.compareTo( value )    > -1 ) &&
                 ( euValue.compareTo( maxValue ) < 1  ) ) {
                return enumItem.getLabel();
            }
        }

        warnings_.add( "No enumeration label found for " +
                       itemName_ +
                       " value: " +
                       euValue.toString() );
        return "";

    }

    private BigInteger encodeNumber( BigInteger calValue ) {

        if ( rawTypeName_.equals( "unsigned" ) == true ) {
            BigInteger uncalValue = integerEncodingUncalibrate( calValue );
            if ( isIntegerRawValueReasonable( uncalValue ) == false ) {
                return BigInteger.ZERO;
            }
            return uncalValue;
        } else if ( rawTypeName_.equals( "signMagnitude" ) == true ) {
            // TODO uncalibrate
            // TODO handle BigInteger twosComplement with signMagnitude
            return calValue;
        } else if ( rawTypeName_.equals( "twosComplement" ) == true ) {
            // TODO uncalibrate
            return calValue;
        } else if ( rawTypeName_.equals( "onesComplement" ) == true ) {
            // TODO uncalibrate
            // TODO handle BigInteger twosComplement with onesComplement
            return calValue;
        } else if ( rawTypeName_.equals( "binary" ) == true ) {
            return calValue;
        } else if ( rawTypeName_.equals( "IEEE754_1985" ) == true ) {
            BigDecimal reasCalValue = new BigDecimal( calValue );
            if ( isFloatRawValueReasonable( reasCalValue.doubleValue() ) == false ) {
                reasCalValue = BigDecimal.ZERO;
            }
            if ( rawSizeInBits_ == 32 ) {
                return BigInteger.valueOf( Float.floatToRawIntBits( reasCalValue.floatValue() ) );
            } else if ( rawSizeInBits_ == 64 ) {
                return BigInteger.valueOf( Double.doubleToRawLongBits( reasCalValue.doubleValue() ) );
            } else if ( rawSizeInBits_ == 128 ) {
                warnings_.add( "Unsupported encoding type for " +
                               itemName_ +
                               " Encoding: " +
                               rawTypeName_ );
            }
        } else if ( rawTypeName_.equals( "MILSTD_1750A" ) == true ) {
            warnings_.add( "Unsupported encoding type for " +
                           itemName_ +
                           " Encoding: " +
                           rawTypeName_ );
        } else if ( rawTypeName_.equals( "UTF-8" ) == true ) {
            String chars = calValue.toString();
            BigInteger retValue = new BigInteger( chars.getBytes( StandardCharsets.UTF_8 ) );
            return encodeUtfString( retValue );
        } else if ( rawTypeName_.equals( "UTF-16" ) == true ) {
            String chars = calValue.toString();
            BigInteger retValue = new BigInteger( chars.getBytes( StandardCharsets.UTF_16 ) );
            if ( retValue.equals( BigInteger.ZERO ) == true ) {
                retValue = new BigInteger( utf16_ );
            }
            return encodeUtfString( retValue );
        } else {
            warnings_.add( "Unrecognized encoding type for " +
                           itemName_ +
                           " Encoding: " +
                           rawTypeName_ );
        }

        return BigInteger.ZERO;

    }

    private BigInteger encodeDecimal( BigDecimal calValue ) {

        if ( rawTypeName_.equals( "unsigned" ) == true ) {
            BigInteger uncalValue = integerEncodingUncalibrate( calValue );
            if ( isIntegerRawValueReasonable( uncalValue ) == false ) {
                return BigInteger.ZERO;
            }
            return uncalValue;
        } else if ( rawTypeName_.equals( "signMagnitude" ) == true ) {
            
        } else if ( rawTypeName_.equals( "twosComplement" ) == true ) {
            
        } else if ( rawTypeName_.equals( "onesComplement" ) == true ) {
            
        } else if ( rawTypeName_.equals( "binary" ) == true ) {
            warnings_.add( "Unsupported encoding type for " +
                           itemName_ +
                           " Encoding: " +
                           rawTypeName_ );
        } else if ( rawTypeName_.equals( "IEEE754_1985" ) == true ) {
            BigDecimal reasCalValue = calValue;
            if ( isFloatRawValueReasonable( reasCalValue.doubleValue() ) == false ) {
                reasCalValue = BigDecimal.ZERO;
            }
            if ( rawSizeInBits_ == 32 ) {
                return BigInteger.valueOf( Float.floatToRawIntBits( reasCalValue.floatValue() ) );
            } else if ( rawSizeInBits_ == 64 ) {
                return BigInteger.valueOf( Double.doubleToRawLongBits( reasCalValue.doubleValue() ) );
            } else if ( rawSizeInBits_ == 128 ) {
                warnings_.add( "Unsupported encoding type for " +
                               itemName_ +
                               " Encoding: " +
                               rawTypeName_ );
            }
        } else if ( rawTypeName_.equals( "MILSTD_1750A" ) == true ) {
            warnings_.add( "Unsupported encoding type for " +
                           itemName_ +
                           " Encoding: " +
                           rawTypeName_ );
        } else if ( rawTypeName_.equals( "UTF-8" ) == true ) {
            String chars = calValue.toString();
            BigInteger retValue = new BigInteger( chars.getBytes( StandardCharsets.UTF_8 ) );
            return encodeUtfString( retValue );
        } else if ( rawTypeName_.equals( "UTF-16" ) == true ) {
            String chars = calValue.toString();
            BigInteger retValue = new BigInteger( chars.getBytes( StandardCharsets.UTF_16 ) );
            if ( retValue.equals( BigInteger.ZERO ) == true ) {
                retValue = new BigInteger( utf16_ );
            }
            return encodeUtfString( retValue );
        } else {
            warnings_.add( "Unrecognized encoding type for " +
                           itemName_ +
                           " Encoding: " +
                           rawTypeName_ );
        }

        return BigInteger.ZERO;

    }

    private BigInteger encodeString( String calValue ) {

        try {

            if ( rawTypeName_.equals( "unsigned" ) == true ) {
                String lowerCalValue = calValue.toLowerCase();
                if ( lowerCalValue.startsWith( "0x" ) == true ) {
                    BigInteger retValue = new BigInteger( lowerCalValue.replaceFirst( "0x", "" ), 16 );
                    BigInteger uncalValue = integerEncodingUncalibrate( retValue );
                    if ( isIntegerRawValueReasonable( uncalValue ) == false ) {
                        return BigInteger.ZERO;
                    }
                    return uncalValue;
                } else {
                    BigDecimal retValue = new BigDecimal( lowerCalValue );
                    BigInteger uncalValue = integerEncodingUncalibrate( retValue );
                    if ( isIntegerRawValueReasonable( uncalValue ) == false ) {
                        return BigInteger.ZERO;
                    }
                    return uncalValue;
                }
            } else if ( rawTypeName_.equals( "signMagnitude" ) == true ) {
            
            } else if ( rawTypeName_.equals( "twosComplement" ) == true ) {
            
            } else if ( rawTypeName_.equals( "onesComplement" ) == true ) {
            
            } else if ( rawTypeName_.equals( "binary" ) == true ) {
                String lowerCalValue = calValue.toLowerCase();
                if ( lowerCalValue.startsWith( "0x" ) == true ) {
                    return new BigInteger( lowerCalValue.replaceFirst( "0x", "" ), 16 );
                } else {
                    return new BigInteger( lowerCalValue );
                }
            } else if ( rawTypeName_.equals( "IEEE754_1985" ) == true ) {
                String reasCalValue = calValue;
                if ( isFloatRawValueReasonable( Double.valueOf( calValue ) ) == false ) {
                    reasCalValue = "0.0";
                }
                if ( rawSizeInBits_ == 32 ) {
                    return BigInteger.valueOf( Float.floatToRawIntBits( Float.parseFloat( reasCalValue ) ) );
                } else if ( rawSizeInBits_ == 64 ) {
                    return BigInteger.valueOf( Double.doubleToRawLongBits( Double.parseDouble( reasCalValue ) ) );
                } else if ( rawSizeInBits_ == 128 ) {
                    warnings_.add( "Unsupported encoding type for " +
                                   itemName_ +
                                   " Encoding: " +
                                   rawTypeName_ );
                }
            } else if ( rawTypeName_.equals( "MILSTD_1750A" ) == true ) {
                warnings_.add( "Unsupported encoding type for " +
                               itemName_ +
                               " Encoding: " +
                               rawTypeName_ );
            } else if ( rawTypeName_.equals( "UTF-8" ) == true ) {
                BigInteger retValue = BigInteger.ZERO;
                if ( calValue.isEmpty() == false ) {
                    retValue = new BigInteger( calValue.getBytes( StandardCharsets.UTF_8 ) );
                }
                return encodeUtfString( retValue );
            } else if ( rawTypeName_.equals( "UTF-16" ) == true ) {
                BigInteger retValue = BigInteger.ZERO;
                if ( calValue.isEmpty() == false ) {
                    retValue = new BigInteger( calValue.getBytes( StandardCharsets.UTF_16 ) );
                }
                if ( retValue.equals( BigInteger.ZERO ) == true ) {
                    retValue = new BigInteger( utf16_ );
                }
                return encodeUtfString( retValue );
            } else {
                warnings_.add( "Unrecognized encoding type for " +
                               itemName_ +
                               " Encoding: " +
                               rawTypeName_ );
            }

        } catch ( NumberFormatException ex ) {
            warnings_.add( itemName_ +
                           " Invalid String value for encoding " +
                           rawTypeName_ +
                           " of '" +
                           calValue +
                           "'" );
        }

        return BigInteger.ZERO;

    }

    private BigInteger encodeUtfString( BigInteger retValue ) {

        long bitLength = retValue.toByteArray().length * 8;
        if ( bitLength > rawSizeInBits_ ) {
            warnings_.add( "String length for encoding " +
                           rawTypeName_ +
                           " item " +
                           itemName_ +
                           " is " +
                           Long.toString( bitLength ) +
                           " which exceeds fixed length size of " +
                           Long.toString( rawSizeInBits_) );
        }
        if ( retValue.equals( BigInteger.ZERO ) == true ) {
            return retValue;
        }
        while ( bitLength < rawSizeInBits_ ) {
            retValue = retValue.shiftLeft( 8 );
            bitLength = retValue.toByteArray().length * 8;
            //retValue = retValue.add(  ); for termination char
        }
        return retValue;

    }

    /** Function to create the raw binary bits for populating a container
     * binary based on the encoded value of this named and typed item from the
     * XTCE data model.
     *
     * The caller provides the uncalibrated value in the form of a BigInteger
     * and this function walks through the bits of that value, ensuring to
     * use all the bits that are in the raw encoded size.  It sets the BitSet
     * such that bit 0 of the BitSet is the least significant bit and the
     * highest (rawSizeInBits_) is the most significant bit.  If the order is
     * reversed by the encoding attribute @bitOrder, then the reverse happens.
     *
     * @param uncalValue BigInteger containing the value to encode into a raw
     * BitSet for inclusion into a container object, either Telemetry or
     * Telecommand.
     *
     * @return BitSet suitable for inclusion into a Telemetry or Telecommand
     * container by simply walking the length and placing the bits into the
     * container.  All ordering has already been handled.
     *
     */

    private BitSet encodeRawBits( BigInteger uncalValue ) {

        // TODO Handle Byte order element ByteOrderList in the encoding

        BitSet rawBits = new BitSet( rawSizeInBits_ );
        if ( rawBitOrder_.equals( "mostSignificantBitFirst" ) == true ) {
            for ( int iii = rawSizeInBits_ - 1; iii >= 0; --iii ) {
                rawBits.set( iii, uncalValue.testBit( iii ) );
            }
        } else {
            for ( int iii = rawSizeInBits_ - 1; iii >= 0; --iii ) {
                rawBits.set( rawSizeInBits_ - iii - 1, uncalValue.testBit( iii ) );
            }
        }

        return rawBits;

    }

    /** Sets ranging information for item value validity.
     *
     * Ranging information is optional and will be ignored if it has not been
     * setup by this method.  This is generally invoked from the function
     * setValidRangeAttributes().  The reason it is separated is so that some
     * platform specific versions of this class can be generated, although they
     * will not be a part of this toolkit.
     *
     * @param lowValidValue String containing the minimum valid value.  See the
     * other arguments for modifiers of this.
     *
     * @param highValidValue String containing the maximum valid value.  See
     * the other arguments for modifiers of this.
     *
     * @param lowValidValueInclusive boolean indicating if the minimum valid
     * value should be inclusive (if true) or exclusive (if false).  This
     * changes the behavior to &gt; or equal to in the case of true and just
     * &gt; in the event of false.
     *
     * @param highValidValueInclusive boolean indicating if the minimum valid
     * value should be inclusive (if true) or exclusive (if false).  This
     * changes the behavior to &lt; or equal to in the case of true and just
     * &lt; in the event of false.
     *
     * @param lowAppliesToCalibrated boolean indicating of the valid values are
     * to be applied to the raw/uncalibrated or EU/calibrated value when doing
     * the encode/decode operations.
     *
     * @param highAppliesToCalibrated boolean indicating of the valid values are
     * to be applied to the raw/uncalibrated or EU/calibrated value when doing
     * the encode/decode operations.
     *
     */

    protected void setValidRange( String  lowValidValue,
                                  String  highValidValue,
                                  boolean lowValidValueInclusive,
                                  boolean highValidValueInclusive,
                                  boolean lowAppliesToCalibrated,
                                  boolean highAppliesToCalibrated ) {

        lowValidValue_           = lowValidValue;
        highValidValue_          = highValidValue;
        lowValidValueInclusive_  = lowValidValueInclusive;
        highValidValueInclusive_ = highValidValueInclusive;
        lowAppliesToCalibrated_  = lowAppliesToCalibrated;
        highAppliesToCalibrated_ = highAppliesToCalibrated;
        validRangeExists_        = true;

    }

    /** Sets the Valid Range attributes of this object based on a general
     * type specification from the XTCE data model, which all inherit from the
     * NameDescriptionType.
     *
     * @param typeObj NameDescriptionType containing the type information for
     * this named and typed Parameter/Argument.
     *
     */

    private void setValidRangeAttributes( NameDescriptionType typeObj ) {

        if ( typeObj.getClass() == BooleanParameterType.class ) {
            setValidRange( "0", "1", true, true, false, false );
            booleanZeroString_ = ((BooleanParameterType)typeObj).getZeroStringValue();
            booleanOneString_  = ((BooleanParameterType)typeObj).getOneStringValue();
        } else if ( typeObj.getClass() == BooleanDataType.class ) {
            booleanZeroString_ = ((BooleanDataType)typeObj).getZeroStringValue();
            booleanOneString_  = ((BooleanDataType)typeObj).getOneStringValue();
        } else if ( typeObj.getClass() == IntegerParameterType.class ) {
            IntegerDataType.ValidRange rangeElement = ((IntegerParameterType)typeObj).getValidRange();
            if ( rangeElement != null ) {
                setValidRange( rangeElement.getMinInclusive(),
                               rangeElement.getMaxInclusive(),
                               true,
                               true,
                               rangeElement.isValidRangeAppliesToCalibrated(),
                               rangeElement.isValidRangeAppliesToCalibrated() );
            }
        } else if ( typeObj.getClass() == IntegerArgumentType.class ) {
            IntegerDataType.ValidRange rangeElement = ((IntegerArgumentType)typeObj).getValidRange();
            if ( rangeElement != null ) {
                setValidRange( rangeElement.getMinInclusive(),
                               rangeElement.getMaxInclusive(),
                               true,
                               true,
                               rangeElement.isValidRangeAppliesToCalibrated(),
                               rangeElement.isValidRangeAppliesToCalibrated() );
            }
        } else if ( typeObj.getClass() == FloatParameterType.class ) {
            FloatDataType.ValidRange rangeElement = ((FloatParameterType)typeObj).getValidRange();
            if ( rangeElement != null ) {
                setFloatValidRange( rangeElement );
            }
        } else if ( typeObj.getClass() == FloatArgumentType.class ) {
            FloatDataType.ValidRange rangeElement = ((FloatArgumentType)typeObj).getValidRange();
            if ( rangeElement != null ) {
                setFloatValidRange( rangeElement );
            }
        }

    }

    /** Sets the Valid Range attributes of this object based on those that are
     * provided with a Floating Point Encoding value.
     *
     * @param rangeElement A FloatDataType.ValidRange element from the XTCE
     * data model.
     *
     */

    private void setFloatValidRange( FloatDataType.ValidRange rangeElement ) {

        // first evaluate the minimum value, if it exists, and determine if
        // it is inclusive or exclusive.  We leave it an empty string if it is
        // not applicable to this item.
        boolean minInclusive = true;
        String  minValue     = "";
        Double  min          = rangeElement.getMinInclusive();
        if ( min == null ) {
            min = rangeElement.getMinExclusive();
            if ( min != null ) {
                minInclusive = false;
            }
        }
        if ( min != null ) {
            minValue = min.toString();
        }

        // next evaluated the maximum value, if it exists, and determine if
        // it is inclusive or exclusive.  We leave it an empty string if it is
        // not applicable to this item.
        boolean maxInclusive = true;
        String  maxValue     = "";
        Double  max          = rangeElement.getMaxInclusive();
        if ( max == null ) {
            max = rangeElement.getMinExclusive();
            if ( max != null ) {
                maxInclusive = false;
            }
        }
        if ( max != null ) {
            maxValue = max.toString();
        }

        // set the valid range information from the more generic setter method
        setValidRange( minValue,
                       maxValue,
                       minInclusive,
                       maxInclusive,
                       rangeElement.isValidRangeAppliesToCalibrated(),
                       rangeElement.isValidRangeAppliesToCalibrated() );

    }

    /** Convert the BitSet object from the encode() method over to a hex byte
     * string, ordered from the most significant byte to the least significant
     * byte.
     *
     * The most significant bits are padded with zero in this case when the
     * raw size is not on an even 8 bit boundary.  This results in the function
     * never returning a hex string that is less than 2 characters for each
     * byte, with a minimum of 1 byte.  A "0x" is prepended.  If the exact
     * raw size is needed, call the rawSizeInBits() method to determine which
     * of the uppermost bits are extraneous.
     *
     * @param bits BitSet returned from the encode() function.
     *
     * @return String containing the hex of the raw value to be encoded,
     * subject to the explanation above associated with this function.
     *
     */

    public String bitSetToHex( BitSet bits ) {

        int bitCount = rawSizeInBits_;
        if ( ( rawSizeInBits_ % 8 ) != 0 ) {
            bitCount += 8 - ( rawSizeInBits_ % 8 );
        }
        int byteCount = bitCount / 8;
        StringBuilder sb = new StringBuilder( "0x" );
        byte[] bytes = bits.toByteArray();
        for ( int iii = byteCount - 1; iii >= 0; --iii ) {
            if ( iii < bytes.length ) {
                sb.append( String.format( "%02x", bytes[iii] ) );
            } else {
                sb.append( "00" );
            }
        }
        return sb.toString();

    }

    /** Convert the BitSet object from the encode() method over to a binary
     * string of zeros and ones, ordered from most significant bit to least
     * significant bit.
     *
     * @param bits BitSet containing the bits returned from the encode()
     * function.
     *
     * @return String containing the binary zeros and ones, with all bit
     * positions populated for the entire length of the raw size.  The number
     * of bits will always exactly equal the raw encoding size, with the upper
     * possible unused bits padded with zeros.
     *
     */

    public String bitSetToBinary( BitSet bits ) {

        StringBuilder sb = new StringBuilder();
        for ( int iii = rawSizeInBits_ - 1; iii >= 0; --iii ) {
            sb.append( bits.get( iii ) == true ? "1" : "0" );
        }
        return sb.toString();

    }

    private BigDecimal floatEncodingUncalibrate( BigDecimal calValue ) {

        if ( defCal_ == null ) {
            return calValue;
        }

        return calValue;

    }

    private BigDecimal floatEncodingUncalibrate( BigInteger calValue ) {

        if ( defCal_ == null ) {
            return new BigDecimal( calValue );
        }

        return new BigDecimal( calValue );

    }

    private BigInteger integerEncodingUncalibrate( BigDecimal calValue ) {

        if ( defCal_ == null ) {
            long value = Math.round( calValue.doubleValue() );
            return new BigInteger( Long.toString( value ) );
        }

        PolynomialType polyCal = defCal_.getPolynomialCalibrator();
        if ( polyCal != null ) {
            HashMap<BigInteger, BigDecimal> terms =
                new HashMap<BigInteger, BigDecimal>();
            List<PolynomialType.Term> xtceTerms = polyCal.getTerm();
            long maxExponent = 0;
            for ( PolynomialType.Term term : xtceTerms ) {
                if ( term.getCoefficient() != 0.0 ) {
                    terms.put( term.getExponent(), new BigDecimal( term.getCoefficient() ) );
                    if ( term.getExponent().longValue() > maxExponent ) {
                        maxExponent = term.getExponent().longValue();
                    }
                }
            }
            if ( maxExponent <= 1 ) {
                double value = calValue.doubleValue();
                if ( terms.containsKey( BigInteger.ZERO ) == true ) {
                    value = value - terms.get( BigInteger.ZERO ).doubleValue();
                }
                if ( terms.containsKey( BigInteger.ONE ) == true ) {
                    value = value / terms.get( BigInteger.ONE ).doubleValue();
                }
                return BigInteger.valueOf( Double.valueOf( value ).longValue() );
            } else if ( maxExponent == 2 ) {
                final BigInteger inttwo = new BigInteger( "2" );
                // evaluate b^2 -4ac to determine if roots exist
                double aaa = 0.0;
                double bbb = 0.0;
                double ccc = -1.0 * calValue.doubleValue();
                if ( terms.containsKey( BigInteger.ZERO ) == true ) {
                    ccc += terms.get( BigInteger.ZERO ).doubleValue();
                }
                if ( terms.containsKey( BigInteger.ONE ) == true ) {
                    bbb = terms.get( BigInteger.ONE ).doubleValue();
                }
                if ( terms.containsKey( inttwo ) == true ) {
                    aaa = terms.get( inttwo ).doubleValue();
                }
                double discriminant = Math.pow( bbb, 2 ) - ( 4.0 * aaa * ccc );
                if ( discriminant < 0 ) {
                    warnings_.add( "Polynomial Calibrator for " +
                                   itemName_ +
                                   " has no real roots for EU value " +
                                   calValue.toString() );
                    return BigInteger.ZERO;
                }
                double posroot = Math.sqrt( discriminant );
                double root1   = ( ( bbb * -1.0 ) - posroot ) / ( 2.0 * aaa );
                double root2   = ( ( bbb * -1.0 ) + posroot ) / ( 2.0 * aaa );
                System.out.println( "Root1 = " + Double.toString( root1 ) + " Root2 = " + Double.toString( root2 ) );
                double bestPick = findBestRoot( root1, root2 );
                return BigInteger.valueOf( Math.round( bestPick ) );
            } else {
                warnings_.add( "Polynomial Calibrator for " +
                               itemName_ +
                               " contains exponent power as high as " +
                               Long.toString( maxExponent ) +
                               ".  Not supported by this toolkit." );
                return BigInteger.ZERO;
            }
        }

        SplineCalibrator splineCal = defCal_.getSplineCalibrator();
        if ( splineCal != null ) {
            
        }

        MathOperationCalibrator mathCal = defCal_.getMathOperationCalibrator();
        if ( mathCal != null ) {
            warnings_.add( "MathOperationCalibrator for " +
                           itemName_ +
                           " not supported" );
            return BigInteger.ZERO;
        }

        long value = calValue.longValue();
        return new BigInteger( Long.toString( value ) );

    }

    private BigInteger integerEncodingUncalibrate( BigInteger calValue ) {

        if ( defCal_ == null ) {
            return calValue;
        }

        // TODO customize this version of the function later
        return integerEncodingUncalibrate( new BigDecimal( calValue ) );

    }

    /** Sets the internal enumeration list information in the event that this
     * is an Enumerated Type Parameter/Argument.
     *
     * @param typeObj XTCETypedObject base class for the Type information
     * about this Parameter/Argument.
     *
     */

    private void setEnumerationList( XTCETypedObject item ) {
        enums_ = item.getEnumerations();
    }

    /** Sets the internal default calibrator list information in the event that
     * this Parameter/Argument has an encoding type that supports calibration.
     *
     * The types that support Calibrators are Integer and Float data encoding.
     *
     */

    private void setDefaultCalibrator( XTCETypedObject item ) {
        defCal_ = item.getDefaultCalibrator();
    }

    /** Check for reasonableness of the raw value to encode to an integer type
     * encoding.
     *
     * This method first figures out the range of possible values based on the
     * size in bits and the signed state.  It then checks if the value should
     * be restricted further by a possibly present ValidRange element.  It
     * applies those limits if they are intended for the raw value.
     *
     * @param rawValue BigInteger containing the raw value that will be encoded
     * if it turns out to be reasonable.
     *
     * @return boolean indicating if this function thinks the value is
     * reasonable to fit in the allowable size and range.
     *
     */

    private boolean isIntegerRawValueReasonable( BigInteger rawValue ) {

        // first find the general size applicable to the bit length
        boolean minInclusive = true;
        boolean maxInclusive = true;
        long    minValue     = 0;
        long    maxValue     = (long)Math.pow( 2, rawSizeInBits_ ) - 1;

        // if it is signed, then correct that range
        if ( ( rawTypeName_.equals( "signMagnitude" ) == true ) ||
             ( rawTypeName_.equals( "onesComplement" ) == true ) ||
             ( rawTypeName_.equals( "twosComplement" ) == true ) ) {
            minValue = -1 * (long)Math.pow( 2, ( rawSizeInBits_ - 1 ) );
            maxValue = (long)Math.pow( 2, ( rawSizeInBits_ - 1 ) ) - 1;
        }

        // if a ValidRange element exists then we want to filter the min and
        // max through that, but only if it applies to RAW
        if ( validRangeExists_ == true ) {
            if ( lowAppliesToCalibrated_ == false ) {
                minValue = Math.round( Double.parseDouble( lowValidValue_ ) );
                if ( lowValidValueInclusive_ == false ) {
                    minInclusive = false;
                }
            }
            if ( highAppliesToCalibrated_ == false ) {
                maxValue = Math.round( Double.parseDouble( highValidValue_ ) );
                if ( highValidValueInclusive_ == false ) {
                    maxInclusive = false;
                }
            }
        }

        // check the special negative case first
        if ( rawTypeName_.equals( "unsigned" ) == true ) {
            if ( rawValue.longValue() < 0 ) {
                warnings_.add( "Unsigned value for item " +
                               itemName_ +
                               " is " +
                               rawValue.toString() +
                               " which cannot be negative" );
                return false;
            }
        }

        // check for the boundary conditions with the minimum first
        if ( minInclusive == false ) {
            if ( minValue >= rawValue.longValue() ) {
                warnings_.add( rawTypeName_ +
                               " encoding value for item " +
                               itemName_ +
                               " is " +
                               rawValue.toString() +
                               ", which is less than or equal to the " +
                               "minimum value " +
                               Long.toString( minValue ) );
                return false;
            }
        } else {
            if ( minValue > rawValue.longValue() ) {
                warnings_.add( rawTypeName_ +
                               " encoding value for item " +
                               itemName_ +
                               " is " +
                               rawValue.toString() +
                               ", which is less than the " +
                               "minimum value " +
                               Long.toString( minValue ) );
                return false;
            }
        }

        // check for the boundary conditions with the maximum
        if ( maxInclusive == false ) {
            if ( maxValue <= rawValue.longValue() ) {
                warnings_.add( rawTypeName_ +
                               " encoding value for item " +
                               itemName_ +
                               " is " +
                               rawValue.toString() +
                               ", which is greater than or equal to the " +
                               "maximum value " +
                               Long.toString( maxValue ) );
                return false;
            }
        } else {
            if ( maxValue < rawValue.longValue() ) {
                warnings_.add( rawTypeName_ +
                               " encoding value for item " +
                               itemName_ +
                               " is " +
                               rawValue.toString() +
                               ", which is greater than the " +
                               "maximum value " +
                               Long.toString( maxValue ) );
                return false;
            }
        }

        return true;

    }

    private boolean isFloatRawValueReasonable( double rawValue ) {

        // first find the general size applicable to the bit length
        boolean minInclusive = true;
        boolean maxInclusive = true;
        double  minValue     = -Double.MAX_VALUE;
        double  maxValue     = Double.MAX_VALUE;

        // if a ValidRange element exists then we want to filter the min and
        // max through that, but only if it applies to RAW
        if ( validRangeExists_ == true ) {
            if ( lowAppliesToCalibrated_ == false ) {
                minValue = Math.round( Double.parseDouble( lowValidValue_ ) );
                if ( lowValidValueInclusive_ == false ) {
                    minInclusive = false;
                }
            }
            if ( highAppliesToCalibrated_ == false ) {
                maxValue = Math.round( Double.parseDouble( highValidValue_ ) );
                if ( highValidValueInclusive_ == false ) {
                    maxInclusive = false;
                }
            }
        }

        // check for the boundary conditions with the minimum first
        if ( minInclusive == false ) {
            if ( minValue >= rawValue ) {
                warnings_.add( rawTypeName_ +
                               " encoding value for item " +
                               itemName_ +
                               " is " +
                               Double.toString( rawValue ) +
                               ", which is less than or equal to the " +
                               "minimum value " +
                               Double.toString( minValue ) );
                return false;
            }
        } else {
            if ( minValue > rawValue ) {
                warnings_.add( rawTypeName_ +
                               " encoding value for item " +
                               itemName_ +
                               " is " +
                               Double.toString( rawValue ) +
                               ", which is less than the " +
                               "minimum value " +
                               Double.toString( minValue ) );
                return false;
            }
        }

        // check for the boundary conditions with the maximum
        if ( maxInclusive == false ) {
            if ( maxValue <= rawValue ) {
                warnings_.add( rawTypeName_ +
                               " encoding value for item " +
                               itemName_ +
                               " is " +
                               Double.toString( rawValue ) +
                               ", which is greater than or equal to the " +
                               "maximum value " +
                               Double.toString( maxValue ) );
                return false;
            }
        } else {
            if ( maxValue < rawValue ) {
                warnings_.add( rawTypeName_ +
                               " encoding value for item " +
                               itemName_ +
                               " is " +
                               Double.toString( rawValue ) +
                               ", which is greater than the " +
                               "maximum value " +
                               Double.toString( maxValue ) );
                return false;
            }
        }

        return true;

    }

    private double findBestRoot( double root1, double root2 ) {

        // function is never called if BOTH are invalid since we checked the
        // discriminant earlier.
        if ( ( root1 == Double.NaN               ) ||
             ( root1 == Double.POSITIVE_INFINITY ) ||
             ( root1 == Double.NEGATIVE_INFINITY ) ) {
            root1 = root2;
        }
        if ( ( root2 == Double.NaN               ) ||
             ( root2 == Double.POSITIVE_INFINITY ) ||
             ( root2 == Double.NEGATIVE_INFINITY ) ) {
            root2 = root1;
        }

        if ( rawTypeName_.equals( "unsigned" ) == true ) {
            // TODO worry about inclusive versus exclusive
            long minValue = 0;
            long maxValue = (long)Math.pow( 2, rawSizeInBits_  ) - 1;
            if ( ( validRangeExists_       == true  ) &&
                 ( lowAppliesToCalibrated_ == false ) ) {
                minValue = Math.round( Double.parseDouble( lowValidValue_ ) );
            }
            if ( ( validRangeExists_        == true  ) &&
                 ( highAppliesToCalibrated_ == false ) ) {
                maxValue = Math.round( Double.parseDouble( highValidValue_ ) );
            }
            if ( ( root1 >= minValue ) && ( root1 <= maxValue ) ) {
                return root1;
            } else if ( ( root2 >= minValue ) && ( root2 <= maxValue ) ) {
                return root2;
            } else {
                warnings_.add( "Polynomial Calibrator for " +
                               itemName_ +
                               " contains roots of " +
                               Double.toString( root1 ) +
                               " and " +
                               Double.toString( root2 ) +
                               ", neither of which are in the range of " +
                               Long.toString( minValue ) +
                               " to " +
                               Long.toString( maxValue ) +
                               " for encoding " +
                               rawTypeName_ );
                return 0.0;
            }
        } else if ( ( rawTypeName_.equals( "signMagnitude" ) == true ) ||
                    ( rawTypeName_.equals( "onesComplement" ) == true ) ||
                    ( rawTypeName_.equals( "twosComplement" ) == true ) ) {
            // TODO worry about inclusive versus exclusive
            long minValue = -1 * (long)Math.pow( 2, ( rawSizeInBits_ - 1 ) );
            long maxValue = (long)Math.pow( 2, ( rawSizeInBits_ - 1 ) ) - 1;
            if ( ( validRangeExists_       == true  ) &&
                 ( lowAppliesToCalibrated_ == false ) ) {
                minValue = Math.round( Double.parseDouble( lowValidValue_ ) );
            }
            if ( ( validRangeExists_        == true  ) &&
                 ( highAppliesToCalibrated_ == false ) ) {
                maxValue = Math.round( Double.parseDouble( highValidValue_ ) );
            }
            if ( ( root1 >= minValue ) && ( root1 <= maxValue ) ) {
                return root1;
            } else if ( ( root2 >= minValue ) && ( root2 <= maxValue ) ) {
                return root2;
            } else {
                warnings_.add( "Polynomial Calibrator for " +
                               itemName_ +
                               " contains roots of " +
                               Double.toString( root1 ) +
                               " and " +
                               Double.toString( root2 ) +
                               ", neither of which are in the range of " +
                               Long.toString( minValue ) +
                               " to " +
                               Long.toString( maxValue ) +
                               " for encoding " +
                               rawTypeName_ );
                return 0.0;
            }
        }
        return root1;

    }


    // Private Data Members

    private String  itemName_;
    private String  euTypeName_;
    private String  rawTypeName_;
    private int     rawSizeInBits_;
    private String  rawBitOrder_;
    private String  lowValidValue_;
    private String  highValidValue_;
    private boolean lowValidValueInclusive_;
    private boolean highValidValueInclusive_;
    private boolean lowAppliesToCalibrated_;
    private boolean highAppliesToCalibrated_;
    private boolean validRangeExists_;
    private boolean validObject_;

    private String  booleanZeroString_;
    private String  booleanOneString_;

    private ArrayList<String>          warnings_ = new ArrayList<String>();
    private List<ValueEnumerationType> enums_    = null;
    private CalibratorType             defCal_   = null;

    private static final byte[] utf16_ = new byte[] { (byte)0xfe, (byte)0xff };

}
