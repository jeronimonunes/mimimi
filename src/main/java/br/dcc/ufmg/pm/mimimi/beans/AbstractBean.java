package br.dcc.ufmg.pm.mimimi.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
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
	
	/**
	 * Método utilizado para adicionar uma mensagem na fila de mensagens do cliente.
	 * @param msg
	 */
	protected final void addMessage(String msg){
		addMessage(new FacesMessage(msg));
	}

	protected final void addMessage(FacesMessage msg){
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	protected final void addError(String error){
		addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, error, null));
	}
	
	protected final void addWarning(String warn){
		addMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, warn, null));
	}
	
}
