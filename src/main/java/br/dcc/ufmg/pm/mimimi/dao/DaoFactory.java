package br.dcc.ufmg.pm.mimimi.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.dcc.ufmg.pm.mimimi.model.EntityInterface;

/**
 * Factory for generating Daos
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 */
public abstract class DaoFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DaoFactory.class);
	
	private static DaoFactory INSTANCE;
	
	/**
	 * Finds the correct implementation of the given interface and instantiate it
	 * @param daoClass
	 * @return An instance (newly created or not) of the given interface
	 */
	public abstract <IdType extends Serializable,EntityType extends EntityInterface<IdType>,DaoType extends Dao<IdType,EntityType>> DaoType getDao(Class<DaoType> daoClass);
	
	/**
	 * Makes sure to find the correct implementation by configuration file
	 * @return A Singleton instance of the founded implementation
	 */
	public static DaoFactory getInstance() {
		if(INSTANCE==null){
			try(InputStream is = DaoFactory.class.getResourceAsStream("/META-INF/dao.properties")){
				Properties config = new Properties();
				config.load(is);
				INSTANCE = (DaoFactory) Class.forName(config.getProperty("factory")).newInstance();
			} catch (IOException e) {
				LOGGER.warn("It was not possible to load Dao configuration file",e);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e){
				LOGGER.warn("It was not possible to instanciate DaoFactory",e);
			}
		}
		return INSTANCE;
	}

}
