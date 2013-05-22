/**
 * <pre>
 * Project: automated-deployment-manager-commons Created on: 27 jul. 2011 File: MapMergerFactoryBean.java
 * Package: nl.tele2.adm.commons.hibernate
 * 
 * Copyright (c) 2011 Tele2 www.tele2.nl All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Tele2
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the license
 * agreement you entered into with Tele2.
 * </pre>
 */
package nl.tranquillizedquality.timeboxer.commons.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * {@link MapFactoryBean} that can merge factory bean lists.
 * 
 * @author Salomo Petrus (salomo.petrus@tele2.com)
 * @since 27 jul. 2011
 */
public class MapMergerFactoryBean extends MapFactoryBean {

	private List<Map<?, ?>> mergeList;

	/**
	 * Default constructor.
	 */
	public MapMergerFactoryBean() {
		mergeList = new ArrayList<Map<?, ?>>();

		setSourceMap(new LinkedHashMap<Object, Object>());
	}

	/**
	 * Sets the list of lists that need to be merged.
	 * 
	 * @param mergeList
	 *            The list of lists that will be merged.
	 */
	public void setMergeList(final List<Map<?, ?>> mergeList) {
		this.mergeList = mergeList;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map createInstance() {
		final Map mapOrigin = super.createInstance();

		for (final Map map : mergeList) {

			final Set entrySet = map.entrySet();
			final Iterator iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				final Map.Entry pairs = (Map.Entry) iterator.next();

				mapOrigin.put(pairs.getKey(), pairs.getValue());
			}
		}

		System.out.println(mapOrigin);

		return mapOrigin;
	}

}
