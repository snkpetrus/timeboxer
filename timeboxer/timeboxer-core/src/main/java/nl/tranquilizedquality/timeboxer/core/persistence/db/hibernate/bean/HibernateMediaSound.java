/*
 * @(#)HibernateDeployer.java 12 dec. 2012
 * 
 * Copyright (c) 2009 Tele2 All rights reserved.
 * Tele2 PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package nl.tranquilizedquality.timeboxer.core.persistence.db.hibernate.bean;


/**
 * Hibernate implementation of a Team in the application.
 * 
 */
@Entity()
@Table(name = "MEDIA_SOUND", schema = "TB")
public class HibernateMediaSound extends AbstractUpdatableDomainObject<Long> implements MediaSound {

    /** The name of the deployer. */
    @BusinessField
    private String name;

    @Override
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MEDIA_SOUND_SEQ_GEN")
    @SequenceGenerator(name = "MEDIA_SOUND_SEQ_GEN", initialValue = 1, allocationSize = 1, sequenceName = "TB.MEDIA_SOUND_SEQ_GEN")
    public Long getId() {
        return id;
    }

    @Override
    @Column(name = "NAME", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
