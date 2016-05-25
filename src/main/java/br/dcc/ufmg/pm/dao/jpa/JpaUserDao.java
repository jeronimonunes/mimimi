package br.dcc.ufmg.pm.dao.jpa;

import br.dcc.ufmg.pm.model.User;

public class JpaUserDao extends AbstractJpaDao<String, User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}
