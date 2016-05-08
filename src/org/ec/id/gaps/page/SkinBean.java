/**
 * 
 */
package org.ec.id.gaps.page;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.ec.id.gaps.base.BaseBean;
import org.ec.id.gaps.dao.sis.AccionNegocioDAO;
import org.ec.id.gaps.dao.sis.ElementoVistaDAO;
import org.ec.id.gaps.dao.sis.FiltroDAO;
import org.ec.id.gaps.dao.sis.ParametroDAO;
import org.ec.id.gaps.dao.sis.PerfilElementoVistaDAO;
import org.ec.id.gaps.dao.sis.UserDAO;
import org.ec.id.gaps.dao.sis.UserPerfilDAO;
import org.ec.id.gaps.enumeration.ConstantesEnum;
import org.ec.id.gaps.jpa.entiti.sis.Filtro;

/**
 * @author Freddy G. Castillo C
 *
 */
@ManagedBean
@SessionScoped
public class SkinBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private AccionNegocioDAO accionNegocioDAO;
	@EJB
	private PerfilElementoVistaDAO perfilElementoSistemaDAO;
	@EJB
	private FiltroDAO filtroDAO;
	@EJB
	private UserDAO userDAO;
	@EJB
	private ElementoVistaDAO elementoVistaDAO;
	@EJB
	private ParametroDAO parametroDAO;

	private Filtro skin;

	@EJB
	UserPerfilDAO userPerfilDAO;

	private static Map<String, Object> countries;
	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("English", Locale.ENGLISH); // label, value
		countries.put("Español", new Locale("es", "ES"));
	}

	public SkinBean() {
		skin = new Filtro();
	}

	@PostConstruct
	@Override
	protected void postConstructor() {
		try {
			perfilElementoSistemaDAO.executedInitScript();
			skin = filtroDAO.getFiltro(getUsuarioCurrent() == null ? userDAO.findByPk(parametroDAO.findCadena(ConstantesEnum.LOGIN_ADMIN.name()).toLowerCase()) : getUsuarioCurrent(), "THEME", elementoVistaDAO.findByPk(1),
					getUsuarioCurrent() == null ? "spacelab" : null, skin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTheme(String theme) {
		try {
			skin.setValorCadena(theme);
			skin = filtroDAO.getFiltro(getUsuarioCurrent() == null ? userDAO.findByPk(parametroDAO.findCadena(ConstantesEnum.LOGIN_ADMIN.name()).toLowerCase()) : getUsuarioCurrent(), "THEME", elementoVistaDAO.findByPk(1), null, skin);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void setLocale(String newLocaleValue) {
		try {
			for (Map.Entry<String, Object> entry : countries.entrySet()) {
				if (entry.getValue().toString().equals(newLocaleValue)) {
					FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public Filtro getSkin() {
		return skin;
	}

	public void setSkin(Filtro skin) {
		this.skin = skin;
	}

}
