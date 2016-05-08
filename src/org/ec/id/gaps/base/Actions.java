package org.ec.id.gaps.base;

import org.ec.id.gaps.jpa.entiti.sis.User;

public class Actions extends Session {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setUsuarioCurrent(User usuarioCurrent) {
		setAtribute("user", usuarioCurrent);
	}

	public User getUsuarioCurrent() {
		return (User) getAtribute("user");
	}
}
