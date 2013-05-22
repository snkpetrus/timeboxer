package nl.tranquilizedquality.timerboxer.commons.domain;

import java.io.Serializable;

/**
 * Inerface that represents a domain object.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <T>
 *        Primary key type.
 */
public interface DomainObject<T> extends Serializable {

    /**
     * Gets the unique identifier for the object.
     * 
     * @return Returns the id.
     */
    T getId();

    /**
     * Determines if the object is persistent or not.
     * 
     * @return Returns true if the object is persistent.
     */
    boolean isPersistent();

    /**
     * Copies the values of the passed in object to the current object.
     * 
     * @param object
     *        The object where the values will be copied from.
     */
    void copy(DomainObject<T> object);

}
