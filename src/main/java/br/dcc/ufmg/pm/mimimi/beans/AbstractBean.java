package br.dcc.ufmg.pm.mimimi.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.ResourceHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.dcc.ufmg.pm.mimimi.dao.Dao;
import br.dcc.ufmg.pm.mimimi.dao.DaoFactory;
import br.dcc.ufmg.pm.mimimi.jsf.JpaFilter;
import br.dcc.ufmg.pm.mimimi.model.EntityInterface;

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
	
	protected <IdType extends Serializable,EntityType extends EntityInterface<IdType>,DaoType extends Dao<IdType,EntityType>> DaoType getDao(Class<DaoType> daoClass){
		return DaoFactory.getInstance().getDao(daoClass);
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
	
	@SuppressWarnings("unchecked")
	public static <T> T getSessionBean(HttpSession session,Class<T> beanClass){
		return (T) session.getAttribute(beanClass.getAnnotation(ManagedBean.class).name());
	}
	
	public static <T> T getSessionBean(Class<T> beanClass){
		return getSessionBean(getSession(),beanClass);
	}
	
	protected final static HttpSession getSession() {
		return getHttpServletRequest().getSession();
	}
	
	protected final static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}
	
	protected final static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	protected final static ResourceHandler getResourceHandler() {
		return FacesContext.getCurrentInstance().getApplication().getResourceHandler();
	}
	
	protected final static ServletContext getServletContext() {
		return (ServletContext) getExternalContext().getContext();
	}
	
	protected final static String getRealPath(String path){
		return getServletContext().getRealPath(path);
	}
	
}
