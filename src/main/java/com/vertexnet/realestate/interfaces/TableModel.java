package com.vertexnet.realestate.interfaces;

import java.util.List;
import com.vertexnet.realestate.view.utility.DefaultTable;

public interface TableModel {
	void setObjects(List<?> objects);
	
	List<?> getSelectedObjects();
	
	List<?> getObjects();

	void setTable(DefaultTable table);
}
