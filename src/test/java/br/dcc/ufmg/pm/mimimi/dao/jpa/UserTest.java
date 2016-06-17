package br.dcc.ufmg.pm.mimimi.dao.jpa;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;

import br.dcc.ufmg.pm.mimimi.dao.DaoFactory;
import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.model.User;

public class UserTest extends AbstractJpaTest<String, User, JpaUserDao>{
	
	private static User user;
	
	private static JpaUserDao userDao;

	@BeforeClass
	public static void init() {
		user = new User();
		user.setBirthdate(new Date());
		user.setCity("city1");
		user.setUsername("user1");
		user.setMembersince(new Date());
		user.setPassword("12345");
		userDao = (JpaUserDao) DaoFactory.getInstance().getDao(UserDao.class);
	}
	
	@Override
	protected User getEntity() {
		return user;
	}
	
	@Override
	protected JpaUserDao getDao() {
		return userDao;
	}
	
	@Override
	public void update() {
		getEntity().setCity("city2");
		userDao.update(getEntity());
		assertEquals("city2", getEntity().getCity());
	}
	

}
