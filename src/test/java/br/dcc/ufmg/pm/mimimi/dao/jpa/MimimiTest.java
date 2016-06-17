package br.dcc.ufmg.pm.mimimi.dao.jpa;

import static org.junit.Assert.assertEquals;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.dcc.ufmg.pm.mimimi.dao.MimimiDao;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * Test class for {@link Mimimi} and {@link MimimiDao}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
public class MimimiTest extends AbstractJpaTest<Long,Mimimi,MimimiDao> {
	
	private User user;

	public MimimiTest() {
		super(MimimiDao.class);
		user = new User();
		user.setBirthdate(new Date());
		user.setCity(generateRandomString());
		user.setUsername(generateRandomString());
		user.setMembersince(new Date());
		user.setPassword(generateRandomString());
	}
	
	@Before
	public void initUser() {
		getEntityManager().persist(user);
	}
	
	@After
	public void endUser() {
		getEntityManager().remove(user);
	}

	@Override
	public Mimimi generateEntity() {
		return new Mimimi(generateRandomString(), user);
	}
	
	@Test
	public void testFormatMarks(){
		Mimimi m = new Mimimi("@jeronimo",user);
		assertEquals("<a href=\"/user?user=jeronimo\">@jeronimo</a>", m.getMessageDecorated(""));
		assertEquals("<a href=\"/mimimi/user?user=jeronimo\">@jeronimo</a>", m.getMessageDecorated("/mimimi"));
		m = new Mimimi("#subjsect",user);
		assertEquals("<a href=\"/hashtag?hashtag=subjsect\">#subjsect</a>", m.getMessageDecorated(""));
		assertEquals("<a href=\"/mimimi/hashtag?hashtag=subjsect\">#subjsect</a>", m.getMessageDecorated("/mimimi"));
		m = new Mimimi("ble #subjsect bli @user bla",user);
		assertEquals("ble <a href=\"/hashtag?hashtag=subjsect\">#subjsect</a> bli <a href=\"/user?user=user\">@user</a> bla", m.getMessageDecorated(""));
		assertEquals("ble <a href=\"/m/hashtag?hashtag=subjsect\">#subjsect</a> bli <a href=\"/m/user?user=user\">@user</a> bla", m.getMessageDecorated("/m"));
	}

}
