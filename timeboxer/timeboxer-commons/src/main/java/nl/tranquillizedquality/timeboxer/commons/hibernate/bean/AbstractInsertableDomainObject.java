package nl.tranquillizedquality.timeboxer.commons.hibernate.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

import com.google.code.simplestuff.annotation.BusinessField;

/**
 * Abstract domain object that defines properties common to all domain objects.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <T>
 *        The key type.
 */
@MappedSuperclass
public abstract class AbstractInsertableDomainObject<T> extends AbstractDomainObject<T> implements InsertableDomainObject<T> {

    private static final long serialVersionUID = 140608694747066463L;

    /** The date on which this object was created. */
    @BusinessField
    private Date created;

    /** The user that created this object. */
    @BusinessField
    private String createdBy;

    /**
     * @return the creation date and time.
     */
    @Override
    @Type(type = "timestamp")
    @Column(name = "CREATED", unique = false, nullable = true)
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
    @Column(name = "CREATED_BY", unique = false, nullable = true)
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
            this.created = domainObject.getCreated();
            this.createdBy = domainObject.getCreatedBy();
        }
    }
}
