package nl.tranquillizedquality.timeboxer.commons.hibernate.bean;

import javax.persistence.Transient;

import com.google.code.simplestuff.annotation.BusinessField;
import com.google.code.simplestuff.bean.AbstractBusinessObject;


/**
 * Basic domain object with a unique identifier.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <T>
 *        The key type.
 */
public abstract class AbstractDomainObject<T> extends AbstractBusinessObject implements DomainObject<T> {

    /**
     * 
     */
    private static final long serialVersionUID = 6326018118241895855L;

    @BusinessField
    protected T id;

    /**
     * @param id
     *        the id to set.
     */
    public void setId(final T id) {
        this.id = id;
    }

    /**
     * Indicates that this instance has a persisted copy.
     * 
     * @return true if the object has a persisted copy.
     */
    @Override
    @Transient
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
        if (object instanceof DomainObject) {
            final DomainObject<T> domainObject = object;
            this.id = domainObject.getId();
        }
    }

}
