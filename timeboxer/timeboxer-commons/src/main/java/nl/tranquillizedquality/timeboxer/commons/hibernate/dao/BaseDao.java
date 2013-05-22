package nl.tranquillizedquality.timeboxer.commons.hibernate.dao;

import java.util.List;

/**
 * Interface representing the basic functionalities of a DAO.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <T>
 *        The implementation type that this DAO is managing.
 * @param <K>
 *        The key type of the object this DAO is managing.
 */
public interface BaseDao<T, K> {

    /**
     * Stores the passed object into a data store.
     * 
     * @param object
     *        The object that will be stored.
     * @return Returns the saved object.
     */
    T save(final T object);

    /**
     * Removes the passed object from the data store.
     * 
     * @param object
     *        The object that will be removed.
     */
    void delete(final T object);

    /**
     * Finds an object with the specified identifier.
     * 
     * @param id
     *        The unique identifier of the object.
     * @return Returns the object with the corresponding unique identifier.
     */
    T findById(final K id);

    /**
     * Retrieves all objects.
     * 
     * @return Returns a {@link List} containing all available objects.
     */
    List<T> findAll();

    /**
     * Deletes all objects.
     * 
     * @throws DataAccessException
     *         Is thrown when something goes wrong during deletion.
     */
    void deleteAll() throws DataAccessException;

    /**
     * Refreshed the object with the data store.
     * 
     * @param object
     *        The object that will be refreshed.
     */
    void refresh(final T object);

    /**
     * Flushes the session.
     */
    void flush();

    /**
     * Clears the session.
     */
    void clear();

    /**
     * Retrieves a new domain object of the type that this DAO is managing.
     * 
     * @return Returns a domain object of type T.
     */
    T newDomainObject();

    /**
     * Finds all objects of type T in a specific chunk which is configured
     * within the {@link PagingSearchCommand}.
     * 
     * @param sc
     *        The {@link PagingSearchCommand} configuration.
     * @return Returns a list containing objects of type T or an emtpy one if
     *         none could be found.
     */
    List<T> findBySearchCommand(PagingSearchCommand sc);

    /**
     * Retrieves the number of objects that are available.
     * 
     * @return Returns the number of available objects.
     */
    Integer getObjectCount();

}
