package br.dcc.ufmg.pm.mimimi.dao.jpa;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.Date;

import org.junit.Test;

import br.dcc.ufmg.pm.mimimi.dao.DaoException;
import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * Unity Test for testing {@link User} and {@link UserDao}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
public class UserTest extends AbstractJpaTest<String, User, UserDao>{

	public UserTest() {
		super(UserDao.class);
	}

	@Override
	public User generateEntity() {
		User user = new User();
		user.setBirthdate(new Date());
		user.setCity(generateRandomString());
		user.setUsername(generateRandomString());
		user.setMembersince(new Date());
		user.setPassword(generateRandomString());
		return user;
	}
	
	/**
	 * Tests whether login is working
	 */
	@Test
	public void loginTest() {
		try {
			User user = generateEntity();
			String originalPassword = user.getPassword();
			assertTrue(!originalPassword.equals(getDao().save(user).getPassword()));
			User login = getDao().login(user.getUsername(), originalPassword);
			assertNotNull(login);
			login = getDao().login(user.getUsername(), generateRandomString()+"a");
			assertNull(login);
			getDao().delete(user);
		} catch (DaoException e) {
			throw new AssertionError("Some Dao Error Happened", e);
		}
	}
	
}
