package com.vertexnet.realestate.dao;

import java.util.List;

import com.vertexnet.realestatenet.model.bean.Layout;

public class LayoutDAO {
	
	/**
	 * Saves or Updates the Customer Data
	 * @param customer
	 */
	public static void saveLayout(Layout layout) {
		HibernateUtil.saveObject(layout);
	}
	
	public static void deleteLayout(Layout layout) {
		HibernateUtil.deleteObject(layout);
	}

	@SuppressWarnings("unchecked")
	public static List<Layout> getLayouts() {
		return (List<Layout>) HibernateUtil.getObjects(Layout.class);
	}

	public static void deleteLayouts(List<Layout> layouts) {
		for(Layout layout : layouts) {
			HibernateUtil.deleteObject(layout);
		}
	}
}
