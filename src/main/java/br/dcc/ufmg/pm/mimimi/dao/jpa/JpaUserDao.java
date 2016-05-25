package br.dcc.ufmg.pm.mimimi.dao.jpa;

import br.dcc.ufmg.pm.mimimi.model.User;

public class JpaUserDao extends AbstractJpaDao<String, User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}
