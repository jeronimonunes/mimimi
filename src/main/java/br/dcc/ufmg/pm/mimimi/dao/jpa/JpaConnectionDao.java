package br.dcc.ufmg.pm.mimimi.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.dcc.ufmg.pm.mimimi.dao.ConnectionDao;
import br.dcc.ufmg.pm.mimimi.model.Connection;
import br.dcc.ufmg.pm.mimimi.model.ConnectionId;
import br.dcc.ufmg.pm.mimimi.model.User;

public class JpaConnectionDao extends AbstractJpaDao<ConnectionId, Connection> implements ConnectionDao {
	
	protected JpaConnectionDao() {
		
	}

	@Override
	public Class<Connection> getEntityClass() {
		return Connection.class;
	}
	
	@Override
	public Long countFollowing(User user) {
		HashMap<String, Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findSingleResult(Connection.COUNT_FOLLOWING, params);
	}

	@Override
	public Long countFollowers(User user) {
		HashMap<String, Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findSingleResult(Connection.COUNT_FOLLOWERS, params);
	}

	@Override
	public List<User> listFollowersUsersByUser(int first, int size, User user) {
		Map<String,Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findListResult(Connection.LIST_USERS_FOLLOWERS,params,first,size);
	}

	@Override
	public List<User> listFollowingUsersByUser(int first, int size, User user) {
		Map<String,Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findListResult(Connection.LIST_USERS_FOLLOWED,params,first,size);
	}

}
