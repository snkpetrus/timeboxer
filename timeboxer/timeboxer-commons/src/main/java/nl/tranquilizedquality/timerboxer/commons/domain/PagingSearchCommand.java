package nl.tranquilizedquality.timerboxer.commons.domain;

import java.io.Serializable;

/**
 * A generic search command object that can be used to retrieve objects in
 * chunks.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 3 jun. 2011
 */
public class PagingSearchCommand implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2434033520080536116L;

    /** The maximum number of results that are allowed to be retrieved. */
    private Integer maxResults;

    /** The starting record number from where records will be retrieved. */
    private Integer start;

    /** The column name to order on. */
    private String orderBy;

    /**
     * Determines if there should be an ascending ordering or a descending
     * ordering.
     */
    private boolean asc;

    /**
     * @return the maxResults
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     * @param maxResults
     *        the maxResults to set
     */
    public void setMaxResults(final Integer maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * @return the start
     */
    public Integer getStart() {
        return start;
    }

    /**
     * @param start
     *        the start to set
     */
    public void setStart(final Integer start) {
        this.start = start;
    }

    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy
     *        the orderBy to set
     */
    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return the asc
     */
    public boolean isAsc() {
        return asc;
    }

    /**
     * @param asc
     *        the asc to set
     */
    public void setAsc(final boolean asc) {
        this.asc = asc;
    }

}
