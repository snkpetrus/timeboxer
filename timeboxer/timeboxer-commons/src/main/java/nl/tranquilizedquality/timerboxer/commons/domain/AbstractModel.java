package nl.tranquilizedquality.timerboxer.commons.domain;

/**
 * Client side representation of an object.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <T>
 *        Implementation type.
 */
public abstract class AbstractModel<T> implements DomainObject<T> {
    private static final long serialVersionUID = 3104762646305286306L;

    /** The primary key of the object. */
    protected T id;

    /**
     * @param id
     *        the id to set.
     */
    public void setId(final T id) {
        this.id = id;
    }

    @Override
    public T getId() {
        return this.id;
    }

    /**
     * Indicates that this instance has a persisted copy.
     * 
     * @return true if the object has a persisted copy.
     */
    @Override
    public boolean isPersistent() {
        if (getId() == null) {
            return false;
        }
        return true;
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
        this.id = object.getId();
    }
}
