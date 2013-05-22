package nl.tranquilizedquality.timerboxer.commons.domain;

import java.util.Date;

/**
 * Domain object that registeres the created by and when history.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <T>
 *        Primary key type.
 */
public interface InsertableDomainObject<T> extends DomainObject<T> {

    /**
     * Retrieves the date on which this object was created.
     * 
     * @return Returns a {@link Date}.
     */
    Date getCreated();

    /**
     * Retrieves the user this object was created by.
     * 
     * @return Returns a {@link String} representation of the user.
     */
    String getCreatedBy();
}
