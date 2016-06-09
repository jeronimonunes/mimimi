package br.dcc.ufmg.pm.mimimi.dao.jpa;

import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.model.User;

public class JpaUserDao extends AbstractJpaDao<String, User> implements UserDao {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public void login(String username, String password) {
		// TODO Auto-generated method stub
		
	}

}
