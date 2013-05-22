package nl.tranquillizedquality.timeboxer.commons.hibernate.bean;

import java.util.Date;

/**
 * Abstract doamin object that defines properties common to all domain objects
 * that should track the modification time and user.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <T>
 *        The key type.
 */
@MappedSuperclass
public abstract class AbstractUpdatableDomainObject<T> extends AbstractInsertableDomainObject<T> implements UpdatableDomainObject<T> {
    private static final long serialVersionUID = 2471426230030867891L;

    /** The date on which this object was altered the last time. */
    @BusinessField
    private Date altered;

    /** The user that altered the object the last time. */
    @BusinessField
    private String alteredBy;

    /**
     * @return the date and time of the last update.
     */
    @Override
    @Type(type = "timestamp")
    @Column(name = "ALTERED", unique = false, nullable = true)
    public Date getAltered() {
        return altered;
    }

    /**
     * @param altered
     *        the alteration date and time to set.
     */
    public void setAltered(final Date altered) {
        this.altered = altered;
    }

    /**
     * @return the name of the user that last updated this instance
     */
    @Override
    @Column(name = "ALTERED_BY", unique = false, nullable = true)
    public String getAlteredBy() {
        return alteredBy;
    }

    /**
     * @param alteredBy
     *        the name of the user that last modified this instance.
     */
    public void setAlteredBy(final String alteredBy) {
        this.alteredBy = alteredBy;
    }

    /**
     * Copies properties of the given object to this instance.
     * Subclasses should override this method to copy their properties too
     * and call super.copy() to copy the inherited properties.
     * 
     * @param object
     *        the object to copy.
     */
    @Override
    public void copy(final DomainObject<T> object) {
        if (object instanceof UpdatableDomainObject) {
            final UpdatableDomainObject<T> domainObject = (UpdatableDomainObject<T>) object;
            this.altered = domainObject.getAltered();
            this.alteredBy = domainObject.getAlteredBy();
        }
        super.copy(object);
    }
}
