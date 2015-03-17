/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.omg.space.xtce.toolkit;

import org.omg.space.xtce.database.CommandContainerType;

/** The XTCETCContainer class models the XTCE CommandContainer element by
 * simplifying the usage by pre-modeling the relationships. 
 *
 * @author Melanie Laub
 *
 */

public class XTCETCContainer extends XTCENamedObject {

    /** Constructor
     * 
     * @param path String containing the Space System path to this container
     * element, without the name of the container.
     *
     * @param iPath String containing the inheritance path for this container
     * element, including the name of this container.
     *
     * @param container CommandContainerType element from the XTCE data model
     * as generated in the JAXB classes.
     *
     */

    XTCETCContainer( String path, String iPath, CommandContainerType container ) {
        super( container.getName(), path, container.getAliasSet() );
        iPath_     = iPath;
        container_ = container;
    }

    /** Retrieve a reference to the CommandContainer element in the JAXB
     * generated classes for the XTCE data model.
     *
     * @return CommandContainerType element represented by this object.
     *
     */

    public CommandContainerType getCommandContainerReference() {
        return container_;
    }

    /** Retrieve the inheritance path for this CommandContainer.
     *
     * @return String containing the inheritance path to this container.
     *
     */

    public String getInheritancePath() {
        return iPath_;
    }

    /** Retrieve the shortDescription attribute for this CommandContainer.
     *
     * @return String containing the short description, or an empty string if
     * no short description is present.
     *
     */

    public String getShortDescription() {
        return getPrimaryShortDescription( container_ );
    }

    /** Sets the shortDescription attribute for this CommandContainer.
     *
     * In the event that the description is null or an empty string, then the
     * attribute will be removed from the element for compression.
     *
     * @param description String containing the new short description or an
     * empty string (null tolerated) to remove the attribute.
     *
     */

    public void setShortDescription( String description ) {
        setPrimaryShortDescription( container_, description );
    }

    /** Retrieve the LongDescription element text for this CommandContainer.
     *
     * @return String containing the long description, or an empty string if
     * no long description is present.
     *
     */

    public String getLongDescription() {
        return getPrimaryLongDescription( container_ );
    }

    /** Sets the Long Description element text for this CommandContainer.
     *
     * In the event that the description is null or an empty string, then the
     * element will be removed for compression.
     *
     * @param description String containing the new long description or an
     * empty string (null tolerated) to remove the element.
     *
     */

    public void setLongDescription( String description ) {
        setPrimaryLongDescription( container_, description );
    }

    /** Comparison method used to order the XTCETCContainer objects when sorted
     * based upon their inheritance path rather than the Space System path.
     *
     * Sorting by inheritance path is used by the graphical interface for the
     * generation of tree nodes.
     *
     * @param that XTCETCContainer object to compare to.
     *
     * @return Integer value of -1, 0, or 1.
     *
     */

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

    public String getDescription() {

        String parameterDescription = getShortDescription();
        if ( parameterDescription.isEmpty() == true ) {
            parameterDescription = getLongDescription();
        }

        return parameterDescription;

    }

    @Override
    public int compareTo( Object that ) {
        return this.getInheritancePath().compareTo(((XTCETCContainer)that).getInheritancePath() );
    }

    private String               iPath_     = null;
    private CommandContainerType container_ = null;

}
