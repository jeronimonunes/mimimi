package br.dcc.ufmg.pm.mimimi.dao;

import java.util.List;

import br.dcc.ufmg.pm.mimimi.model.Connection;
import br.dcc.ufmg.pm.mimimi.model.ConnectionId;
import br.dcc.ufmg.pm.mimimi.model.User;

public interface ConnectionDao extends Dao<ConnectionId,Connection> {
	
	public Long countFollowing(User user);

	public Long countFollowers(User user);

	public List<Connection> listFollowersByUser(int first, int pageSize, User user);

	public List<Connection> listFollowingByUser(int first, int pageSize, User user);

}
