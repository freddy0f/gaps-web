package org.ec.id.gaps.base;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

abstract class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setAtribute(String key, Object value) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.getSessionMap().put(key, value);
	}

	public Object getAtribute(String key) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> map = externalContext.getSessionMap();
		return map.get(key);
	}
}
