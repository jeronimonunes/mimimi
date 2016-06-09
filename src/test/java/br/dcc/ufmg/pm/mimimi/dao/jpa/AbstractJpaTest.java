package br.dcc.ufmg.pm.mimimi.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.dcc.ufmg.pm.mimimi.model.EntityInterface;

//TODO error messages
public abstract class AbstractJpaTest<IdType extends Serializable,T extends EntityInterface<IdType>, D extends AbstractJpaDao<IdType, T>> {

	private static EntityManagerFactory emf;
	
	private EntityManager entityManager;

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
		getDao().setEntityManager(getEntityManager());
	}
	
	@After
	public void closeEntityManager(){
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected abstract T getEntity();

	protected abstract D getDao();

	@Test
	public void save() {
		assertNotNull(getDao().save(getEntity()).getId());
	}

	@Test
	public void find() {
		assertNotNull(getDao().find(getEntity().getId()));
	}

	@Test
	public void list() {
		assertEquals(1,getDao().list().size());
	}

	@Test
	public abstract void update();

	@Test
	public void delete() {
		getDao().delete(getEntity());
		assertNull(getDao().find(getEntity().getId()));
	}

}
