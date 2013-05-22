package nl.tranquilizedquality.timerboxer.commons.domain;

import java.util.Date;

/**
 * Client side representation of a updateable object.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <T>
 */
public abstract class AbstractUpdatableModel<T> extends AbstractInsertableModel<T> implements UpdatableDomainObject<T> {
    private static final long serialVersionUID = -3984937904058198249L;

    /** The alter date. */
    private Date altered;

    /** The user that altered the object. */
    private String alteredBy;

    /**
     * @return the date and time of the last update.
     */
    @Override
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
        if (object instanceof UpdatableDomainObject) {
            final UpdatableDomainObject<T> domainObject = (UpdatableDomainObject<T>) object;
            this.altered = domainObject.getAltered();
            this.alteredBy = domainObject.getAlteredBy();
        }
    }
}
