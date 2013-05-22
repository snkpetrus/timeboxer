package nl.tranquilizedquality.timerboxer.commons.domain.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.tranquilizedquality.timerboxer.commons.domain.DomainObject;

/**
 * Factory for transforming client side beans to persistent beans and visa versa.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <ClientType>
 *        The client side implementation.
 * @param <PersistentType>
 *        The persistent type side implementation.
 * @param <InterfaceType>
 *        The interface of the domain object.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbstractFactory<ClientType extends DomainObject, PersistentType extends DomainObject, InterfaceType extends DomainObject> {

    /**
     * Creates a new client side representation bean.
     * 
     * @return Returns the client side bean.
     */
    protected abstract ClientType createNewClientBean();

    /**
     * Transforms a single {@link InterfaceTye} into a {@link ClientType} object.
     * 
     * @param bean
     *        The object that will be transformed.
     * @return Returns a client side representation that has all the values of
     *         the passed in bean.
     */
    public ClientType createClientBean(final InterfaceType bean) {
        final ClientType clientBean = createNewClientBean();
        clientBean.copy(bean);

        return clientBean;
    }

    /**
     * Transforms all InterfaceType objects in the passed in {@link Collection} into a
     * ClientType object.
     * 
     * @param beans
     *        the {@link Collection} containing all the {@link Privilege} objects that
     *        will be transformed.
     * @return Returns a {@link List} containing objects that were passed in.
     */
    public List<ClientType> createClientBeans(final Collection<InterfaceType> beans) {
        final List<ClientType> clientBeans = new ArrayList<ClientType>();

        for (final InterfaceType bean : beans) {
            final ClientType clientBean = createNewClientBean();
            clientBean.copy(bean);

            clientBeans.add(clientBean);
        }

        return clientBeans;
    }

    protected abstract PersistentType createNewPersistentBean();

    /**
     * Transforms a single bean into a persistent type object.
     * 
     * @param bean
     *        The object that will be transformed.
     * @return Returns a persistent object that has all the values of the passed
     *         in bean.
     */
    public PersistentType createPersistentBean(final InterfaceType bean) {
        final PersistentType persistentBean = createNewPersistentBean();
        persistentBean.copy(bean);

        return persistentBean;
    }

    /**
     * Transforms all objects in the passed in {@link Collection} into a persistent
     * object.
     * 
     * @param beans
     *        the {@link Collection} containing all the objects that will be
     *        transformed.
     * @return Returns a {@link List} containing persistent objects that were
     *         passed in.
     */
    public List<PersistentType> createPersistentBeans(final Collection<ClientType> beans) {
        final List<PersistentType> persistentBeans = new ArrayList<PersistentType>();

        for (final ClientType type : beans) {
            final PersistentType persistentBean = createNewPersistentBean();
            persistentBean.copy(type);

            persistentBeans.add(persistentBean);
        }

        return persistentBeans;
    }
}
