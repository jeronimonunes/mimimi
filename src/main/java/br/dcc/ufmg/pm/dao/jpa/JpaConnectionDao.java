package br.dcc.ufmg.pm.dao.jpa;

import br.dcc.ufmg.pm.model.Connection;
import br.dcc.ufmg.pm.model.ConnectionId;

public class JpaConnectionDao extends AbstractJpaDao<ConnectionId, Connection> {

	@Override
	public Class<Connection> getEntityClass() {
		return Connection.class;
	}

}
