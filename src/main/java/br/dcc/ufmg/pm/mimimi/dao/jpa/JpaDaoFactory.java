package br.dcc.ufmg.pm.mimimi.dao.jpa;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.dcc.ufmg.pm.mimimi.dao.Dao;
import br.dcc.ufmg.pm.mimimi.dao.DaoFactory;
import br.dcc.ufmg.pm.mimimi.model.EntityInterface;

public class JpaDaoFactory extends DaoFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JpaDaoFactory.class);
	
	/**
	 * A cache to store {@link Dao} objects, since {@link AbstractJpaDao} are thread safe,
	 * they can be stored and reused 
	 */
	private Map<Class<?>,Object> cache;
	
	public JpaDaoFactory() {
		cache = new HashMap<>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
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
			LOGGER.warn("It was not possible to instanciate Dao",e);
			return null;
		}
	}

}
