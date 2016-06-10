package br.dcc.ufmg.pm.mimimi.lazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.dcc.ufmg.pm.mimimi.filter.JpaFilter;
import br.dcc.ufmg.pm.mimimi.model.EntityInterface;

public abstract class AbstractLazyList<T extends EntityInterface<? extends Serializable>> extends LazyDataModel<T>{

	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		if(entityManager==null || !entityManager.isOpen()){
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			entityManager = (EntityManager) request.getAttribute(JpaFilter.ENTITY_MANAGER);
		}
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public T getRowData(String rowKey) {
		@SuppressWarnings("unchecked")
		List<T> data = (List<T>) super.getWrappedData();
		for(T object : data) {
			if(object.getId().toString().equals(rowKey))
				return object;
		}
		return null;
	}

	@Override
	public Object getRowKey(T object) {
		return object.getId().toString();
	}
	
	protected abstract int load(int first, int pageSize);
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        setRowCount(load(first,pageSize));
        return (List<T>) getWrappedData();
    }
    
	@Override
	@SuppressWarnings("unchecked")
    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String,Object> filters) {
		setRowCount(load(first,pageSize));
        return (List<T>) getWrappedData();
    }

}
