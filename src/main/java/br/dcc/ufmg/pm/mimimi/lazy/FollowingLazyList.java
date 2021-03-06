package br.dcc.ufmg.pm.mimimi.lazy;

import br.dcc.ufmg.pm.mimimi.dao.ConnectionDao;
import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * {@link AbstractLazyList} to load the {@link User}'s following page
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
public class FollowingLazyList extends AbstractLazyList<User> {
	
	private static final long serialVersionUID = 1L;

	private ConnectionDao connectionDao;
	
	private User user;
	
	public FollowingLazyList(User user) {
		this.user = user;
		connectionDao = getDao(ConnectionDao.class);
	}

	@Override
	protected int load(int first, int pageSize) {
		super.setWrappedData(connectionDao.listFollowingUsersByUser(first,pageSize,user));
		return connectionDao.countFollowing(user).intValue();
	}

}
