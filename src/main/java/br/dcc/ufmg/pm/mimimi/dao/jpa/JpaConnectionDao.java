package br.dcc.ufmg.pm.mimimi.dao.jpa;

import br.dcc.ufmg.pm.mimimi.dao.ConnectionDao;
import br.dcc.ufmg.pm.mimimi.model.Connection;
import br.dcc.ufmg.pm.mimimi.model.ConnectionId;

public class JpaConnectionDao extends AbstractJpaDao<ConnectionId, Connection> implements ConnectionDao {

	@Override
	public Class<Connection> getEntityClass() {
		return Connection.class;
	}

}
