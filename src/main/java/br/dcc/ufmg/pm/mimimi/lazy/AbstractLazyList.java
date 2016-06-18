package br.dcc.ufmg.pm.mimimi.lazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.dcc.ufmg.pm.mimimi.dao.Dao;
import br.dcc.ufmg.pm.mimimi.dao.DaoFactory;
import br.dcc.ufmg.pm.mimimi.model.EntityInterface;

/**
 * Class to bring data from dao in pages to be used along with primefaces lazy components
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 * @param <T> The {@link EntityInterface} that will be recovered from database
 */
public abstract class AbstractLazyList<T extends EntityInterface<? extends Serializable>> extends LazyDataModel<T>{

	private static final long serialVersionUID = 1L;
	
	protected <IdType extends Serializable,EntityType extends EntityInterface<IdType>,DaoType extends Dao<IdType,EntityType>> DaoType getDao(Class<DaoType> daoClass){
		return DaoFactory.getInstance().getDao(daoClass);
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
