package org.ec.id.gaps.seguridad;

import java.security.acl.Group;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.security.auth.login.LoginException;

import org.ec.id.gaps.dao.sis.UserDAO;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

@ManagedBean
@RequestScoped
public class LoginAuth extends UsernamePasswordLoginModule {

	private static final Logger log = Logger.getLogger(LoginAuth.class.getName());
	private final String USER_NAME = "user";
	private final String USER_PWD = "user1";
	private final String ADMIN_NAME = "admin";
	private final String ADMIN_PWD = "admin1";
	private String role = "";
	@EJB
	private UserDAO userDao;

	@Override
	protected String getUsersPassword() throws LoginException {
		String username = getUsername();
		System.out.println(userDao);
		log.info(username);
		login();
		if (username.equalsIgnoreCase(USER_NAME)) {
			role = "admin";
			return USER_PWD;
		} else if (username.equalsIgnoreCase(ADMIN_NAME)) {
			role = "admin";
			return ADMIN_PWD;
		}
		return null;

	}

	@Override
	protected Group[] getRoleSets() throws LoginException {
		Group[] groups = { new SimpleGroup("Roles") };
		SimplePrincipal principal = new SimplePrincipal(role);
		groups[0].addMember(principal);
		return groups;
	}

}
