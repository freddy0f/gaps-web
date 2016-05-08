/**
 * 
 */
package org.ec.id.gaps.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.ec.id.gaps.base.BaseBean;
import org.ec.id.gaps.dao.sis.ElementoVistaDAO;
import org.ec.id.gaps.dao.sis.UserDAO;
import org.ec.id.gaps.enumeration.SiNoEnum;
import org.ec.id.gaps.enumeration.TipoElementoVistaEnum;
import org.ec.id.gaps.jpa.entiti.sis.ElementoVista;

import net.bootsfaces.component.dropMenu.DropMenu;

/**
 * @author Freddy G. Castillo C
 *
 */
@ManagedBean
@SessionScoped
public class PageBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	ElementoVistaDAO elementoVistaDAO;
	@EJB
	UserDAO userDAO;

	private DropMenu dropMenu;
	private Boolean isLogin = false;
	private List<ElementoVista> elementoVistas = new ArrayList<>(0);

	public PageBean() {
		System.out.println("Inicio");
	}

	@PostConstruct
	@Override
	protected void postConstructor() {
		super.postConstructor();
		try {
			if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null) {
				String login = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();

				if (login != null) {
					setUsuarioCurrent(userDAO.findByParameter("User.findByLogin", login));
					//// setAuditoriaLogin();
					elementoVistas = elementoVistaDAO.findListByParameter("ElementoVista.findAllByUser", null, TipoElementoVistaEnum.MOD, getUsuarioCurrent(), SiNoEnum.S, SiNoEnum.S, SiNoEnum.S, SiNoEnum.S);
					if (!elementoVistas.isEmpty())
						createMenu(elementoVistas.isEmpty() ? null : elementoVistas.get(0));
				}
				// // Número de registros en la tabla
				// Integer numeroRegiscreateMenutros =
				// parametroBO.getInteger("",
				//// getUsuarioCurrent().getIdComunidad().getIdComunidad(),
				//// "NUMREGTAB");
				// setNumeroFilas(numeroRegistros);
			}

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("skinBean");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("skinBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createMenu(ElementoVista padre) {
	}

	public DropMenu getDropMenu() {
		return dropMenu;
	}

	public void setDropMenu(DropMenu dropMenu) {
		this.dropMenu = dropMenu;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

}
