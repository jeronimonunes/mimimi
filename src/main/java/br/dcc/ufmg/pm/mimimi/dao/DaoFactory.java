package br.dcc.ufmg.pm.mimimi.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.dcc.ufmg.pm.mimimi.dao.jpa.AbstractJpaDao;
import br.dcc.ufmg.pm.mimimi.model.EntityInterface;

public class DaoFactory {
	
	private static final DaoFactory INSTANCE = new DaoFactory();
	
	/**
	 * A cache to store {@link Dao} objects, since {@link AbstractJpaDao} are thread safe,
	 * they can be stored and reused 
	 */
	private Map<Class<?>,Object> cache;
	
	private DaoFactory() {
		cache = new HashMap<>();
	}
	
	@SuppressWarnings("unchecked")
	public <IdType extends Serializable,EntityType extends EntityInterface<IdType>,DaoType extends Dao<IdType,EntityType>> DaoType getDao(Class<DaoType> daoClass){
		try {
			Object dao = cache.get(daoClass);
			if(dao == null){
				String daoName = daoClass.getPackage().getName()+".jpa.Jpa"+daoClass.getSimpleName();
				dao = Class.forName(daoName).newInstance();
				cache.put(daoClass, dao);
			}
			return (DaoType) dao;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			//TODO exception
			return null;
		}
	}

	public static DaoFactory getInstance() {
		return INSTANCE;
	}

}
