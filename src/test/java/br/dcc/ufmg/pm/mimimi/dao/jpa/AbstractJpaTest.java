package br.dcc.ufmg.pm.mimimi.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.dcc.ufmg.pm.mimimi.dao.Dao;
import br.dcc.ufmg.pm.mimimi.dao.DaoException;
import br.dcc.ufmg.pm.mimimi.dao.DaoFactory;
import br.dcc.ufmg.pm.mimimi.model.EntityInterface;

/**
 * Abstract Test to test {@link EntityInterface} and {@link AbstractJpaDao}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 * @param <IdType> The Type of the {@link EntityInterface} Primary Key
 * @param <T> The {@link EntityInterface} type
 * @param <D> The {@link Dao} type
 */
public abstract class AbstractJpaTest<IdType extends Serializable,T extends EntityInterface<IdType>, D extends Dao<IdType, T>> {
	
	/**
	 * A Random generator to serve random strings to subclasses
	 */
	private static final SecureRandom RANDOM = new SecureRandom();

	/**
	 * The Factory responsible for generating {@link EntityManager}
	 */
	private static EntityManagerFactory emf;

	/**
	 * The {@link EntityManager} responsible for managing data in JPA context
	 */
	private EntityManager entityManager;

	/**
	 * The {@link AbstractJpaDao} implementation to be tested
	 */
	private D dao;

	/**
	 * Constructs a new {@link AbstractJpaTest}
	 * @param class1 The {@link AbstractJpaDao} type to be tested
	 */
	public AbstractJpaTest(Class<D> daoClass) {
		this.setDao(DaoFactory.getInstance().getDao(daoClass));
	}

	/**
	 * Performs a CRUD test of the {@link EntityInterface}
	 * to makes sure that the {@link Dao} works
	 * It does not test update method, since it's Entity dependent
	 */
	@Test
	public void crud() {
		try {
			List<T> entities = new ArrayList<>(10);
			for(int i = 0; i < 10; i++) {
				T entity = generateEntity();
				entities.add(entity);
				//makes sure insert is correct
				assertNotNull(getDao().save(entity).getId());
			}
			List<T> recoveredEntities = getDao().list();
			//makes shure list is correct 
			assertEquals(entities.size(), recoveredEntities.size());
			assertTrue(recoveredEntities.containsAll(entities));
			//deleting
			for(T entity : recoveredEntities){
				getDao().delete(entity);
			}
			recoveredEntities = getDao().list();
			//makes sure delete is correct 
			assertEquals(0, recoveredEntities.size());
		} catch (DaoException e) {
			throw new AssertionError("Some Dao Error Happened", e);
		}
	}

	protected static final String generateRandomString() {
		return new BigInteger(130, RANDOM).toString(32);
	}

	/**
	 * Method to be extended to generate random valid T
	 * @return A new T filled
	 */
	public abstract T generateEntity();

	@BeforeClass
	public static void openDatabase(){
		emf = Persistence.createEntityManagerFactory("mimimi");
	}

	@AfterClass
	public static void closeDatabase(){
		emf.close();
	}

	@Before
	public void openEntityManager(){
		entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		((AbstractJpaDao<IdType,T>) getDao()).setEntityManager(getEntityManager());
	}

	@After
	public void closeEntityManager(){
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected D getDao() {
		return dao;
	}

	protected void setDao(D dao) {
		this.dao = dao;
	}

}
