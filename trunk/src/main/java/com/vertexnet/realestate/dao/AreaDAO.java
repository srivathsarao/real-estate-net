package com.vertexnet.realestate.dao;

import java.util.List;

import com.vertexnet.realestatenet.model.bean.Area;

public class AreaDAO {

	@SuppressWarnings("unchecked")
	public static List<Area> getAreas() {
		return (List<Area>) HibernateUtil.getObjects(Area.class);
	}

	public static void saveAreas(List<Area> areasToAdd) {
		for(Area area : areasToAdd) {
			HibernateUtil.saveObject(area);
		}
	}

	public static void deleteAreas(List<Area> areasToDelete) {
		for(Area area : areasToDelete) {
			HibernateUtil.deleteObject(area);
		}
	}

}
