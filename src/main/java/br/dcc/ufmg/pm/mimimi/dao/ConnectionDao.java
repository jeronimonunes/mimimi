package br.dcc.ufmg.pm.mimimi.dao;

import java.util.List;

import br.dcc.ufmg.pm.mimimi.model.Connection;
import br.dcc.ufmg.pm.mimimi.model.ConnectionId;
import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * {@link Dao} extended for {@link Connection}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
public interface ConnectionDao extends Dao<ConnectionId,Connection> {
	
	public Long countFollowing(User user);

	public Long countFollowers(User user);

	public List<User> listFollowersUsersByUser(int first, int pageSize, User user);

	public List<User> listFollowingUsersByUser(int first, int pageSize, User user);

}
