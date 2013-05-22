package nl.tranquilizedquality.timerboxer.commons.domain;

import java.util.Date;

/**
 * Client side representation of an instertable object.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <T>
 *        Implementation type.
 */
public abstract class AbstractInsertableModel<T> extends AbstractModel<T> implements InsertableDomainObject<T> {
    private static final long serialVersionUID = -2834176286351239723L;

    /** The date the object was created. */
    private Date created;

    /** The user that created this object. */
    private String createdBy;

    /**
     * Default constructor.
     */
    public AbstractInsertableModel() {
        created = new Date();
    }

    /**
     * @return the creation date and time.
     */
    @Override
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     *        the creation date and time to set.
     */
    public void setCreated(final Date created) {
        this.created = created;
    }

    /**
     * @return the name of the user that created this instance.
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     *        the name of the user that creates this instance.
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Copies properties of the given object to this instance. Subclasses should
     * override this method to copy their properties too and call super.copy()
     * to copy the inherited properties.
     * 
     * @param object
     *        the object to copy.
     */
    @Override
    public void copy(final DomainObject<T> object) {
        super.copy(object);
        if (object instanceof InsertableDomainObject) {
            final InsertableDomainObject<T> domainObject = (InsertableDomainObject<T>) object;
            created = domainObject.getCreated();
            createdBy = domainObject.getCreatedBy();
        }
    }
}
