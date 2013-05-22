package nl.tranquillizedquality.timeboxer.commons.hibernate.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.management.Query;

/**
 * The base DAO for doing Hibernate actions.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 * @param <T>
 *        Domain object that is managed by this DAO.
 * @param <KeyType>
 *        The key type of this object.
 */
@Repository
public abstract class AbstractHibernateBaseDao<T extends DomainObject<KeyType>, KeyType extends Serializable> implements
        BaseDao<T, KeyType> {

    /** The Hibernate session factory. */
    private SessionFactory sessionFactory;

    /** The logger for this class. */
    private static Logger log = LoggerFactory.getLogger(AbstractHibernateBaseDao.class);

    /** The type of class the DAO is managing. */
    protected Class<T> domainClass = getDomainClass();

    /** The from clause for this DAO. */
    protected String from = "from " + domainClass.getName();

    /**
     * Method to return the class of the domain object.
     * 
     * @return Returns a new domain object of the specified class type.
     */
    protected abstract Class<T> getDomainClass();

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria getDefaultCriteria() {
        final Session currentSession = getCurrentSession();
        return currentSession.createCriteria(domainClass);
    }

    @Override
    public T save(final T object) {
        final Session currentSession = getCurrentSession();
        currentSession.saveOrUpdate(object);
        return object;
    }

    @Override
    public void delete(final T object) {
        final Session currentSession = getCurrentSession();

        try {
            currentSession.delete(object);
        } catch (final RuntimeException e) {
            log.error(e.getMessage());
            currentSession.evict(object);
            throw new RuntimeException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(final KeyType id) {
        final Session currentSession = getCurrentSession();
        return (T) currentSession.get(domainClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        final Session currentSession = getCurrentSession();
        final Criteria criteria = currentSession.createCriteria(domainClass);

        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findBySearchCommand(final PagingSearchCommand sc) {
        final Session currentSession = getCurrentSession();
        final Criteria criteria = currentSession.createCriteria(domainClass);

        criteria.setMaxResults(sc.getMaxResults());
        criteria.setFirstResult(sc.getStart());

        if (sc.getOrderBy() != null) {
            if (sc.isAsc()) {
                final Order order = Order.asc(sc.getOrderBy());
                criteria.addOrder(order);
            } else {
                final Order order = Order.desc(sc.getOrderBy());
                criteria.addOrder(order);
            }
        }

        final List<T> operators = criteria.list();

        return operators;

    }

    @Override
    public Integer getObjectCount() {
        final Session currentSession = getCurrentSession();
        final Criteria criteria = currentSession.createCriteria(domainClass);
        criteria.setProjection(Projections.rowCount());

        final Long count = (Long) criteria.uniqueResult();
        return count.intValue();
    }

    @Override
    public void deleteAll() throws DataAccessException {
        final Session currentSession = getCurrentSession();
        final Query query = currentSession.createQuery("delete " + domainClass.getName());
        query.executeUpdate();
    }

    @Override
    public void refresh(final T object) {
        final Session currentSession = getCurrentSession();
        currentSession.refresh(object);
    }

    @Override
    public void flush() {
        final Session currentSession = getCurrentSession();
        currentSession.flush();
    }

    @Override
    public void clear() {
        final Session currentSession = getCurrentSession();
        currentSession.clear();
    }

    protected void configurePagingAndSorting(final PagingSearchCommand sc, final Criteria criteria) {
        final Integer maxResults = sc.getMaxResults();
        if (maxResults != null) {
            criteria.setMaxResults(maxResults);
        }

        final Integer start = sc.getStart();
        if (start != null) {
            criteria.setFirstResult(start);
        }

        final String orderBy = sc.getOrderBy();
        if (StringUtils.isNotBlank(orderBy)) {
            if (sc.isAsc()) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }
    }

    @Override
    public abstract T newDomainObject();

    @Required
    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
