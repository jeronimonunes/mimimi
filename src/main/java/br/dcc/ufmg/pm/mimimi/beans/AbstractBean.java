package br.dcc.ufmg.pm.mimimi.beans;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.dcc.ufmg.pm.mimimi.filter.JpaFilter;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		entityManager = (EntityManager) request.getAttribute(JpaFilter.ENTITY_MANAGER);
		return entityManager;
	}

}
